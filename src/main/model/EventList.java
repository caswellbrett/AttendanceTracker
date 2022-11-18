package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.List;
import java.util.ArrayList;

// represents a list of events
public class EventList implements Writable {
    private List<Event> listOfEvents;
    private String name;

    // EFFECTS: instantiates an event list
    public EventList(String name) {
        listOfEvents = new ArrayList<>();
        this.name = name;
    }

    // EFFECTS: Returns true if the given event name is already an event name in our list
    public boolean isNameTaken(String nameEntry) {
        boolean takenName = false;
        for (Event event : listOfEvents) {
            if (nameEntry.equals(event.getName())) {
                takenName = true;
                break;
            }
        }
        return takenName;
    }

    // REQUIRES:    nameEntry cannot already be found in list of events
    //              date must be valid date, with month being first 3 letters of valid month and
    //              day being a day of the applicable month
    // MODIFIES:    this
    // EFFECTS:     adds an event created by the user to our event list
    public void addEvent(Event event) {
        listOfEvents.add(event);
    }

    // REQUIRES: searchName is the name of an event in our list of events
    // EFFECTS: returns the index of the event list that the user wishes to search
    public int findEventIndex(String searchName) {
        int index = 0;
        for (Event event : listOfEvents) {
            String name = event.getName();
            if (name.equals(searchName)) {
                break;
            }
            index++;
        }
        return index;
    }

    // REQUIRES: month input is the first 3 letters of a month in a calendar
    // EFFECTS: returns true if the given day is an acceptable day of the given month, false if not
    //          (ex. returns true if given "Feb" and 15 but return false given "Feb" and 30)
    public boolean isValidDayInput(String monthInput, int dayInput) {
        monthInput = monthInput.toLowerCase();
        boolean valid = false;
        if (monthInput.equals("feb")) {
            if (dayInput > 0 && dayInput <= 29) {
                valid = true;
            }
        } else if (monthInput.equals("apr")
                || monthInput.equals("jun")
                || monthInput.equals("sep")
                || monthInput.equals("nov")) {
            if (dayInput > 0 && dayInput <= 30) {
                valid = true;
            }
        } else if (dayInput > 0 && dayInput <= 31) {
            valid = true;
        }
        return valid;
    }

    // EFFECTS: produces true if the given month input is the first 3 letters of a month name
    public boolean isValidMonthInput(String monthInput) {
        monthInput = monthInput.toLowerCase();
        return monthInput.equals("jan")
                || monthInput.equals("feb")
                || monthInput.equals("mar")
                || monthInput.equals("apr")
                || monthInput.equals("may")
                || monthInput.equals("jun")
                || monthInput.equals("jul")
                || monthInput.equals("aug")
                || monthInput.equals("sep")
                || monthInput.equals("oct")
                || monthInput.equals("nov")
                || monthInput.equals("dec");
    }

    // EFFECTS: transforms event list in JSON file
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("events", eventsToJson());
        return json;
    }

    // EFFECTS: returns events in event list as JSON array
    private JSONArray eventsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Event e : listOfEvents) {
            jsonArray.put(e.toJson());
        }

        return jsonArray;
    }


    // REQUIRES:    month input is the first three letters of a month on the calendar
    //              day input is an appropriate date for the given month
    // EFFECTS:     takes given year, month, and date and returns the event date in the
    //              correct format (ex. "Feb 12, 2022")
    public String makeEventDate(int yearInput, String monthInput, int dayInput) {
        String eventDate = (monthInput.substring(0,1).toUpperCase()
                + monthInput.substring(1,3).toLowerCase() + " " + dayInput + ", " + yearInput);
        return eventDate;
    }

    public List<Event> getEventsList() {
        return listOfEvents;
    }

    public List<String> getEventNames() {
        List<String> eventNames = new ArrayList<>();
        for (Event event : listOfEvents) {
            eventNames.add(event.getName());
        }
        return eventNames;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
