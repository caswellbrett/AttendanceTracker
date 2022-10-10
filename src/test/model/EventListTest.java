package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

public class EventListTest {
    EventList eventList;

    @BeforeEach
    public void setup() {
        eventList = new EventList();
    }

    @Test
    public void constructorTest() {
        EventList newEventList = new EventList();
        List<String> eventNames = newEventList.getEventNameList();
        List<String> eventDates = newEventList.getEventDateList();
        List<List<String>> eventAttendeesList = newEventList.getEventAttendeesList();
        assertEquals(0, eventNames.size());
        assertEquals(0,eventDates.size());
        assertEquals(0,eventAttendeesList.size());
    }

    @Test
    public void isValidMonthInputTest() {
        assertFalse(eventList.isValidMonthInput(""));
        assertFalse(eventList.isValidMonthInput("march"));
        assertFalse(eventList.isValidMonthInput("mai"));
        assertTrue(eventList.isValidMonthInput("JAN"));
        assertTrue(eventList.isValidMonthInput("feB"));
        assertTrue(eventList.isValidMonthInput("mAr"));
        assertTrue(eventList.isValidMonthInput("Apr"));
        assertTrue(eventList.isValidMonthInput("may"));
        assertTrue(eventList.isValidMonthInput("JUn"));
        assertTrue(eventList.isValidMonthInput("JuL"));
        assertTrue(eventList.isValidMonthInput("aUG"));
        assertTrue(eventList.isValidMonthInput("SEp"));
        assertTrue(eventList.isValidMonthInput("oCT"));
        assertTrue(eventList.isValidMonthInput("NOV"));
        assertTrue(eventList.isValidMonthInput("dec"));
    }

    @Test
    public void isValidDayInputTestFalse() {
        assertFalse(eventList.isValidDayInput("feb",-10));
        assertFalse(eventList.isValidDayInput("feB",0));
        assertFalse(eventList.isValidDayInput("FEb",30));

        assertFalse(eventList.isValidDayInput("Apr",-10));
        assertFalse(eventList.isValidDayInput("juN",0));
        assertFalse(eventList.isValidDayInput("sep",31));

        assertFalse(eventList.isValidDayInput("jan",-10));
        assertFalse(eventList.isValidDayInput("MAR",0));
        assertFalse(eventList.isValidDayInput("May",32));
    }

    @Test
    public void isValidDayInputTestTrue() {
        assertTrue(eventList.isValidDayInput("feb",1));
        assertTrue(eventList.isValidDayInput("FEB",29));

        assertTrue(eventList.isValidDayInput("nov",1));
        assertTrue(eventList.isValidDayInput("apR",30));

        assertTrue(eventList.isValidDayInput("juL",1));
        assertTrue(eventList.isValidDayInput("AUg",31));
    }

    @Test
    public void addEventDateTest() {
        eventList.addEventDate(1998,"jAN",30);
        eventList.addEventDate(2003,"Feb",27);
        eventList.addEventDate(1198,"MaR",31);
        String janDate = eventList.getEventDate(0);
        String febDate = eventList.getEventDate(1);
        String marDate = eventList.getEventDate(2);
        assertEquals("Jan 30, 1998", janDate);
        assertEquals("Feb 27, 2003", febDate);
        assertEquals("Mar 31, 1198", marDate);
    }

    @Test
    public void addEventNameTest() {
        eventList.addEventName("Swim Practice");
        List<String> names = eventList.getEventNameList();
        assertEquals(1, names.size());
        String name = eventList.getEventName(0);
        assertEquals("Swim Practice", name);

        eventList.addEventName("Sara's Party");
        names = eventList.getEventNameList();
        assertEquals(2, names.size());
        name = eventList.getEventName(1);
        assertEquals("Sara's Party", name);
    }

    @Test
    public void isNameTakenTest() {
        eventList.addEventName("Swim Practice");
        eventList.addEventName("Broadway Show");
        assertTrue(eventList.isNameTaken("Swim Practice"));
        assertTrue(eventList.isNameTaken("Broadway Show"));
        assertFalse(eventList.isNameTaken("Soccer Game"));
        eventList.addEventName("Soccer Game");
        assertTrue(eventList.isNameTaken("Soccer Game"));
    }

    @Test
    public void addEventAttendeesTest() {
        ArrayList<String> attendees1 = new ArrayList<>();
        attendees1.add("Bryce");
        attendees1.add("Sara");
        attendees1.add("Jean");
        eventList.addEventAttendees(attendees1);
        List<List<String>> attendeesLists = eventList.getEventAttendeesList();
        assertEquals(1, attendeesLists.size());
        List<String> eventAttendees1 = eventList.getEventAttendees(0);
        assertEquals("Bryce", eventAttendees1.get(0));

        ArrayList<String> attendees2 = new ArrayList<>();
        attendees2.add("Stuart");
        attendees2.add("Ben");
        eventList.addEventAttendees(attendees2);
        attendeesLists = eventList.getEventAttendeesList();
        assertEquals(2, attendeesLists.size());
        List<String> eventAttendees2 = eventList.getEventAttendees(1);
        assertEquals("Ben", eventAttendees2.get(1));
    }

    @Test
    public void findEventIndexTest() {
        eventList.addEventName("Charity Event");
        eventList.addEventName("Swim Practice");
        eventList.addEventName("Camp Retreat");
        assertEquals(0, eventList.findEventIndex("Charity Event"));
        assertEquals(1, eventList.findEventIndex("Swim Practice"));
        assertEquals(2, eventList.findEventIndex("Camp Retreat"));
    }



}
