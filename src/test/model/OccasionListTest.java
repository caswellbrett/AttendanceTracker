package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

public class OccasionListTest {
    OccasionList occasionList;
    List<String> swimAttendees;
    Occasion swimPractice;

    List<String> funeralAttendees;
    Occasion funeral;

    @BeforeEach
    public void setup() {
        occasionList = new OccasionList("Event List 1");

        swimAttendees = new ArrayList<>();
        swimAttendees.add("Liam");
        swimAttendees.add("Darren");
        swimAttendees.add("Matthew");
        swimPractice = new Occasion("Swim Practice", "Oct 12, 2022", swimAttendees);

        funeralAttendees = new ArrayList<>();
        funeral = new Occasion("Funeral", "Dec 25, 2030", funeralAttendees);


    }

    @Test
    public void constructorTest() {
        assertEquals(0, occasionList.getOccasionsList().size());
    }

    @Test
    public void isValidMonthInputTest() {
        assertFalse(occasionList.isValidMonthInput(""));
        assertFalse(occasionList.isValidMonthInput("march"));
        assertFalse(occasionList.isValidMonthInput("mai"));
        assertTrue(occasionList.isValidMonthInput("JAN"));
        assertTrue(occasionList.isValidMonthInput("feB"));
        assertTrue(occasionList.isValidMonthInput("mAr"));
        assertTrue(occasionList.isValidMonthInput("Apr"));
        assertTrue(occasionList.isValidMonthInput("may"));
        assertTrue(occasionList.isValidMonthInput("JUn"));
        assertTrue(occasionList.isValidMonthInput("JuL"));
        assertTrue(occasionList.isValidMonthInput("aUG"));
        assertTrue(occasionList.isValidMonthInput("SEp"));
        assertTrue(occasionList.isValidMonthInput("oCT"));
        assertTrue(occasionList.isValidMonthInput("NOV"));
        assertTrue(occasionList.isValidMonthInput("dec"));
    }

    @Test
    public void isValidDayInputTestFalse() {
        assertFalse(occasionList.isValidDayInput("feb",-10));
        assertFalse(occasionList.isValidDayInput("feB",0));
        assertFalse(occasionList.isValidDayInput("FEb",30));

        assertFalse(occasionList.isValidDayInput("Apr",-10));
        assertFalse(occasionList.isValidDayInput("juN",0));
        assertFalse(occasionList.isValidDayInput("sep",31));

        assertFalse(occasionList.isValidDayInput("jan",-10));
        assertFalse(occasionList.isValidDayInput("MAR",0));
        assertFalse(occasionList.isValidDayInput("May",32));
    }

    @Test
    public void isValidDayInputTestTrue() {
        assertTrue(occasionList.isValidDayInput("feb",1));
        assertTrue(occasionList.isValidDayInput("FEB",29));

        assertTrue(occasionList.isValidDayInput("nov",1));
        assertTrue(occasionList.isValidDayInput("apR",30));

        assertTrue(occasionList.isValidDayInput("juL",1));
        assertTrue(occasionList.isValidDayInput("AUg",31));
    }

    @Test
    public void addEventTest() {
        occasionList.addOccasion(swimPractice);
        occasionList.addOccasion(funeral);

        assertEquals(2, occasionList.getOccasionsList().size());
        assertEquals("Swim Practice", occasionList.getOccasionsList().get(0).getName());
        assertEquals("Funeral", occasionList.getOccasionsList().get(1).getName());

    }

    @Test
    public void isNameTakenTest() {
        occasionList.addOccasion(swimPractice);
        occasionList.addOccasion(funeral);

        assertTrue(occasionList.isNameTaken("Swim Practice"));
        assertTrue(occasionList.isNameTaken("Funeral"));
        assertFalse(occasionList.isNameTaken("Halloween Party"));
    }

    @Test
    public void makeEventDateTest() {
        assertEquals("Jan 30, 1998", occasionList.makeOccasionDate(1998, "jAN", 30));
        assertEquals("Feb 27, 2003", occasionList.makeOccasionDate(2003, "Feb", 27));
        assertEquals("Mar 31, 1198", occasionList.makeOccasionDate(1198, "MaR", 31));
    }

    @Test
    public void findEventIndexTest() {
        assertEquals(0, occasionList.findOccasionIndex("Swim Practice"));

        occasionList.addOccasion(swimPractice);
        occasionList.addOccasion(funeral);
        assertEquals(0, occasionList.findOccasionIndex("Swim Practice"));
        assertEquals(1, occasionList.findOccasionIndex("Funeral"));

        assertEquals(2, occasionList.findOccasionIndex("Party"));
    }

    @Test
    public void setNameTest() {
        occasionList.setName("Event List 2");
        assertEquals("Event List 2", occasionList.getName());
    }
}
