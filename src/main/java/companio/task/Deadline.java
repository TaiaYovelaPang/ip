package companio.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task that must be completed by a specific date and time.
 * <p>
 * A {@code Deadline} stores a description, a due {@link java.time.LocalDateTime}.
 * It allows Companio to track tasks that have clear deadlines.
 */
public class Deadline extends Task {
    protected LocalDateTime byWhen;

    public Deadline(String description, LocalDateTime byWhen) {
        super(description);
        this.byWhen = byWhen;
    }

    public Deadline(String description, boolean isDone, String byWhen) {
        super(description, isDone);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        this.byWhen = LocalDateTime.parse(byWhen, formatter);
    }

    @Override
    public LocalTime getTime() {
        return this.byWhen.toLocalTime();
    }

    @Override
    public LocalDate getDate() {
        return this.byWhen.toLocalDate();
    }

    @Override
    public String toString() {
        DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern("dd MMM yyyy, h:mma");
        return "[D]" + super.toString() + "(by: " + byWhen.format(outputFormat) + ")";
    }

    @Override
    public String toSave() {
        DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return "D|" + super.toSave() + "|" + byWhen.format(outputFormat);
    }
}
