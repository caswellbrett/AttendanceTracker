package persistence;

import model.EventList;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

// some code adapted from Carter, P (2021) JsonSerializationDemo (Version 20210307) [Source Code].
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

// represents an object that transforms representation of event list to JSON file
public class JsonWriter {
    private static final int TAB = 4;
    private PrintWriter writer;
    private String destination;

    // EFFECTS: constructs writer to write user's input into JSON file
    public JsonWriter(String destination) {
        this.destination = destination;
    }

    // MODIFIES: this
    // EFFECTS: opens writer; throws FileNotFoundException if destination file cannot
    // be opened for writing
    public void open() throws FileNotFoundException {
        writer = new PrintWriter(new File(destination));
    }

    // MODIFIES: this
    // EFFECTS: writes JSON version of event list to JSON file
    public void write(EventList el) {
        JSONObject json = el.toJson();
        saveToFile(json.toString(TAB));
    }

    // MODIFIES: this
    // EFFECTS: closes the JSON writer
    public void close() {
        writer.close();
    }

    // MODIFIES: this
    // EFFECTS: takes a string and transforms it to JSON file
    private void saveToFile(String json) {
        writer.print(json);
    }
}
