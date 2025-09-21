package companio.command;

import companio.task.TaskList;
import companio.task.TaskStorage;

/**
 * A command representing an error.
 * When executed, it simply returns the error message to be displayed.
 */
public class ErrorCommand implements Command {

    private final String errorMessage;

    public ErrorCommand(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public String execute(TaskList tasks, TaskStorage storage) {
        return errorMessage;
    }
}
