package companio.addtask;

import companio.CompanioException;
import companio.task.Task;
import companio.task.Event;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

/**
 * A class that helps Companio to handle new events added by the user.
 *
 * <p> It will ensure validity of the input given by the user and creates the event. </p>
 */

public class AddEvent {
    private String input;

    public AddEvent(String input) {
        this.input = input;
    }

    /**
     * Checks validity of the input given by user.
     * @throws CompanioException exception to catch missing description
     */
    public void checkInput() throws CompanioException {
        if (input.trim().equals("event")) {
            throw new CompanioException("event description is empty");
        }
        String[] strings = input.substring(6).split("/");
        if (strings.length < 4) {
            throw new CompanioException("event details not specified!");
        }
    }

    /**
     * Creates a new event task.
     * @return Event task
     * @throws CompanioException exception to handle date and timing errors
     */
    public Task create() throws CompanioException {
        String[] strings = input.substring(6).split("/");
        LocalDate date;
        try {
            date = LocalDate.parse(strings[1].trim()); // ISO format expected: yyyy-MM-dd
        } catch (DateTimeParseException e) {
            throw new CompanioException("Invalid date format! Use yyyy-MM-dd (e.g., 2025-08-30).");
        }
        LocalTime startTime;
        try {
            startTime = LocalTime.parse(strings[2].trim());
        } catch (DateTimeParseException e) {
            throw new CompanioException("Invalid time format! Use HH:mm (e.g., 18:25).");
        }
        LocalTime endTime;
        try {
            endTime = LocalTime.parse(strings[3].trim());
        } catch (DateTimeParseException e) {
            throw new CompanioException("Invalid time format! Use HH:mm (e.g., 18:25).");
        }
        if (!endTime.isAfter(startTime)) {
            throw new CompanioException("AHHH end time must be after start time!");
        }
        if (date.isBefore(LocalDate.now())) {
            throw new CompanioException("Oh man, I cannot create a past event :(");
        }
        if (date.isEqual(LocalDate.now()) && endTime.isBefore(LocalTime.now())) {
            throw new CompanioException("Oh man, I cannot create a past event :(");
        }
        Task task = new Event(strings[0], date, startTime, endTime);
        return task;
    }
}
