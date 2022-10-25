package persistence;

import org.json.JSONObject;

// some code adapted from Carter, P (2021) JsonSerializationDemo (Version 20210307) [Source Code].
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

// represents an object that can transform code to JSON object
public interface Writable {
    // EFFECTS: returns this as a JSON object
    JSONObject toJson();
}
