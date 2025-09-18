package companio.command;

import companio.CompanioException;
import companio.addtask.AddDeadline;
import companio.addtask.AddEvent;
import companio.addtask.AddTodo;
import companio.task.Task;
import companio.task.TaskList;
import companio.task.TaskStorage;

import java.io.IOException;

public class AddCommand implements Command {
    private final String input;

    public AddCommand(String input) {
        this.input = input;
    }

    @Override
    public String execute(TaskList tasks, TaskStorage storage) throws IOException, CompanioException {
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
    }
}
