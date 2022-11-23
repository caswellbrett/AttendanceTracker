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

public class JsonWriterTest extends JsonTest {
    @Test
    void openJsonWriterTestInvalidFile() {
        try {
            OccasionList el = new OccasionList("Event List 1");
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
            OccasionList el = new OccasionList("Event List 1");
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyEventList.json");
            writer.open();
            writer.write(el);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyEventList.json");
            el = reader.read();
            assertEquals("Event List 1", el.getName());
            assertEquals(0, el.getOccasionsList().size());
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
            OccasionList el = new OccasionList("Event List 1");
            el.addOccasion(new Occasion("Practice", "October 25, 2005", attendees1));
            el.addOccasion(new Occasion("Birthday", "November 2, 2022", attendees2));
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralEventList.json");
            writer.open();
            writer.write(el);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralEventList.json");
            el = reader.read();
            assertEquals("Event List 1", el.getName());
            List<Occasion> occasions = el.getOccasionsList();
            assertEquals(2, occasions.size());
            toJsonTest("Practice", "October 25, 2005", attendees1, occasions.get(0));
            toJsonTest("Birthday", "November 2, 2022", attendees2, occasions.get(1));

        } catch (IOException e) {
            fail("IOException incorrectly thrown.");
        }
    }

    @Test
    void testWriterEventListNoAttendees() {
        List<String> attendees1 = new ArrayList<>();

        List<String> attendees2 = new ArrayList<>();
        attendees2.add("Maiya");
        try {
            OccasionList el = new OccasionList("Event List 1");
            el.addOccasion(new Occasion("Practice", "October 25, 2005", attendees1));
            el.addOccasion(new Occasion("Birthday", "November 2, 2022", attendees2));
            JsonWriter writer = new JsonWriter("./data/testWriterEventListNoAttendees.json");
            writer.open();
            writer.write(el);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEventListNoAttendees.json");
            el = reader.read();
            assertEquals("Event List 1", el.getName());
            List<Occasion> occasions = el.getOccasionsList();
            assertEquals(2, occasions.size());
            toJsonTest("Practice", "October 25, 2005", attendees1, occasions.get(0));
            toJsonTest("Birthday", "November 2, 2022", attendees2, occasions.get(1));

        } catch (IOException e) {
            fail("IOException incorrectly thrown.");
        }
    }

}
