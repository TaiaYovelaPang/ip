package companio.command;

import companio.CompanioException;
import companio.task.Task;
import companio.task.TaskList;
import companio.task.TaskStorage;

import java.io.IOException;

public class DeleteCommand implements Command {
    private final int index;

    public DeleteCommand(String input) {
        this.index = Integer.parseInt(input.split(" ")[1]) - 1;
    }

    @Override
    public String execute(TaskList tasks, TaskStorage storage) throws IOException, CompanioException {
        try {
            Task removed = tasks.delete(index);
            storage.save(tasks);
            return "Yay! One task removed!\n    " + removed + "\nNumber of tasks: " + tasks.size();
        } catch (CompanioException e) {
            return "No such task found.";
        }
    }
}
