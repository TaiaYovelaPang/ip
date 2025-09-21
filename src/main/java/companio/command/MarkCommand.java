package companio.command;

import companio.CompanioException;
import companio.task.Task;
import companio.task.TaskList;
import companio.task.TaskStorage;

import java.io.IOException;

/**
 * This class helps to mark tasks as completed. It also checks if the task exists.
 */
public class MarkCommand implements Command {
    private final int index;

    public MarkCommand(String input) {
        this.index = Integer.parseInt(input.split(" ")[1]) - 1;
    }

    @Override
    public String execute(TaskList tasks, TaskStorage storage) throws IOException, CompanioException {
        try {
            Task task = tasks.get(index);
            task.markAsDone();
            storage.save(tasks);
            return "Good job in completing a task!\n    " + task;
        } catch (CompanioException e) {
            // catch invalid index and give user-friendly response
            return "No such task found.";
        }
    }
}
