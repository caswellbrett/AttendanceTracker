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
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Event)) {
            return false;
        }
        Event event = (Event) o;
        return Objects.equals(dateLogged, event.dateLogged) && Objects.equals(getDescription(), event.getDescription());
    }

    @Override
    public int hashCode() {
        return Objects.hash(dateLogged, getDescription());
    }

    @Override
    public String toString() {
        return description + dateLogged;
    }
}
