package companio.command;

import companio.CompanioException;
import companio.CompanioExitException;
import companio.task.TaskList;
import companio.task.TaskStorage;

/**
 * This class deals with the bye command, and helps to close the chatbot.
 */
public class ByeCommand implements Command {
    @Override
    public String execute(TaskList tasks, TaskStorage storage) throws CompanioException {
        throw new CompanioExitException("Bye. Till next time!");
    }
}
