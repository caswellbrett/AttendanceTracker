package ui;

import model.Event;
import model.EventList;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;

// the console interface of the Attendance List project
public class AttendanceListConsole {
    private EventList userList = new EventList();
    private Scanner scanner = new Scanner(System.in);
    private String nameToAdd;
    private String dateToAdd;
    private List<String> attendeesToAdd;

    // EFFECTS: runs console interface and welcomes user to Attendance Tracker
    public AttendanceListConsole() {
        System.out.println("Welcome to Attendance Tracker, "
                + "where you can track attendance lists for all your events!");
        runConsole();
    }

    // MODIFIES: this
    // EFFECTS: Displays options to user and processes the user's response
    //          continuously until user asks to quit attendance tracker.
    //          Thanks user when they quit the program.
    public void runConsole() {
        boolean addMore = true;

        while (addMore) {
            presentOptions();
            String chosenOption = scanner.next();
            if (chosenOption.equals("a")) {
                addEventName();
                addEventDate();
                addEventAttendees();
                Event userEvent = new Event(nameToAdd, dateToAdd, attendeesToAdd);
                userList.addEvent(userEvent);
                System.out.println("Event added!");
            } else if (chosenOption.equals("s")) {
                searchEvent();
            } else if (chosenOption.equals("e")) {
                addMore = false;
            } else {
                System.out.println("Invalid selection. Please try again.");
            }
        }

        System.out.println("Thank you for using Attendance Tracker!");
    }

    // MODIFIES: this
    // EFFECTS: searches for event in the event list and prints its name, date,
    //          and list of attendees if the event is found (prints other message if not found)
    private void searchEvent() {
        System.out.println("Please enter the name of the event you wish to search: ");
        String searchName = scanner.next();
        if (userList.isNameTaken(searchName)) {
            int index = userList.findEventIndex(searchName);
            System.out.println("Event name: " + userList.getEventsList().get(index).getName());
            System.out.println("Event date: " + userList.getEventsList().get(index).getDate());
            System.out.println("Event attendees:");
            List<String> attendees = userList.getEventsList().get(index).getAttendees();
            for (String attendee : attendees) {
                System.out.println(attendee);
            }
        } else {
            System.out.println("Event not found in event list.");
        }
    }

    // MODIFIES: this
    // EFFECTS: asks for name of event to add to list and asks again if event name already taken
    private void addEventName() {
        System.out.println("Please add a name for the event you wish to add: ");
        String nameEntry = scanner.next();
        if (!userList.isNameTaken(nameEntry)) {
            nameToAdd = nameEntry;
        } else {
            System.out.println("The name entered is either too short or "
                    + "already exists in your events list. Please try again.");
            addEventName();
        }
    }

    // MODIFIES: this
    // EFFECTS: asks for year, month, and day of the event the user wishes to add.
    //          asks again if given date is invalid
    public void addEventDate() {
        System.out.println("Please add the year when the event took place (ex. \"2003\"):");
        int yearInput = scanner.nextInt();
        System.out.println("Please add the first three letters of the month then the event "
                + "took place (ex. \"Feb\" for February):");
        String monthInput = scanner.next();
        System.out.println("Please add the day of then month when the event took place (ex. \"13\"):");
        int dayInput = scanner.nextInt();
        if (userList.isValidDayInput(monthInput, dayInput) && userList.isValidMonthInput(monthInput)) {
            dateToAdd = userList.makeEventDate(yearInput, monthInput, dayInput);
        } else {
            System.out.println("Invalid input. Please try again.");
            addEventDate();
        }
    }

    // MODIFIES: this
    // EFFECTS: generates a list of attendees for an event and adds the list to the list of events' attendees
    public void addEventAttendees() {
        System.out.println("Add event attendees one-by-one. When you are done, type \"done\":");
        ArrayList<String> attendees = new ArrayList<>();
        boolean notDone = true;
        while (notDone) {
            System.out.println("Add an attendee:");
            String attendee = scanner.next();
            if (attendee.toLowerCase().equals("done")) {
                notDone = false;
            } else {
                attendees.add(attendee);
            }
        }
        attendeesToAdd = attendees;
    }

    // MODIFIES: this
    // EFFECTS: prints options for user one opening Attendance Tracker, completing all steps for one of the options
    public void presentOptions() {
        System.out.println("To add an event, please enter \"a\"");
        System.out.println("To search for an event, please enter \"s\"");
        System.out.println("To exit the program, please enter \"e\"");
    }

}
