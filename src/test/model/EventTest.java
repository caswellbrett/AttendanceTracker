package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

public class EventTest {
    Event swimPractice;
    List<String> swimPracticeAttendees = new ArrayList<>();
    Event funeral;
    List<String> funeralAttendees = new ArrayList<>();

    @BeforeEach
    public void setup() {
        swimPracticeAttendees.add("Claire");
        swimPracticeAttendees.add("Megan");
        swimPracticeAttendees.add("Maelle");
        swimPractice = new Event("Swim Practice", "Oct 12, 2022", swimPracticeAttendees);

        funeral = new Event("Funeral", "Dec 25, 2030", funeralAttendees);
    }

    @Test
    public void eventConstructorTest() {
        assertEquals("Swim Practice", swimPractice.getName());
        assertEquals("Oct 12, 2022", swimPractice.getDate());
        assertEquals(3, swimPractice.getAttendees().size());
        assertEquals("Claire", swimPractice.getAttendees().get(0));
        assertEquals("Megan", swimPractice.getAttendees().get(1));
        assertEquals("Maelle", swimPractice.getAttendees().get(2));

        assertEquals("Funeral", funeral.getName());
        assertEquals("Dec 25, 2030", funeral.getDate());
        assertEquals(0, funeral.getAttendees().size());
    }
}
