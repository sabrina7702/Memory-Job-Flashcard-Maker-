package ui;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JOptionPane;

import model.Flashcard;
import model.FlashcardDeck;
import model.Folders;
import model.EventLog;
import persistence.JsonReader;
import persistence.JsonWriter;
import ui.gui.DeckPage;
import ui.gui.HomePage;

// the main flashcard application that allows users to add, remove, edit,
// and review flashcards with minigames.
public class FlashcardReviewer {
    // NOTE: Code is adapted from lab 4.2 and example of Phase 2 of the course CPSC 210 (2025)
    private FlashcardDeck flashcardDeck;
    private Folders decks;
    private int currentCardIndex = 0;
    private Scanner scanner;
    private boolean isProgramRunning;
    private MatchMakerGame matchingGame;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private HomePage gui;
    private static final String JSON_STORE = "./data/workroom.json";
    private DeckPage deckPage;

    // EFFECTS: creates an instance of the FlashcardReviewer console ui application
    public FlashcardReviewer() {
        this.flashcardDeck = new FlashcardDeck("Untitled Deck");
        this.decks = new Folders();
        this.scanner = new Scanner(System.in);
        this.isProgramRunning = true;
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        gui = new HomePage(this);

        while (this.isProgramRunning) {
            handleMenu();
        }
        
    }

    // EFFECTS: displays and processes inputs for the main menu
    public void handleMenu() {
        displayMenu();
        String input = this.scanner.nextLine();

    }

    // EFFECTS: displays a list of commands that can be used in the main menu
    public void displayMenu() {
        System.out.println("Pick an option:");
        System.out.println("1: Create flashcard");
        System.out.println("2: Remove flashcard");
        System.out.println("3: Make folder/deck");
        System.out.println("4: View all flashcards");
        System.out.println("5: View all unreviewed flashcards");
        System.out.println("6: View all reviewed flashcards");
        System.out.println("7: View all decks");
        System.out.println("\n8: Review Flashcards (basic)");
        System.out.println("9: Play Jeopardy");
        System.out.println("10: Play matching game");
        System.out.println("11: Try multiple choice quiz");
        System.out.println("s: save work room to file");
        System.out.println("l: load work room from file");
        printDivider();
    }

    public void processMenuCommands(String input) {
        switch (input) {
            case "1":
                addCard();
                break;
            case "2":
                removeCard();
                break;
            case "3":
                createDeck();
                break;
            case "4":
                viewAllCards();
                break;
            case "5":
                viewAllCards();
                break;
            case "6":
                viewReviewed();
                break;
            case "7":
                viewDecks();
                break;
            case "8":
                reviewFlashcardsBasic();
                break;
            case "9":
                // TODO: call to jeopardy class

                break;
            case "10":
                playMatchingGame();
                break;
            case "11":
                // TODO: call to MC quiz class

                break;
            case "s":
                saveFlashcardReviewer();
                break;
            case "l":
                loadFlashcardReviewer();
                break;
            default:
                System.out.println("Invalid option inputted. Please try again.\n");
        }
        printDivider();
    }

    // MODIFIES: this
    // EFFECTS: adds a flashcard to the list of flashcards
    public void addCard() {
        String question = JOptionPane.showInputDialog("Enter the flashcard's question:");

        String answer = JOptionPane.showInputDialog("\nEnter the flashcard's answer:");

        Flashcard flashcard = new Flashcard(question, answer, false);

        flashcardDeck.addCard(flashcard);
        JOptionPane.showMessageDialog(null, 
                "New flashcard successfully created",
                ".", JOptionPane.INFORMATION_MESSAGE);
    }

    // MODIFIES: this
    // EFFECTS: remove selected flashcard from deck
    public void removeCard() {
        if (flashcardDeck.getDeck().isEmpty()) {
            JOptionPane.showMessageDialog(null, 
                     "You have no cards to remove!",
                    ".", JOptionPane.INFORMATION_MESSAGE);  
            return;
        }
        
        int cardRemove = Integer.parseInt(JOptionPane.showInputDialog(
                "Enter the number corresponding to the card you want to remove"));

        flashcardDeck.removeCard(cardRemove);
        JOptionPane.showMessageDialog(null, 
                "Card successfully removed!",
                ".", JOptionPane.INFORMATION_MESSAGE);
    }



    // MODIFIES: this
    // EFFECTS: makes a new deck
    public void createDeck() {
        System.out.println("Give the name of the deck:");
        String name = this.scanner.nextLine();

        FlashcardDeck newDeck = new FlashcardDeck(name);
        decks.addDeck(newDeck, name);

        int input = 0;
        while (input != 3) {
            printDeckMenu();
            input = this.scanner.nextInt();
            if (input == 1) {
                System.out.println("Enter the index of the card you want to add to this deck");
                // viewAllFlashcards(flashcardDeck.getDeck());
                int index = this.scanner.nextInt();
                newDeck.addCard(flashcardDeck.getDeck().get(index));
                System.out.println("Card successfully added\n");
            } else if (input == 2) {
                // viewAllFlashcards(newDeck.getDeck());
            } else if (input == 3) {
                System.out.println("Returning to main menu...");
            } else {
                System.out.println("Invalid option: choose again\n");
            }
            printDivider();
        }
        this.scanner.nextLine(); 
    }

    // EFFECTS:  displays a list of commands that can be used in the view deck menu
    public void printDeckMenu() {
        System.out.println("Enter '1' to add a card");
        System.out.println("Enter '2' to view all cards in this deck");
        System.out.println("Enter '3' to return to main menu");
    }

    // // MODIFIES: this
    // // EFFECTS: displays all flashcards one at a time
    // public void viewAllCards() {
    //     viewAllFlashcards(flashcardDeck.getDeck());
    // }

    // public void viewReviewed() {
    //     viewAllFlashcards(flashcardDeck.getReviewedDeck());
    // }

    // MODIFIES: this
    // EFFECTS: displays all decks one at a time
    public void viewDecks() {
        if (decks.getDecks().isEmpty()) {
            System.out.println("\nError: You made no decks. Try creating a deck first!");
            return;
        }

        System.out.println("Here are your decks:");
        int index = 0;
        for (FlashcardDeck deck : decks.getDecks()) {
            System.out.println(index + ". " + deck.getDeckName());
            index++;
        }
    }

    // // MODIFIES: this
    // // EFFECTS: displays only the unreviewed flashcards at same time
    // public void viewUnreviewedCards() {
    //     System.out.println("Flashcards to be reviewed:");
    //     viewAllFlashcards(flashcardDeck.getDeck());
    // }

    // EFFECTS: displays a list of commands that can be used in the view deck menu
    public void viewDeckMenu() {
        System.out.println("Entire '1' to create a new deck");
        System.out.println("Entire '2' to remove a deck");
        System.out.println("Entire '3' to review a deck");
        System.out.println("Entire '4' to return to main menu");
        printDivider();
    }

    // EFFECTS: displays a list of commands that can be used in the view flashcard menu
    public void reviewFlashcardsBasic() {
        System.out.println("Enter '1' to answer the flashcard.");
        System.out.println("Enter '<' to move to the previous flashcard.");
        System.out.println("Enter '>' to move to the next flashcard.");
        System.out.println("Enter '4' to return to the main menu.");
        printDivider();
    }

    // MODIFIES: this
    // EFFECTS: processes the user's input in the view flashcards menu
    public void handleCardReviewCommands(String input, List<Flashcard> flashcards) {
        System.out.print("\n");

        Flashcard currentFlashcard = flashcards.get(this.currentCardIndex);
        switch (input) {
            case "1":
                answerFlashcard(currentFlashcard);
                break;
            case "<":
                getPreviousFlashcard();
                break;
            case ">":
                getNextFlashcard(flashcards);
                break;
            case "4":
                System.out.println("Returning to the menu...");
                break;
            default:
                System.out.println("Invalid option inputted. Please try again.\n");
        }
    }

    // EFFECTS: displays the answer of the given flashcard
    public void answerFlashcard(Flashcard flashcard) {
        String answer = this.scanner.nextLine();
        boolean isCorrect = flashcard.checkAnswer(answer);

        if (isCorrect) {
            System.out.println("Correct!");
            reviewFlashcardsBasic();
        } else {
            System.out.println("Incorrect...");
            reviewFlashcardsBasic();
        }
    }

    // EFFECTS: if there is another flashcard to display, increments the current flashcard index
    public void getNextFlashcard(List<Flashcard> flashcards) {
        if (this.currentCardIndex >= flashcards.size() - 1) {
            System.out.println("Error: No more new flashcards to display!");
        } else {
            this.currentCardIndex++;
        }
    }

    // EFFECTS: if there is a previous flashcard to display, decrements the current flashcard index
    public void getPreviousFlashcard() {
        if (this.currentCardIndex <= 0) {
            System.out.println("Error: No more previous flashcards to display!");
        } else {
            this.currentCardIndex--;
        }
    }

    // EFFECTS: returns flashcard deck;
    public ArrayList<Flashcard> getFlashcards() {
        return flashcardDeck.getDeck();
    }

    // EFFECTS: starts the Match Maker game
    public void playMatchingGame() {
        System.out.println("Select a deck's index to make a match maker game:");
        if (decks.getDecks().isEmpty()) {
            System.out.println("0 - Untitled Deck");
        } else {
            viewDecks();
        }

        int index = this.scanner.nextInt();
        if (decks.getDecks().isEmpty()) {
            matchingGame.startGame(flashcardDeck);
        } else {
            matchingGame.startGame(decks.getDecks().get(index));
        }
        
    }

    // MODIFIES: this
    // EFFECTS: prints a closing message and marks the program as not running
    public void exitApplication() {
        System.out.println("Closing App...");
        isProgramRunning = false;
    }

    // EFFECTS: prints out a line of dashes to act as a divider
    private void printDivider() {
        System.out.println("___________________________________");
    }

     // EFFECTS: saves the workroom to file
    public void saveFlashcardReviewer() {
        try {
            jsonWriter.open();
            jsonWriter.write(flashcardDeck);
            jsonWriter.close();
            System.out.println("Saved " + flashcardDeck.getDeckName() + " to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads workroom from file
    public void loadFlashcardReviewer() {
        try {
            flashcardDeck = jsonReader.read();
            System.out.println("Loaded " + flashcardDeck.getDeckName() + " from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }
}
