package model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class EventLog implements Iterable<Event> {
    private static EventLog theLog;
    private Collection<Event> events;

    // EFFECTS: constructs an event log (may only happen once from Singleton Design
    // pattern)
    private EventLog() {
        events = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: constructs the event log if it has not yet been created, then returns event log
    public static EventLog getInstance() {
        if (theLog == null) {
            theLog = new EventLog();
        }
        return theLog;
    }

    // MODIFIES: this
    // EFFECTS: adds an event to the Event log
    public void logEvent(Event e) {
        events.add(e);
    }

    @Override
    public Iterator<Event> iterator() {
        return events.iterator();
    }
}
