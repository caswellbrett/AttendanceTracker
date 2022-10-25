package persistence;

import model.EventList;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import model.Event;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

// some code adapted from Carter, P (2021) JsonSerializationDemo (Version 20210307) [Source Code].
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

public class JsonWriterTest extends JsonTest {
    @Test
    void openJsonWriterTestInvalidFile() {
        try {
            EventList el = new EventList("Event List 1");
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was not thrown.");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyEventList() {
        try {
            EventList el = new EventList("Event List 1");
            JsonWriter writer = new JsonWriter("./data/testReaderEmptyEventList.json");
            writer.open();
            writer.write(el);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyEventList.json");
            el = reader.read();
            assertEquals("Event List 1", el.getName());
            assertEquals(0, el.getEventsList().size());
        } catch (IOException e) {
            fail("IOException incorrectly thrown.");
        }
    }

    @Test
    void testWriterGeneralEventList() {
        List<String> attendees1 = new ArrayList<>();
        attendees1.add("Brett");
        attendees1.add("Bryce");
        attendees1.add("Sam");

        List<String> attendees2 = new ArrayList<>();
        attendees2.add("Maiya");
        try {
            EventList el = new EventList("Event List 1");
            el.addEvent(new Event("Practice", "October 25, 2005", attendees1));
            el.addEvent(new Event("Birthday", "November 2, 2022", attendees2));
            JsonWriter writer = new JsonWriter("./data/testReaderGeneralEventList.json");
            writer.open();
            writer.write(el);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralEventList.json");
            el = reader.read();
            assertEquals("Event List 1", el.getName());
            List<Event> events = el.getEventsList();
            assertEquals(2, events.size());
            toJsonTest("Practice", "October 25, 2005", attendees1, events.get(0));
            toJsonTest("Birthday", "November 2, 2022", attendees2, events.get(1));

        } catch (IOException e) {
            fail("IOException incorrectly thrown.");
        }
    }
}
