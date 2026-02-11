package model;

import org.json.JSONObject;

import persistence.Writable;

// a class representing a single flashcard with question, answer, and review status
public class Flashcard implements Writable {
    private String question;
    private String answer;
    private boolean status;

    // EFFECTS: constructs a flashcard that has not been reviewed
    // and with no progess 
    public Flashcard(String question, String answer, boolean status) {
        this.question = question;
        this.answer = answer;
        this.status = status;
    }

    // EFFECTS: returns true if answer matches the flashcards
    // answer. False otherwise. (not case sensitive)
    public boolean checkAnswer(String answer) {
        return this.answer.equalsIgnoreCase(answer);
    }

    // MODIFIES: this
    // EFFECTS: changes the answer of the flashcard
    // to newAnswer
    public void editAnswer(String newAnswer) {
        this.answer = newAnswer;
    }

    // MODIFIES: this
    // EFFECTS: changes the question of the flashcard
    // to newQuestion
    public void editQuestion(String newQuestion) {
        this.question = newQuestion;
    }

    // MODIFIES: this
    // EFFECTS: mark card as reviewed
    public void markReviewed() {
        this.status = true;
        EventLog.getInstance().logEvent(new Event("Card is marked as reviewed."));
    }

    // MODIFIES: this
    // EFFECTS: mark card as not reviewed
    public void markUnreviewed() {
        this.status = false;
        EventLog.getInstance().logEvent(new Event("Card is marked as unreviewed."));
    }

    public String getAnswer() {
        return this.answer;
    }

    public String getQuestion() {
        return this.question;
    }

    public boolean getStatus() {
        return this.status;
    }

    // EFFECTS: saves cards data to a JSON file
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("question", question);
        json.put("answer", answer);
        json.put("status", status);
        return json;
    }
}
