package companio.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTest {

    @Test
    public void testToSave() {
        ToDo todo = new ToDo("Exercise");
        String expected = "T| |Exercise";
        assertEquals(expected, todo.toSave());
    }
}
