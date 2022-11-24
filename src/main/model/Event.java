package model;

import java.util.Calendar;
import java.util.Objects;
import java.util.Date;

public class Event {
    private Date dateLogged;
    private String description;

    // REQUIRES: description != null
    // EFFECTS: creates an event with the date it was logged and its given description
    public Event(String description) {
        dateLogged = Calendar.getInstance().getTime();
        this.description = description;
    }

    public Date getDate() {
        return dateLogged;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return description + dateLogged;
    }
}
