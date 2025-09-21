package companio.command;

import companio.CompanioException;
import companio.task.TaskList;
import companio.task.TaskStorage;
/**
 * This class helps to list out the task list.
 */
public class ListCommand implements Command {
    @Override
    public String execute(TaskList tasks, TaskStorage storage) throws CompanioException {
        if (tasks.size() == 0) {
            return "Your task list is empty!";
        }
        return tasks.listAsString();
    }
}
