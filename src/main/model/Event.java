package model;

import java.util.List;

// represents an event with a name, date, and list of attendees
public class Event {
    private String name;
    private String date;
    private List<String> attendees;

    // EFFECTS: constructs an event with a name, date, and attendee list
    public Event(String name, String date, List<String> attendees) {
        this.name = name;
        this.date = date;
        this.attendees = attendees;
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

}
