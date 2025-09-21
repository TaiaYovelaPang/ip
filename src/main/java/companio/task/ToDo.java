package companio.task;

/**
 * Represents a task without any specific date or time constraints.
 * <p>
 * A {@code Todo} is the simplest type of task in Companio — it only stores a description
 * and can be marked as done or not done. Unlike {@link Deadline} or {@link Event}, it does
 * not carry any date or time information.
 */
public class ToDo extends Task {

    public ToDo(String description) {
        super(description);
    }

    public ToDo(String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String toSave() {
        return "T|" + super.toSave();
    }
}
