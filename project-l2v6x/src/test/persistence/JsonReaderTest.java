package persistence;

import org.junit.jupiter.api.Test;

import model.Flashcard;
import model.FlashcardDeck;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JsonReaderTest extends JsonTest {
    // NOTE: model from example given in Phase 2
    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            FlashcardDeck fd = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyWorkRoom() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyWorkRoom.json");
        try {
            FlashcardDeck fd = reader.read();
            assertEquals("Flashcard deck name: ", fd.getDeckName());
            assertEquals(0, fd.getDeck().size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralWorkRoom() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralWorkRoom.json");
        try {
            FlashcardDeck fd = reader.read();
            assertEquals("Flashcard deck name: ", fd.getDeckName());
            List<Flashcard> flashcards = fd.getDeck();
            assertEquals(2, flashcards.size());
            checkCard("What flower is red", "rose", false, flashcards.get(0));
            checkCard("What colour is the sky", "blue", false, flashcards.get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}