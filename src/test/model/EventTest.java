package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class EventTest {
    private Event event1;
    private Event event2;

    @BeforeEach
    public void setup() {
        event1 = new Event("This is a test event.");
        event2 = new Event("This is a different event.");
    }

    @Test
    public void eventConstructorTest() {
        Date date = Calendar.getInstance().getTime();
        assertEquals("This is a test event.", event1.getDescription());
        assertTrue(event1.getDate().getClass() == date.getClass());
    }

    @Test
    public void eventToStringTest() {
        assertEquals(event1.getDescription() + event1.getDate(),
                event1.toString());
    }
}
