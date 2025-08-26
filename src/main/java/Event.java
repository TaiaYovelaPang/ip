public class Event extends Task {

    protected String details;

    public Event(String description, String details) {
        super(description);
        this.details = details;
    }

    public Event(String description, boolean isDone, String details) {
        super(description, isDone);
        this.details = details;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(" + details + ")";
    }

    @Override
    public String toSave() {
        return "E|" + super.toSave() + "|" + this.details;
    }
}
