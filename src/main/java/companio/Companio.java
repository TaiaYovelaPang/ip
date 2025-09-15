package companio;

import companio.addtask.AddDeadline;
import companio.addtask.AddEvent;
import companio.addtask.AddTodo;
import companio.task.Task;
import companio.task.TaskStorage;

import java.io.IOException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    private static TaskStorage storage = new TaskStorage("./data/companio.txt");

    //creation of task list
    private static ArrayList<Task> tasks = new ArrayList<>();

    public Companio() throws IOException, CompanioException {
        tasks = storage.loadTaskList();
        assert tasks != null : "Task list should not be empty after loading from storage.";
    }

    // To add tasks given by user
    private Task addTask(String input) throws CompanioException {
        assert input != null && !input.trim().isEmpty() : "Input should not be null or empty.";
        Task task;
        if (input.startsWith("todo")) {
            AddTodo todo  = new AddTodo(input);
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
            throw new CompanioException("Unknown task type!");
        }
        tasks.add(task);
        assert tasks.contains(task) : "Task should be present in the task list after adding.";
        return task;
    }


    /**
     * Handles the input given by the user by calling the respective methods.
     * @param input text input from user
     * @return string response given to user
     * @throws IOException
     * @throws CompanioException
     */
    public String getResponse(String input) throws IOException, CompanioException {
        if (input.equals("bye")) {
            return handleBye();
        } else if (input.equals("list")) {
            return handleList();
        } else if (input.startsWith("mark ")) {
            int index = Integer.parseInt(input.split(" ")[1]) - 1;
            checkIndex(index);
            return handleMark(index);
        } else if (input.startsWith("unmark ")) {
            int index = Integer.parseInt(input.split(" ")[1]) - 1;
            checkIndex(index);
            return handleUnmark(index);
        } else if (input.startsWith("delete ")) {
            int index = Integer.parseInt(input.split(" ")[1]) - 1;
            checkIndex(index);
            return handleDelete(index);
        } else if (input.startsWith("find ")) {
            return handleFind(input);
        } else {
            // Default: add a task
            try {
                Task task = addTask(input);
                storage.save(tasks);
                return "One task added:\n    " + task + "\nNumber of tasks: " + tasks.size();
            } catch (CompanioException | IOException e) {
                return e.getMessage();
            }
        }
    }

    private void checkIndex(int index) throws CompanioException {
        if (index < 0 || index >= tasks.size()) {
            throw new CompanioException("No such task found.");
        }
    }

    private String handleBye() {
        return "Bye. Till next time!";
    }

    private String handleList() {
        StringBuilder sb = new StringBuilder("Showing your to-do list:\n");
        for (int i = 0; i < tasks.size(); i++) {
            sb.append(i + 1).append(". ").append(tasks.get(i)).append("\n");
        }
        return sb.toString();
    }

    private String handleMark(int index) throws IOException {
        Task task = tasks.get(index);
        task.markAsDone();
        storage.save(tasks);
        return "Good job in completing a task!\n    " + task;
    }

    private String handleUnmark(int index) throws IOException {
        Task task = tasks.get(index);
        task.markAsUndone();
        storage.save(tasks);
        return "Oops, one more undone task.\n    " + task;
    }

    private String handleDelete(int index) throws IOException {
        Task removedTask = tasks.remove(index);
        storage.save(tasks);
        return "Yay! One task removed!\n    " + removedTask + "\nNumber of tasks: " + tasks.size();
    }

    private String handleFind(String input) throws CompanioException {
        if (input.trim().equals("find")) {
            throw new CompanioException("find description is empty!");
        }
        String description = input.substring(5);
        List<Task> matches = tasks.stream()
                .filter(t -> t.getDescription().contains(description))
                .toList();
        if (matches.isEmpty()) {
            return "No task matching your input found :(";
        }
        return matches.stream()
                .map(Task::toString)
                .collect(Collectors.joining("\n"));
    }
}
