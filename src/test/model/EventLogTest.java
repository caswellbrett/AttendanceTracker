package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

public class EventLogTest {
    private EventLog theLog;

    @BeforeEach
    public void setup() {
        theLog = EventLog.getInstance();
        theLog.logEvent(new Event("Event1"));
        theLog.logEvent(new Event("Event2"));
    }

    @Test
    public void iteratorTest() {
        List<Event> events = new ArrayList<>();
        for (Event event : theLog) {
            events.add(event);
        }
        assertEquals("Event1", events.get(0).getDescription());
        assertEquals("Event2", events.get(1).getDescription());
        assertEquals(2, events.size());
    }

}
