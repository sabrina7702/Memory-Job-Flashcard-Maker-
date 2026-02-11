package persistence;

import org.json.JSONObject;

public interface Writable {
    // NOTE: credit to example given in Phase 2
    // EFFECTS: returns this as JSON object
    JSONObject toJson();
}
