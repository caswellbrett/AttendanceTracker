package persistence;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.List;
import model.Occasion;

// some code adapted from Carter, P (2021) JsonSerializationDemo (Version 20210307) [Source Code].
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
public class JsonTest {
    protected void toJsonTest(String name, String date, List<String> attendees, Occasion occasion) {
        assertEquals(name, occasion.getName());
        assertEquals(date, occasion.getDate());
        assertEquals(attendees, occasion.getAttendees());
    }
}
