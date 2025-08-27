package companio.task;

public class Task {
    protected String description;
    protected boolean isDone;

    //Creation of a task
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public String getStatus() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    //Marking a task as done
    public void done() {
        isDone = true;
    }

    //Marking a task as undone
    public void undone() {
        isDone = false;
    }

    public String toString() {
        return "[" + this.getStatus() + "] " + description;
    }

    public String toSave() {
        return this.getStatus() + "|" + this.description;
    }
}
