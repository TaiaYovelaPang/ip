package companio.task;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {

    @Test
    public void testToString() {
        Event event = new Event("Carnival",LocalDate.of(2025, 1, 10),
                LocalTime.of(12, 15),LocalTime.of(15, 30));
        String expected = "[E][ ] Carnival (at: 10 Jan 2025, 12:15pm to 3:30pm)";
        assertEquals(expected, event.toString());
    }
}
