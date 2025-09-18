package companio.command;

import companio.CompanioException;
import companio.task.Task;
import companio.task.TaskList;
import companio.task.TaskStorage;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class ViewCommand implements Command {
    private final LocalDate date;

    public ViewCommand(String input) throws CompanioException {
        if (input.trim().equals("view")) {
            throw new CompanioException("view date not specified!");
        }
        String dateString = input.substring(5).trim();
        this.date = LocalDate.parse(dateString);
    }

    @Override
    public String execute(TaskList tasks, TaskStorage storage) {
        List<Task> schedule = tasks.view(date);
        if (schedule.isEmpty()) {
            return "Seems like your schedule is free today!";
        }
        return schedule.stream()
                .map(Task::toString)
                .collect(Collectors.joining("\n"));
    }
}
