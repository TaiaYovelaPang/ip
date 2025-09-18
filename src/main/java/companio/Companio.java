package companio;

import companio.command.Command;
import companio.task.TaskList;
import companio.task.TaskStorage;

import java.io.IOException;

/**
 * The main class for Companio chatbot.
 *
 * <p> Companio is a personal assistant chatbot that helps users keep track of
 * different types of tasks. </p>
 *
 * <p> Types of tasks: todo, deadline, event </p>
 *
 * <p> Methods supported: add, delete, mark, unmark, list </p>
 */

public class Companio {
    private final TaskStorage storage = new TaskStorage("./data/companio.txt");
    private final TaskList tasks;

    public Companio() throws IOException, CompanioException {
        this.tasks = storage.loadTaskList();
    }

    /**
     * Handles the input given by the user by calling the respective methods.
     *
     * @param input text input from user
     * @return string response given to user
     * @throws IOException
     * @throws CompanioException
     */
    public String getResponse(String input) throws IOException, CompanioException {
        Command command = Parser.parse(input);
        return command.execute(tasks, storage);
    }
}