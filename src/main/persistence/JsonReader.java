package persistence;

import model.Occasion;
import model.OccasionList;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.json.*;

// some code adapted from Carter, P (2021) JsonSerializationDemo (Version 20210307) [Source Code].
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

// represents a reader that takes JSON data and reads the represented OccasionList
public class JsonReader {
    private String source;

    // EFFECTS: constructs a JSON reader to read the source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: returns a read occasion list from file and
    // throws IOException if an error occurs reading data from file
    public OccasionList read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseOccasionList(jsonObject);
    }

    // EFFECTS: returns source file as string
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: returns parsed occasion list from JSON object
    private OccasionList parseOccasionList(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        OccasionList ol = new OccasionList(name);
        addOccasions(ol, jsonObject);
        return ol;
    }

    // MODIFIES: ol
    // EFFECTS: adds parsed occasions to occasion list
    private void addOccasions(OccasionList ol, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("occasions");
        for (Object json : jsonArray) {
            JSONObject nextOccasions = (JSONObject) json;
            addOccasion(ol, nextOccasions);
        }
    }

    // MODIFIES: ol
    // EFFECTS: adds parsed occasion from JSON object to occasions list
    private void addOccasion(OccasionList ol, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        String date = jsonObject.getString("date");
        JSONArray jsonAttendees = jsonObject.getJSONArray("attendees");
        List<String> attendees = new ArrayList<>();
        if (jsonAttendees.length() > 0) {
            for (int i = 0; i < jsonAttendees.length(); i++) {
                attendees.add(jsonAttendees.optString(i));
            }
        }
        Occasion occasion = new Occasion(name, date, attendees);
        ol.addOccasion(occasion);
    }
}
