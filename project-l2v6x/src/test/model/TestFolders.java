package model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestFolders {
    private Folders foldersTest;
    private FlashcardDeck deck1;
    private FlashcardDeck deck2;

    @BeforeEach
    void runBefore() {
        foldersTest = new Folders();
        deck1 = new FlashcardDeck("A");
        deck2 = new FlashcardDeck("B");
    }
    
    @Test
    void testConstructor() {
        assertEquals(0, foldersTest.getDecks().size());
        assertEquals("Untitled Folder", foldersTest.getFolderName());
    }

    @Test
    void testAddDeck() {
        foldersTest.addDeck(deck1, "A");
        assertEquals(1, foldersTest.getDecks().size());
        assertEquals(deck1, foldersTest.getDecks().get(0));
        String deck1Name = foldersTest.getDecks().get(0).getDeckName();
        assertEquals("A", deck1Name);

        foldersTest.addDeck(deck2, "B");
        assertEquals(2, foldersTest.getDecks().size());
        assertEquals(deck2, foldersTest.getDecks().get(1));
        String deck2Name = foldersTest.getDecks().get(1).getDeckName();
        assertEquals("B", deck2Name);

        foldersTest.addDeck(deck1, "C");
        assertEquals(2, foldersTest.getDecks().size());
        assertEquals(deck1, foldersTest.getDecks().get(0));
        assertEquals(deck2, foldersTest.getDecks().get(1));
        deck1Name = foldersTest.getDecks().get(0).getDeckName();
        assertEquals("A", deck1Name);
    }

    @Test
    void testRemoveDeckOne() {
        FlashcardDeck deck3 = new FlashcardDeck("C");
        foldersTest.addDeck(deck1, "A");
        foldersTest.removeDeck("A");
        assertEquals(0, foldersTest.getDecks().size());

        foldersTest.addDeck(deck1, "A");
        foldersTest.addDeck(deck2, "B");
        foldersTest.addDeck(deck3, "C");
        assertEquals(3, foldersTest.getDecks().size());
        assertEquals(deck1, foldersTest.getDecks().get(0));
        assertEquals(deck2, foldersTest.getDecks().get(1));
        foldersTest.removeDeck("B");
        assertEquals(2, foldersTest.getDecks().size());
        assertEquals(deck1, foldersTest.getDecks().get(0));
    }

    @Test
    void testRemoveDeckMany() {
        foldersTest.addDeck(deck1, "A");
        foldersTest.addDeck(deck2, "B");
        foldersTest.removeDeck("A");
        assertEquals(1, foldersTest.getDecks().size());
        assertEquals(deck2, foldersTest.getDecks().get(0));
        foldersTest.removeDeck("B");
        assertEquals(0, foldersTest.getDecks().size());
    }

    @Test
    void testRenameFolder() {
        assertEquals("Untitled Folder", foldersTest.getFolderName());
        foldersTest.setFolderName("NewName");
        assertEquals("NewName", foldersTest.getFolderName());
    }
}
