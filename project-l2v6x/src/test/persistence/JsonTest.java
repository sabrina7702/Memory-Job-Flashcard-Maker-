package persistence;


import static org.junit.jupiter.api.Assertions.assertEquals;

import model.Flashcard;

public class JsonTest {
    // NOTE: adapted from example given in Phase 2
    protected void checkCard(String question, String answer, boolean status, Flashcard card) {
        assertEquals(question, card.getQuestion());
        assertEquals(answer, card.getAnswer());
        assertEquals(status, card.getStatus());
    }
}
