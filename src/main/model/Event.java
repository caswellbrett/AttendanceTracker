package model;

import org.json.JSONObject;
import persistence.Writable;

import java.util.List;

// represents an event with a name, date, and list of attendees
public class Event implements Writable {
    private String name;
    private String date;
    private List<String> attendees;

    // REQUIRES: name of event does not already exist in event list and
    //          date is an acceptable date format (ex. Feb 12, 2010)
    // EFFECTS: constructs an event with a name, date, and attendee list
    public Event(String name, String date, List<String> attendees) {
        this.name = name;
        this.date = date;
        this.attendees = attendees;
    }

    public void addAttendee(String attendee) {
        attendees.add(attendee);
    }

    // EFFECTS: converts event instance to JSON object
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("date", date);
        json.put("attendees", attendees);
        return json;
    }

    public String getName() {
        return this.name;
    }

    public String getDate() {
        return this.date;
    }

    public List<String> getAttendees() {
        return this.attendees;
    }

    @Override
    public String toString() {
        return name;
    }
}
