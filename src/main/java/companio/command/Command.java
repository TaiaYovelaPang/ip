package companio.command;

import companio.CompanioException;
import companio.task.TaskList;
import companio.task.TaskStorage;

import java.io.IOException;

public interface Command {
    /**
     * Executes the command.
     *
     * @param tasks    the current task list
     * @param storage  the storage used for saving/loading tasks
     * @return the response message to display to the user
     * @throws IOException if saving/loading fails
     * @throws CompanioException if a command-specific error occurs
     */
    String execute(TaskList tasks, TaskStorage storage) throws IOException, CompanioException;
}


