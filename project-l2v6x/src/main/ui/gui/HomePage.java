package ui.gui;

import javax.swing.*;


import ui.FlashcardReviewer;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

// Constructs the HomePage frame with the given FlashcardReviewer
public class HomePage extends JFrame {
    private JPanel panel;
    private JButton cardsButton;
    private FlashcardReviewer reviewer;
    private ImageIcon bgImage;
    private JLabel label;

    // EFFECTS: Initializes the GUI with menu options and prompts user to load saved flashcards
    public HomePage(FlashcardReviewer reviewer) {
        super("Memory Jog App");
        this.reviewer = reviewer;
        bgImage = new ImageIcon(getClass().getResource("background.jpg"));
        label = new JLabel(bgImage);
        label.setBounds(0, 0, 800, 500);
        add(label);

        setSize(800, 500);
        setResizable(false);
        setLayout(null); 
        setLocationRelativeTo(null);

        getMenuOptions();

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                promptSaveBeforeExit();
            }
        });

        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: Asks if user wants to load previously saved flashcards;
    //          adds buttons for navigating to flashcard decks or reviewing flashcards
    public void getMenuOptions() {
        panel = new JPanel();
        panel.setLayout(new GridLayout(2, 1, 0, 5)); 
        panel.setBounds(300, 200, 200, 80);

        int choice = JOptionPane.showConfirmDialog(this, 
                "Would you like to load your previously saved flashcards?", 
                "Load Flashcards", JOptionPane.YES_NO_CANCEL_OPTION);

        if (choice == JOptionPane.YES_OPTION) {
            reviewer.loadFlashcardReviewer();
        }

        panel.add(getCardsButton());
        add(panel);
    }

    // EFFECTS: Returns button that when clicked opens DeckPage and closes HomePage
    public JButton getCardsButton() {
        cardsButton = new JButton("View Flashcards & Decks");
        cardsButton.setFocusable(false);
        cardsButton.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == cardsButton) {
                    DeckPage deckPage = new DeckPage(reviewer);
                    dispose();
                }
            }
        });

        return cardsButton;
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
            reviewer.saveFlashcardReviewer();
            JOptionPane.showMessageDialog(this, "Flashcards saved successfully!");
            System.exit(0);
        } else if (choice == JOptionPane.NO_OPTION) {
            System.exit(0);
        } 
    }
}
