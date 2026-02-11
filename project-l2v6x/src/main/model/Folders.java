package model;

import java.util.ArrayList;

// a class representing a collection of Flashcard decks/sets
public class Folders {
    private ArrayList<FlashcardDeck> folders;
    private String name;

    // EFFECTS: constructs a list with no flashcard decks and
    // given name
    public Folders() {
        folders = new ArrayList<>();
        this.name = "Untitled Folder";
    }

    // MODIFIES: this
    // EFFECTS: adds deck to the folders if not already with 
    // in folder with a given name 
    public void addDeck(FlashcardDeck deck, String name) {
        boolean hasNoDeck = true;
        for (FlashcardDeck d : folders) {
            if (d.equals(deck)) {
                hasNoDeck = false;
            }
        }

        if (hasNoDeck) {
            folders.add(deck);
        }
    }

    // REQUIRES: deckNameToRemove must be in folder
    // MODIFIES: this
    // EFFECTS: remove the given deck from the folder
    public void removeDeck(String deckNameToRemove) {
        boolean removed = false;
        int index = 0;
        FlashcardDeck currDeck = folders.get(index);

        while (!removed) {
            currDeck = folders.get(index);
            if (currDeck.getDeckName().equalsIgnoreCase(deckNameToRemove)) {
                folders.remove(currDeck);
                removed = true;
            }
            index++;
        }
    }

    public void setFolderName(String newFolderName) {
        name = newFolderName;
    }

    // EFFECTS: returns all flashcard decks in folder
    public ArrayList<FlashcardDeck> getDecks() {
        return folders;
    }

    public String getFolderName() {
        return name;
    }
}
