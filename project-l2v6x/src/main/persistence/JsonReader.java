package persistence;

import model.Flashcard;
import model.FlashcardDeck;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.*;

// Represents a reader that reads workroom from JSON data stored in file
public class JsonReader {
    // NOTE: model from the example provided in Phase 2
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads flashcard deck from file and returns it;
    // throws IOException if an error occurs reading data from file
    public FlashcardDeck read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseFlashcardDeck(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses flashcard from JSON object and returns it
    private FlashcardDeck parseFlashcardDeck(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        FlashcardDeck fd = new FlashcardDeck(name);
        addFlashcard(fd, jsonObject);
        return fd;
    }

    // MODIFIES: fd
    // EFFECTS: parses flashcards from JSON object and adds them to flashcard deck
    private void addFlashcard(FlashcardDeck fd, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("flashcards");
        for (Object json : jsonArray) {
            JSONObject nextThingy = (JSONObject) json;
            addCard(fd, nextThingy);
        }
    }

    // MODIFIES: fd
    // EFFECTS: parses card info from JSON object and adds it to flashcard deck
    private void addCard(FlashcardDeck fd, JSONObject jsonObject) {
        String question = jsonObject.getString("question");
        String answer = jsonObject.getString("answer");
        boolean status = jsonObject.getBoolean("status");
        Flashcard card = new Flashcard(question, answer, status);
        fd.addCard(card);
    }
}
