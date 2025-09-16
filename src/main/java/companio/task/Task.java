package companio.task;

import java.time.LocalDate;
import java.time.LocalTime;

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

    public String getDescription() {
        return description;
    }

    public LocalTime getTime() {
        return null;
    }

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
