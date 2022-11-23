package model;

import org.json.JSONObject;
import persistence.Writable;

import java.util.List;

// represents an occasion with a name, date, and list of attendees
public class Occasion implements Writable {
    private String name;
    private String date;
    private List<String> attendees;

    // REQUIRES: name of occasion does not already exist in occasion list and
    //          date is an acceptable date format (ex. Feb 12, 2010)
    // EFFECTS: constructs an occasion with a name, date, and attendee list
    public Occasion(String name, String date, List<String> attendees) {
        this.name = name;
        this.date = date;
        this.attendees = attendees;
    }

    // REQUIRES: !attendee.equals("")
    // MODIFIES: this
    // EFFECTS: adds an attendee to the list of attendees
    public void addAttendee(String attendee) {
        attendees.add(attendee);
    }

    // EFFECTS: converts occasion instance to JSON object
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

    // EFFECTS: returns the name of the occasion
    @Override
    public String toString() {
        return name;
    }
}
