public class Deadline extends Task {
    protected String byWhen;

    public Deadline(String description, String byWhen) {
        super(description);
        this.byWhen = byWhen;
    }

    public Deadline(String description, boolean isDone, String byWhen) {
        super(description, isDone);
        this.byWhen = byWhen;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(" + byWhen + ")";
    }

    @Override
    public String toSave() {
        return "D|" + super.toSave() + "|" + this.byWhen;
    }
}
