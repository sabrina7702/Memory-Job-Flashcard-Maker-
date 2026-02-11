package model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestFlashcardDeck {
    private Flashcard card1;
    private Flashcard card2;
    private FlashcardDeck deck1;

    @BeforeEach
    void runBefore() {
        card1 = new Flashcard("Is the sky blue?", "Yes", false);
        card2 = new Flashcard("Where does milk come from?", "Cows", false);
        deck1 = new FlashcardDeck("A");
    }
    
    @Test
    void testConstructor() {
        assertEquals(0, deck1.getDeck().size());
        assertEquals("A", deck1.getDeckName());
        assertEquals(0, deck1.getReviewedDeck().size());
    }

    @Test
    void testAddCard() {
        deck1.addCard(card1);
        assertEquals(1, deck1.getDeck().size());
        assertEquals(card1, deck1.getDeck().get(0));
        
        deck1.addCard(card2);
        assertEquals(2, deck1.getDeck().size());
        assertEquals(card2, deck1.getDeck().get(1));

        deck1.addCard(card1);
        assertEquals(2, deck1.getDeck().size());
        assertEquals(card1, deck1.getDeck().get(0));
        assertEquals(card2, deck1.getDeck().get(1));

        assertEquals(0, deck1.getReviewedDeck().size());
    }

    @Test
    void testRemoveCardFirstCard() {
        deck1.addCard(card1);
        deck1.removeCard(0);
        assertEquals(0, deck1.getDeck().size());

        deck1.addCard(card1);
        deck1.addCard(card2);
        deck1.removeCard(0);
        assertEquals(1, deck1.getDeck().size());
        assertEquals(card2, deck1.getDeck().get(0));

        assertEquals(0, deck1.getReviewedDeck().size());
    }

    @Test
    void testRemoveCardInMiddle() {
        deck1.addCard(card1);
        deck1.addCard(card2);
        deck1.removeCard(1);
        assertEquals(1, deck1.getDeck().size());
        assertEquals(card1, deck1.getDeck().get(0));
    }

    @Test
    void testAddToReviewedOneCard() {
        deck1.addCard(card1);
        deck1.addToReviewed(card1);
        assertEquals(0, deck1.getDeck().size());
        assertEquals(1, deck1.getReviewedDeck().size());
        assertEquals(card1, deck1.getReviewedDeck().get(0));

        deck1.getReviewedDeck().clear();
        deck1.addCard(card1);
        deck1.addCard(card2);
        deck1.addToReviewed(card2);
        assertEquals(1, deck1.getDeck().size());
        assertEquals(1, deck1.getReviewedDeck().size());
        assertEquals(card2, deck1.getReviewedDeck().get(0));
    }

    @Test
    void testAddToReviewedManyCards() {
        deck1.addCard(card1);
        deck1.addCard(card2);
        deck1.addToReviewed(card2);
        assertEquals(1, deck1.getDeck().size());
        assertEquals(card1, deck1.getDeck().get(0));
        assertEquals(1, deck1.getReviewedDeck().size());
        assertEquals(card2, deck1.getReviewedDeck().get(0));

        deck1.addToReviewed(card1);
        assertEquals(0, deck1.getDeck().size());
        assertEquals(2, deck1.getReviewedDeck().size());
        assertEquals(card2, deck1.getReviewedDeck().get(0));
        assertEquals(card1, deck1.getReviewedDeck().get(1));
    }

    @Test
    void testResetDeckProgress() {
        deck1.addCard(card1);
        deck1.addToReviewed(card1);
        deck1.resetDeckProgress();
        assertEquals(0, deck1.getReviewedDeck().size());
        assertEquals(1, deck1.getDeck().size());
        assertEquals(card1, deck1.getDeck().get(0));

        deck1.addCard(card1);
        deck1.addCard(card2);
        deck1.addToReviewed(card1);
        deck1.addToReviewed(card2);
        deck1.resetDeckProgress();
        assertEquals(0, deck1.getReviewedDeck().size());
        assertEquals(2, deck1.getDeck().size());
        assertEquals(card1, deck1.getDeck().get(0));
        assertEquals(card2, deck1.getDeck().get(1));
    }

    @Test
    void renameDeck() {
        assertEquals("A", deck1.getDeckName());
        deck1.renameDeck("B");
        assertEquals("B", deck1.getDeckName());
    }
}
