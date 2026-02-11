package ui.gui;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import model.Flashcard;
import model.FlashcardDeck;
import ui.FlashcardReviewer;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

public class DeckPage extends JFrame {
    private FlashcardReviewer fcReviewer;
    private JPanel folderMenu;
    private JPanel cardMenu;
    private JPanel tools;
    private JButton addCardButton;
    private JButton removeCardButton;
    private JButton returnHomeButton;
    private JProgressBar bar;
    private int progress;

    // EFFECTS: Initializes DeckPage with the given FlashcardReviewer and sets up the GUI layout
    public DeckPage(FlashcardReviewer reviewer) {
        super("All Cards");
        fcReviewer = reviewer;
        progress = 0;
        bar = new JProgressBar();
        addCardButton = new JButton("+");
        setSize(800, 500);
        setLayout(new BorderLayout());
        setResizable(false);
        setLocationRelativeTo(null);
        setup();

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                promptSaveBeforeExit();
            }
        });
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setVisible(true);
    }

    // EFFECTS: Calls helper methods to set up different UI components of DeckPage
    public void setup() {
        makeFolderMenu();
        makeCardMenu();
        displayCards(fcReviewer.getFlashcards());
        cardButtons();
        displayProgress(false);
        makeReturnHomeButton();
    }
    
    // MODIFIES: this
    // EFFECTS: Creates new add and remove card buttons and adds them to the tools panel
    public void cardButtons() {
        addCardButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == addCardButton) {
                    fcReviewer.addCard();
                    displayProgress(false);
                    displayCards(fcReviewer.getFlashcards());
                }
            }
        });

        removeCardButton = new JButton("Remove Card");
        removeCardButton.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == removeCardButton) {
                    fcReviewer.removeCard();
                    displayProgress(false);
                    displayCards(fcReviewer.getFlashcards());
                } 
            }
        });
        tools.add(addCardButton);
        tools.add(removeCardButton);
    }

    // EFFECTS: Displays the flashcards in the provided deck; none if deck empty
    public void displayCards(ArrayList<Flashcard> deck) {
        cardMenu.removeAll();
        cardMenu.add(tools, BorderLayout.SOUTH);

        DefaultListModel<String> listOfCards = new DefaultListModel<>();
        int index = 0;
        for (Flashcard c : deck) {
            String info = index + " - Q: " + c.getQuestion() + " A: " + c.getAnswer() + " Status: " + c.getStatus();
            listOfCards.addElement(info);
            index++;
        }
       
        JList<String> cards = new JList<>(listOfCards);
        selectedCard(cards);

        cardMenu.add(cards, BorderLayout.NORTH);
        cardMenu.revalidate();
        cardMenu.repaint();
    }

    // EFFECTS: Detects when a flashcard is selected and asks
    //          user if it has been reviewed
    public void selectedCard(JList<String> cards) {
        cards.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int index = cards.getSelectedIndex();
                if (index != -1) {
                    int result = JOptionPane.showConfirmDialog(null, "Is card reviewed?",
                            "Card Status", JOptionPane.YES_NO_OPTION); 
                    updateStatus(result, index);
                } 
            }
        });
    }

    // REQUIRES: index is a valid index within the flashcard list
    // MODIFIES: flashcard
    // EFFECTS: Updates the review status of the flashcard at the given index
    //          then updates the displayed progress
    public void updateStatus(int result, int index) {
        boolean isReviewed = false;

        if (result == JOptionPane.YES_OPTION) {
            fcReviewer.getFlashcards().get(index).markReviewed();
        } else {
            fcReviewer.getFlashcards().get(index).markUnreviewed();
        }
        displayCards(fcReviewer.getFlashcards());
        displayProgress(isReviewed);
    }

    // MODIFIES bar
    // EFFECTS: Updates and displays the progress bar based on reviewed flashcards
    public void displayProgress(boolean isReviewed) {
        int total = fcReviewer.getFlashcards().size();
        if (!(total > 0)) {
            total = 1;
        }

        bar.setMaximum(total);
        bar.setValue(calculateProgress());
        bar.setStringPainted(true);

        if (isReviewed) {
            progress++;
            bar.setValue(progress);
        }
        add(bar, BorderLayout.NORTH);
        revalidate();
        repaint();
    }

    // EFFECTS: Returns the number of reviewed flashcards in the deck
    public int calculateProgress() {
        int count = 0;
        for (Flashcard c : fcReviewer.getFlashcards()) {
            if (c.getStatus()) {
                count++;
            }
        }
        return count;
    }

    // MODIFIES: this
    // EFFECTS: Creates a return home button and adds it to the folder menu
    public void makeReturnHomeButton() {
        returnHomeButton = new JButton("<");
        returnHomeButton.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == returnHomeButton) {
                    HomePage homePage = new HomePage(fcReviewer);
                    dispose();
                }
            }
        });
        folderMenu.add(returnHomeButton);
    }

    // MODIFIES: this
    // EFFECTS: Creates the folder menu panel and adds it to the frame
    public void makeFolderMenu() {
        folderMenu = new JPanel();
        folderMenu.setBackground(Color.BLUE);
        folderMenu.setPreferredSize(new Dimension(150, 100));
        add(folderMenu, BorderLayout.WEST);
    }

    // MODIFIES: this
    // EFFECTS: Creates the card menu panel and adds it to frame
    public void makeCardMenu() {
        cardMenu = new JPanel();
        cardMenu.setLayout(new BorderLayout()); 
        cardMenu.setBackground(Color.RED);
        cardMenu.setPreferredSize(new Dimension(300, 300));

        tools = new JPanel();
        tools.setLayout(new GridLayout(1, 2, 10, 40));

        cardMenu.add(tools, BorderLayout.SOUTH);
        add(cardMenu, BorderLayout.CENTER);
    }

    // MODIFIES: JSON
    // EFFECTS: Asks if user wants to save progress:
    //          - YES: Saves progress and exits the application
    //          - NO: Exits without saving
    //          - CANCEL: Does nothing
    private void promptSaveBeforeExit() {
        int choice = JOptionPane.showConfirmDialog(this,
                "Do you want to save your progress before exiting?",
                "Exit Confirmation", JOptionPane.YES_NO_CANCEL_OPTION);

        if (choice == JOptionPane.YES_OPTION) {
            fcReviewer.saveFlashcardReviewer();
            JOptionPane.showMessageDialog(this, "Flashcards saved successfully!");
            System.exit(0);
        } else if (choice == JOptionPane.NO_OPTION) {
            System.exit(0);
        } 
    }
}
