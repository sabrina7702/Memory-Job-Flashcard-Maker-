package ui;

import java.util.List;

import model.Flashcard;
import model.FlashcardDeck;

// a multiple choice quiz that is part of the main application
public class MCQuiz {
    private FlashcardDeck flashcards;
    private FlashcardDeck questions;
    private int currentQuestionIndex;
    private int score;
    
    // EFFECTS: construct quiz of how ever many questions the
    // user wants from the deck that they choose. Sets score to 0
    public void startQuiz(int numOfQuestion, FlashcardDeck deck) { 

    }

    // REQUIRES: questions has at least 1 question
    // MODIFIES: this
    // EFFECTS: randomly shuffles the order of questions
    public void shuffleQuestions() {

    }

    // EFFECTS: displays the current question and 4 random answers,
    // except one answer is the correct answer
    public void displayQuestion() { 

    }

    // EFFECTS: generates a shuffled list of multiple-choice options, 
    // including the correct answer and distractors.
    public List<String> generateAnswerChoices(Flashcard flashcard) { 
        return null;
    }
    
    // MODIFIES: this
    // EFFECTS: checks the user’s answer, updates the score if correct, 
    // and moves on to the next question
    public void submitAnswer(String userAnswer) { 

    }

    // EFFECTS: returns true if the user's answer matches the correct
    //  answer, otherwise false.
    public boolean checkAnswer(String userAnswer, String correctAnswer) { 
        return false;
    }
    
    // EFFECTS: increments the score if answer is correct
    public void updateScore() { 

    }

    // EFFECTS: returns true if all questions have been answered, 
    // otherwise false
    public boolean isQuizOver() { 
        return false;
    }

    public void endQuiz() { 

    }

    public int getScore() { 
        return 0;
    }

    public String getAnswer() {
        return null;
    }
}
