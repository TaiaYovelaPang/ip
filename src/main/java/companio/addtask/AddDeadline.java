package companio.addtask;

import companio.CompanioException;
import companio.task.Task;
import companio.task.Deadline;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * A class that helps Companio to handle new deadlines added by the user.
 *
 * <p> It will ensure validity of the input given by the user and creates the deadline. </p>
 */

public class AddDeadline {
    private String input;

    public AddDeadline(String input) {
        this.input = input;
    }

    /**
     * Checks validity of the input given by user.
     * @throws CompanioException exception to catch missing description
     */
    public void checkInput() throws CompanioException {
        if (input.trim().equals("deadline")) {
            throw new CompanioException("deadline description is empty");
        }
        String[] strings = input.substring(9).split("/");
        if (strings.length < 2) {
            throw new CompanioException("missing deadline for task!");
        }
    }

    /**
     * Creates a new deadline task.
     * @return Deadline task
     * @throws CompanioException exception to catch deadline errors
     */
    public Task create() throws CompanioException {
        LocalDateTime deadline;
        String[] strings = input.substring(9).split("/");
        try {
            DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            deadline = LocalDateTime.parse(strings[1].trim(), inputFormat);
        } catch (DateTimeParseException e) {
            throw new CompanioException("Invalid deadline format! Use yyyy-MM-dd HHmm (e.g., 2025-08-30 18:25).");
        }
        if (deadline.isBefore(LocalDateTime.now())) {
            throw new CompanioException("Oh no, your deadline seems to have passed :0");
        }
        Task task = new Deadline(strings[0], deadline);
        return task;
    }
}
