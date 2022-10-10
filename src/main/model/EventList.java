package model;

import java.util.List;
import java.util.ArrayList;

//
public class EventList {
    private List<String> eventNameList = new ArrayList<>();
    private List<String> eventDateList = new ArrayList<>();
    private List<List<String>> eventAttendeesList = new ArrayList<>();

    // EFFECTS: instantiates an event list
    public void eventList() {

    }

    // EFFECTS: Returns true if the given event name is in our events list, false if not
    public boolean isNameTaken(String nameEntry) {
        return (eventNameList.contains(nameEntry));
    }

    // REQUIRES: nameEntry cannot already be found in eventNameList
    // MODIFIES: this
    // EFFECTS: adds an event name to our list of event names
    public void addEventName(String nameEntry) {
        eventNameList.add(nameEntry);
    }

    // REQUIRES:    monthInput must be the first three letters of the name of a calendar month (ex. "feb")
    //              dayInput must > 0 and a day that can be found in the given month (ex. < 31 for January)
    // MODIFIES:    this
    // EFFECTS:     adds the given year, month, and day to our list of event dates in the proper format
    //              (ex. "Jan 13, 2003")
    public void addEventDate(int yearInput, String monthInput, int dayInput) {
        String eventDate = (monthInput.substring(0,1).toUpperCase()
                + monthInput.substring(1,3).toLowerCase() + " " + dayInput + ", " + yearInput);
        eventDateList.add(eventDate);
    }

    // MODIFIES: this
    // EFFECTS: adds the list of attendees from a given event to the list of event attendees list
    public void addEventAttendees(ArrayList<String> eventAttendees) {
        eventAttendeesList.add(eventAttendees);
    }

    // REQUIRES: monthInput is the first 3 letters of a month in the calendar (ex. "Jun")
    // EFFECTS: produces true if the given day is appropriate for the given month, false if not
    //          (ex. produces true if 0 < day <= 31 for a date in January)
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

    // REQUIRES: searchName can be found in list of event names
    // EFFECTS: returns the index of the event list that the user wishes to search
    public int findEventIndex(String searchName) {
        int index = -1;
        for (int i = 0; i < eventNameList.size(); i++) {
            String name = eventNameList.get(i);
            if (name.equals(searchName)) {
                index = i;
                break;
            }
        }
        return index;
    }

    public String getEventName(int index) {
        return eventNameList.get(index);
    }

    public String getEventDate(int index) {
        return eventDateList.get(index);
    }

    public List<String> getEventAttendees(int index) {
        return eventAttendeesList.get(index);
    }

    public List<String> getEventNameList() {
        return eventNameList;
    }

    public List<String> getEventDateList() {
        return eventDateList;
    }

    public List<List<String>> getEventAttendeesList() {
        return eventAttendeesList;
    }
}
