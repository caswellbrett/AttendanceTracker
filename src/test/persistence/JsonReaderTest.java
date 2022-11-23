package persistence;

import model.OccasionList;
import model.Occasion;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

// some code adapted from Carter, P (2021) JsonSerializationDemo (Version 20210307) [Source Code].
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

public class JsonReaderTest extends JsonTest {
    @Test
    void jsonReaderTestInvalidFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            OccasionList el = reader.read();
            fail("Did not throw an IO Exception.");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyEventList() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyEventList.json");
        try {
            OccasionList el = reader.read();
            assertEquals("Event List 1", el.getName());
            assertEquals(0, el.getOccasionsList().size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralEventList() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralEventList.json");
        List<String> attendees1 = new ArrayList<>();
        attendees1.add("Brett");
        attendees1.add("Bryce");
        attendees1.add("Sam");

        List<String> attendees2 = new ArrayList<>();
        attendees2.add("Maiya");
        try {
            OccasionList el = reader.read();
            assertEquals("Event List 1", el.getName());
            List<Occasion> occasions = el.getOccasionsList();
            assertEquals(2, occasions.size());
            toJsonTest("Practice", "October 25, 2005", attendees1, occasions.get(0));
            toJsonTest("Birthday", "November 2, 2022", attendees2, occasions.get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderEventListNoAttendees() {
        JsonReader reader = new JsonReader("./data/testReaderEventListNoAttendees.json");
        List<String> attendees1 = new ArrayList<>();

        List<String> attendees2 = new ArrayList<>();
        attendees2.add("Maiya");
        try {
            OccasionList el = reader.read();
            assertEquals("Event List 1", el.getName());
            List<Occasion> occasions = el.getOccasionsList();
            assertEquals(2, occasions.size());
            toJsonTest("Practice", "October 25, 2005", attendees1, occasions.get(0));
            toJsonTest("Birthday", "November 2, 2022", attendees2, occasions.get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }


}
