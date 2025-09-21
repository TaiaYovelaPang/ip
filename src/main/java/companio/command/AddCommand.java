package companio.command;

import companio.CompanioException;
import companio.addtask.AddDeadline;
import companio.addtask.AddEvent;
import companio.addtask.AddTodo;
import companio.task.Task;
import companio.task.TaskList;
import companio.task.TaskStorage;

import java.io.IOException;

/**
 * This class helps to deal with todo, deadline and event additions. It also handles the case where
 * the input is not any of the method calls for Companio.
 */
public class AddCommand implements Command {
    private final String input;

    public AddCommand(String input) {
        this.input = input;
    }

    @Override
    public String execute(TaskList tasks, TaskStorage storage) throws IOException, CompanioException {
        try {
            Task task;

            if (input.startsWith("todo")) {
                AddTodo todo = new AddTodo(input);
                todo.checkInput();
                task = todo.create();
            } else if (input.startsWith("deadline")) {
                AddDeadline deadline = new AddDeadline(input);
                deadline.checkInput();
                task = deadline.create();
            } else if (input.startsWith("event")) {
                AddEvent event = new AddEvent(input);
                event.checkInput();
                task = event.create();
            } else {
                return "Unknown task type!";
            }

            tasks.add(task);
            storage.save(tasks);

            return "One task added:\n    " + task + "\nNumber of tasks: " + tasks.size();
        } catch (CompanioException e) {
            return e.getMessage();
        }
    }
}
