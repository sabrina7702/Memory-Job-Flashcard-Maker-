package ui;

import java.util.List;

import model.Flashcard;
import model.FlashcardDeck;
import model.Folders;

// a jeopardy game that will be part of the main application to review cards
public class JeopardyGame {
    private FlashcardDeck flashcards;
    private int score;
    private Flashcard selectedCard;
    private String category;
    private int value;
    
    // REQUIRES: categories has at least 1 element
    // EFFECTS: constructs the game board with flashcards as questions
    // sets the score to 0
    public void startGame(Folders categories) { 
        
    }

    // EFFECTS: displays the game board with 4 columns 
    // of categories and the point values
    public void displayGameBoard() { 

    }

    // REQUIRES: category & value must be available to choose
    // EFFECTS: allows the user to select a question based on 
    // category and point value
    public void selectQuestion(int category, int value) { 

    }
    
    // MODIFIES: this
    // EFFECTS: sets the seclected ｃard based on the 
    // category and point value
    public void chooseQuestion(int category, int value) { 

    }

    // EFFECTS: checks if the user's answer is correct
    public boolean checkAnswer(String userAnswer) { 
        return false;
    }
    
    // MODIFIES: this
    // EFFECTS: Updates the user's score based on whether 
    // they answered correct or not
    public void updateScore(int value, boolean isCorrect) { 

    }

    // EFFECTS: end game if all flashcards have been answered
    // or 'opened'. Display score.
    public void endGame() { 

    }

    public int getScore() { 
        return 0;
    }
}
