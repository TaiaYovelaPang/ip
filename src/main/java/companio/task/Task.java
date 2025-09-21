package companio.task;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Represents a general task that can be managed by Companio.
 * <p>
 * A {@code Task} serves as the base class for all specific task types, including:
 * <ul>
 *     <li>{@link companio.task.ToDo} – a simple task with only a description</li>
 *     <li>{@link companio.task.Deadline} – a task that must be completed by a specific date and time</li>
 *     <li>{@link companio.task.Event} – a task that occurs within a scheduled time range</li>
 * </ul>
 * Each task has a description and a completion status (done or not done). Subclasses
 * extend this class to add additional properties such as deadlines or event durations.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Creates a task specified with status of the task automatically set to not done.
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Creates a task specified.
     * @param description The description of the task.
     * @param isDone A boolean reflecting the status of the task.
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Gets status of the task.
     * @return "X" or " " depending on whether the task is done or not.
     */
    public String getStatus() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Gets description of the task.
     * @return the description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns the time associated with this task, if any.
     * <p>
     * By default, {@code Task} does not have a time, so this method returns {@code null}.
     * Subclasses such as {@link companio.task.Deadline} and {@link companio.task.Event}
     * override this method to provide meaningful values.
     *
     * @return the {@link java.time.LocalTime} of this task, or {@code null} if not applicable
     */
    public LocalTime getTime() {
        return null;
    }

    /**
     * Returns the date associated with this task, if any.
     * <p>
     * By default, {@code Task} does not have a date, so this method returns {@code null}.
     * Subclasses such as {@link companio.task.Deadline} and {@link companio.task.Event}
     * override this method to provide meaningful values.
     *
     * @return the {@link java.time.LocalDate} of this task, or {@code null} if not applicable
     */
    public LocalDate getDate() {
        return null;
    }

    /**
     * Marks a task as done by changing isDone to true.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Marks a task as not done by changing isDone to false.
     */
    public void markAsUndone() {
        isDone = false;
    }

    /**
     * Returns what is to be printed out by the chatbot.
     * @return A string showing the status and description of the task.
     */
    public String toString() {
        return "[" + this.getStatus() + "] " + description;
    }

    /**
     * Returns what is to be saved to the hard disk.
     * @return A string showing the status and description of the task.
     */
    public String toSave() {
        return this.getStatus() + "|" + this.description;
    }
}
