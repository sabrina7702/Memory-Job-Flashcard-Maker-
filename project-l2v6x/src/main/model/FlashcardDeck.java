package model;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import persistence.Writable;

// a class representing collection of flashcards
public class FlashcardDeck implements Writable {
    private ArrayList<Flashcard> deck;
    private ArrayList<Flashcard> reviewedDeck;
    private String deckName;

    // EFFECTS: constructs an empty deck of flashcards with
    // no cards reviewed and a given name
    public FlashcardDeck(String name) {
        this.deck = new ArrayList<>();
        this.reviewedDeck = new ArrayList<>();
        this.deckName = name;
    }

    // MODIFIES: this
    // EFFECTS: adds card to this collection of flashcards if 
    // not already in deck
    public void addCard(Flashcard card) {
        boolean notInDeck = true;
        for (Flashcard c : deck) {
            if (c.equals(card)) {
                notInDeck = false;
            }
        }

        if (notInDeck) {
            deck.add(card);
        }
        EventLog.getInstance().logEvent(new Event("Card is added."));
    }

    // REQUIRES: size of deck > 0
    // MODIFIES: this
    // EFFECTS: removes card at index from deck
    public void removeCard(int index) {
        deck.remove(index);
        EventLog.getInstance().logEvent(new Event("Card is removed."));
    }

    // REQUIRES: deck has at least 1 card
    // MODIFIES: this
    // EFFECTS: moves the given card from its original 
    // deck to the reviewed deck
    public void addToReviewed(Flashcard card) {
        reviewedDeck.add(card);
        deck.remove(card);
    }

    // REQUIRES: reviewedDeck has at least 1 card
    // MODIFIES: this
    // EFFECTS: moves all cards from the reviewed deck to 
    // its orginal deck in order that was put into the
    // reviewed deck
    public void resetDeckProgress() {
        for (Flashcard c : reviewedDeck) {
            deck.add(c);
        }

        while (reviewedDeck.size() > 0) {
            reviewedDeck.remove(0);
        }
    }

    // MODIFIES: this
    // EFFECTS: changes the name of the flashcard deck
    public void renameDeck(String newDeckName) {
        this.deckName = newDeckName;
    }

    public ArrayList<Flashcard> getDeck() {
        return deck;
    }

    public String getDeckName() {
        return deckName;
    }

    public ArrayList<Flashcard> getReviewedDeck() {
        return reviewedDeck;
    }

    // EFFECTS: saves decks data to JSON file
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", deckName);
        json.put("flashcards", flashcardsToJson());
        return json;
    }

    // EFFECTS: returns cards in this flashcard deck as a JSON array
    private JSONArray flashcardsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Flashcard c : deck) {
            jsonArray.put(c.toJson());
        }

        return jsonArray;
    }

    
}
