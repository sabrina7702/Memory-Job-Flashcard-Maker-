package ui;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import model.Flashcard;
import model.FlashcardDeck;

// a matching game that will be part of the main application to review cards
public class MatchMakerGame {
    private int score;
    private boolean status;
    private FlashcardDeck unmatchedPairs;
    private ArrayList<String> questions;
    private ArrayList<String> answers;
    private ArrayList<Flashcard> matchedPairs;
    private Random rand = new Random();

    // EFFECTS: constructs the game board with matching 
    //(Q/A) pairs by shuffling the terms and definitions 
    public void startGame(FlashcardDeck flashcards) { 
        this.unmatchedPairs = flashcards;
        matchedPairs = new ArrayList<>();
        setup();
    }

    // EFFECTS: Sets up the questions and answers into seperate lists
    public void setup() {
        int boardSize = rand.nextInt(unmatchedPairs.getDeck().size());
        ArrayList<Flashcard> board = new ArrayList<>();
        for (int i = 0; i < boardSize; i++) {
            board.add(unmatchedPairs.getDeck().get(i));
        }

        for (Flashcard card : board) {
            questions.add(card.getQuestion());
            answers.add(card.getAnswer());
        }

        shufflePairs(questions);
        shufflePairs(answers);
    }

    // EFFECTS: displays the non-matched shuffled terms and 
    // definitions on the game board
    public void displayGameBoard() { 
        int questionNum = 1;
        System.out.println("Questions:");
        for (String q : questions) {
            System.out.println(questionNum + q);
        }

        System.out.println("Answer choices:");
        for (String a : answers) {
            System.out.println(a);
        }
    }

    // MODIFIES: this
    // EFFECTS: randomly shuffles the order of terms and definitions for the game
    public void shufflePairs(ArrayList<String> list) { 
        Collections.shuffle(list);
    }
    
    // EFFECTS: returns true if the term and definition correctly match, 
    // otherwise false
    public boolean checkMatch(String term, String definition) { 
        return term.equalsIgnoreCase(definition);
    }

    // MODIFIES: this
    // EFFECTS: removes matched pair from the unmatched pairs
    public void markAsMatched(String term, String definition) { 
        questions.remove(term);
        answers.remove(definition);
    }

    // MODIFIES: this
    // EFFECTS: increments score 
    public void increaseScore() { 
        score++;
    }

    // EFFECTS: returns true and ends game if no more questions, otherwise false
    public boolean isGameOver() { 
        boolean isGameOver = false;
        if (questions.isEmpty()) {
            System.out.println("Game Ended");
            isGameOver = true;
        }
        return isGameOver;
    }

    public int getScore() { 
        return score;
    }
}
