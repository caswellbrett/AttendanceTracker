package persistence;

import model.Event;
import model.EventList;

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

// represents a reader that takes JSON data and reads the represented EventList
public class JsonReader {
    private String source;

    // EFFECTS: constructs a JSON reader to read the source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: returns a read event list from file and
    // throws IOException if an error occurs reading data from file
    public EventList read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseEventList(jsonObject);
    }

    // EFFECTS: returns source file as string
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: returns parsed event list from JSON object
    private EventList parseEventList(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        EventList el = new EventList(name);
        addEvents(el, jsonObject);
        return el;
    }

    // MODIFIES: el
    // EFFECTS: adds parsed events to event list
    private void addEvents(EventList el, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("events");
        for (Object json : jsonArray) {
            JSONObject nextEvent = (JSONObject) json;
            addEvent(el, nextEvent);
        }
    }

    // MODIFIES: el
    // EFFECTS: adds parsed event from JSON object to events list
    private void addEvent(EventList el, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        String date = jsonObject.getString("date");
        JSONArray jsonAttendees = jsonObject.getJSONArray("attendees");
        List<String> attendees = new ArrayList<>();
        if (jsonAttendees.length() > 0) {
            for (int i = 0; i < jsonAttendees.length(); i++) {
                attendees.add(jsonAttendees.optString(i));
            }
        }
        Event event = new Event(name, date, attendees);
        el.addEvent(event);
    }
}
