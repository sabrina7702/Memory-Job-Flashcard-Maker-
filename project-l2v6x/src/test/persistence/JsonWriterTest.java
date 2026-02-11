package persistence;

import org.junit.jupiter.api.Test;

import model.Flashcard;
import model.FlashcardDeck;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JsonWriterTest extends JsonTest {
    // NOTE: model from example given in Phase 2
    
    //NOTE TO CPSC 210 STUDENTS: the strategy in designing tests for the JsonWriter is to
    //write data to a file and then use the reader to read it back in and check that we
    //read in a copy of what was written out.

    @Test
    void testWriterInvalidFile() {
        try {
            FlashcardDeck fd = new FlashcardDeck("My work room");
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyWorkroom() {
        try {
            FlashcardDeck fd = new FlashcardDeck("My work room");
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyWorkroom.json");
            writer.open();
            writer.write(fd);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyWorkroom.json");
            fd = reader.read();
            assertEquals("My work room", fd.getDeckName());
            assertEquals(0, fd.getDeck().size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralWorkroom() {
        try {
            FlashcardDeck wr = new FlashcardDeck("Flashcard deck name: ");
            wr.addCard(new Flashcard("What sound do dogs make", "bark", false));
            wr.addCard(new Flashcard("What season has snow", "winter", false));
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralWorkroom.json");
            writer.open();
            writer.write(wr);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralWorkroom.json");
            wr = reader.read();
            assertEquals("Flashcard deck name: ", wr.getDeckName());
            List<Flashcard> flashcards = wr.getDeck();
            assertEquals(2, flashcards.size());
            checkCard("What sound do dogs make", "bark", false, flashcards.get(0));
            checkCard("What season has snow", "winter", false, flashcards.get(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}