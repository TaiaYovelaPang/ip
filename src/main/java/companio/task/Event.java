package companio.task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    protected LocalDate date;
    protected LocalTime startTime;
    protected LocalTime endTime;

    public Event(String description, LocalDate date, LocalTime startTime, LocalTime endTime) {
        super(description);
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Event(String description, boolean isDone, String date, String startTime, String endTime) {
        super(description, isDone);
        this.date = LocalDate.parse(date, DateTimeFormatter.ISO_LOCAL_DATE);
        this.startTime = LocalTime.parse(startTime, DateTimeFormatter.ISO_LOCAL_TIME);
        this.endTime = LocalTime.parse(endTime, DateTimeFormatter.ISO_LOCAL_TIME);
    }

    @Override
    public String toString() {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd MMM yyyy");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("h:mma");
        return "[E]" + super.toString() + " (at: " + date.format(dateFormatter) + ", "
                + startTime.format(timeFormatter) + " to " + endTime.format(timeFormatter) + ")";
    }

    @Override
    public String toSave() {
        return "E|" + super.toSave() + "|" + this.date + "|" + this.startTime.toString() + "|" + this.endTime.toString();
    }
}
