package companio.command;

import companio.CompanioException;
import companio.task.Task;
import companio.task.TaskList;
import companio.task.TaskStorage;

import java.io.IOException;

/**
 * This class helps to unmark completed tasks. It also checks if the task exists.
 */
public class UnmarkCommand implements Command {
    private final int index;

    public UnmarkCommand(String input) throws CompanioException {
        String[] parts = input.split(" ");
        if (parts.length < 2) {
            throw new CompanioException("Invalid formatting! Follow example: unmark 2");
        }
        this.index = Integer.parseInt(parts[1]) - 1;
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
