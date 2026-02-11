package model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestFlashcard {
    // NOTE: tests and codes are adapted from lab 4.2 of the course CPSC 210 (2025)
    Flashcard card1;
    Flashcard card2;

    @BeforeEach
    void runBefore() {
        card1 = new Flashcard("Is the sky blue?", "Yes", false);
        card2 = new Flashcard("Where does milk come from?", "Cows", false);
    }

    @Test
    void testConstructor() {
        assertEquals("Is the sky blue?", card1.getQuestion());
        assertEquals("Yes", card1.getAnswer());
        assertEquals("Where does milk come from?", card2.getQuestion());
        assertEquals("Cows", card2.getAnswer());
    }

    @Test
    void testCheckAnswer() {
        assertTrue(card1.checkAnswer("Yes"));
        assertFalse(card1.checkAnswer("No!"));
        assertTrue(card2.checkAnswer("Cows"));
        assertFalse(card2.checkAnswer("Pigs"));
    }

    @Test
    void testMarkReviewed() {
        card1.markReviewed();
        assertTrue(card1.getStatus());

        card1.markUnreviewed();
        card1.markReviewed();
        card2.markReviewed();
        assertTrue(card1.getStatus());

        card2.markReviewed();
        assertTrue(card2.getStatus());

        card2.markUnreviewed();
        card2.markReviewed();
        card2.markReviewed();
        assertTrue(card2.getStatus());
    }

    @Test
    void testMarkUnreviewed() {
        card1.markUnreviewed();
        assertFalse(card1.getStatus());

        card2.markUnreviewed();
        assertFalse(card2.getStatus());
    }

    @Test
    void testEditAnswer() {
        card1.editAnswer("No");
        assertEquals("No", card1.getAnswer());
        card1.editAnswer("maybe");
        assertEquals("maybe", card1.getAnswer());

        card2.editAnswer("Chickens");
        assertEquals("Chickens", card2.getAnswer());
        card2.editAnswer("Cows");
        assertEquals("Cows", card2.getAnswer());
    }

    @Test
    void testEditQuestion() {
        card1.editQuestion("What color is a pig?");
        assertEquals("What color is a pig?", card1.getQuestion());
        card1.editQuestion("Does the egg come before the chicken?");
        assertEquals("Does the egg come before the chicken?", card1.getQuestion());

        card2.editQuestion("Define atom");
        assertEquals("Define atom", card2.getQuestion());
        card2.editQuestion("What month is Christmas?");
        assertEquals("What month is Christmas?", card2.getQuestion());
    }

    
}
