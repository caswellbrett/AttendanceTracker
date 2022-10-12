package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

public class EventListTest {
    EventList eventList;
    Event event;
    List<String> swimAttendees;
    Event swimPractice;

    List<String> funeralAttendees;
    Event funeral;

    @BeforeEach
    public void setup() {
        eventList = new EventList();

        swimAttendees = new ArrayList<>();
        swimAttendees.add("Liam");
        swimAttendees.add("Darren");
        swimAttendees.add("Matthew");
        swimPractice = new Event("Swim Practice", "Oct 12, 2022", swimAttendees);

        funeralAttendees = new ArrayList<>();
        funeral = new Event("Funeral", "Dec 25, 2030", funeralAttendees);


    }

    @Test
    public void constructorTest() {
        assertEquals(0, eventList.getEventsList().size());
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
    public void addEventTest() {
        eventList.addEvent(swimPractice);
        eventList.addEvent(funeral);

        assertEquals(2, eventList.getEventsList().size());
        assertEquals("Swim Practice", eventList.getEventsList().get(0).getName());
        assertEquals("Funeral", eventList.getEventsList().get(1).getName());

    }

    @Test
    public void isNameTakenTest() {
        eventList.addEvent(swimPractice);
        eventList.addEvent(funeral);

        assertTrue(eventList.isNameTaken("Swim Practice"));
        assertTrue(eventList.isNameTaken("Funeral"));
        assertFalse(eventList.isNameTaken("Halloween Party"));
    }

    @Test
    public void makeEventDateTest() {
        assertEquals("Jan 30, 1998", eventList.makeEventDate(1998, "jAN", 30));
        assertEquals("Feb 27, 2003", eventList.makeEventDate(2003, "Feb", 27));
        assertEquals("Mar 31, 1198", eventList.makeEventDate(1198, "MaR", 31));
    }

    @Test
    public void findEventIndexTest() {
        eventList.addEvent(swimPractice);
        eventList.addEvent(funeral);
        assertEquals(0, eventList.findEventIndex("Swim Practice"));
        assertEquals(1, eventList.findEventIndex("Funeral"));
    }
}
