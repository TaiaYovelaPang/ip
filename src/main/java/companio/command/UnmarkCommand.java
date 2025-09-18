package companio.command;

import companio.CompanioException;
import companio.task.Task;
import companio.task.TaskList;
import companio.task.TaskStorage;

import java.io.IOException;

public class UnmarkCommand implements Command {
    private final int index;

    public UnmarkCommand(String input) {
        this.index = Integer.parseInt(input.split(" ")[1]) - 1;
    }

    @Override
    public String execute(TaskList tasks, TaskStorage storage) throws IOException, CompanioException {
        try {
            Task task = tasks.get(index);
            task.markAsUndone();
            storage.save(tasks);
            return "Oops, one more undone task.\n    " + task;
        } catch (CompanioException e) {
            return "No such task found.";
        }
    }
}
