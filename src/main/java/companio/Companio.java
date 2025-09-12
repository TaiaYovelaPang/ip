package companio;

import companio.task.Task;
import companio.task.ToDo;
import companio.task.Deadline;
import companio.task.Event;
import companio.task.TaskStorage;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

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
            if (input.trim().equals("todo")) {
                throw new CompanioException("todo description is empty!");
            }
            task = new ToDo(input.substring(5));
        } else if (input.startsWith("deadline")) {
            if (input.trim().equals("deadline")) {
                throw new CompanioException("deadline description is empty");
            }
            String[] strings = input.substring(9).split("/");
            if (strings.length < 2) {
                throw new CompanioException("missing deadline for task!");
            }
            LocalDateTime deadline;
            try {
                DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                deadline = LocalDateTime.parse(strings[1].trim(), inputFormat);
            } catch (DateTimeParseException e) {
                throw new CompanioException("Invalid deadline format! Use yyyy-MM-dd HHmm (e.g., 2025-08-30 18:25).");
            }
            task = new Deadline(strings[0], deadline);
        } else if (input.startsWith("event")) {
            if (input.trim().equals("event")) {
                throw new CompanioException("event description is empty");
            }
            String[] strings = input.substring(6).split("/");
            if (strings.length < 4) {
                throw new CompanioException("event details not specified!");
            }
            LocalDate date;
            try {
                date = LocalDate.parse(strings[1].trim()); // ISO format expected: yyyy-MM-dd
            } catch (DateTimeParseException e) {
                throw new CompanioException("Invalid date format! Use yyyy-MM-dd (e.g., 2025-08-30).");
            }
            LocalTime startTime;
            try {
                startTime = LocalTime.parse(strings[2].trim());
            } catch (DateTimeParseException e) {
                throw new CompanioException("Invalid time format! Use HH:mm (e.g., 18:25).");
            }
            LocalTime endTime;
            try {
                endTime = LocalTime.parse(strings[3].trim());
            } catch (DateTimeParseException e) {
                throw new CompanioException("Invalid time format! Use HH:mm (e.g., 18:25).");
            }
            task = new Event(strings[0], date, startTime, endTime);
        } else {
            throw new CompanioException("Unknown task type!");
        }
        tasks.add(task);
        assert tasks.contains(task) : "Task should be present in the task list after adding.";
        return task;
    }


    public String getResponse(String input) throws IOException, CompanioException {
        StringBuilder response = new StringBuilder();
        if (input.equals("bye")) {
            response.append("Bye. Till next time!");
        } else if (input.equals("list")) {
            response.append("Showing your to-do list:\n");
            for (int i = 0; i < tasks.size(); i++) {
                response.append((i + 1)).append(". ").append(tasks.get(i)).append("\n");
            }
        } else if (input.startsWith("mark ")) {
            int index = Integer.parseInt(input.split(" ")[1]) - 1;
            Task task = tasks.get(index);
            task.markAsDone();
            storage.save(tasks);
            response.append("Good job in completing a task!\n    ").append(task);
        } else if (input.startsWith("unmark ")) {
            int index = Integer.parseInt(input.split(" ")[1]) - 1;
            Task task = tasks.get(index);
            task.markAsUndone();
            storage.save(tasks);
            response.append("Oops, one more undone task.\n    ").append(task);
        } else if (input.startsWith("delete ")) {
            try {
                int index = Integer.parseInt(input.split(" ")[1]) - 1; //Tasks are 1 based
                if (index < 0 || index >= tasks.size()) {
                    throw new CompanioException("No such task found.");
                }
                Task removedTask = tasks.remove(index);
                storage.save(tasks);
                response.append("Yay! One task removed!\n    ").append(removedTask)
                        .append("\nNumber of tasks: ").append(tasks.size());
            } catch (CompanioException | IOException e){
                response.append(e.getMessage());
            }
        } else if (input.startsWith("find ")) {
            ArrayList<Task> matches = new ArrayList<>();
            if (input.trim().equals("find")) {
                throw new CompanioException("find description is empty!");
            }
            String description = input.substring(5);
            for (Task task : tasks) {
                if (task.getDescription().contains(description)) {
                    matches.add(task);
                }
            }
            if (matches.isEmpty()) {
                response.append("No task matching your input found :(");
            } else {
                for (Task matchedTask : matches) {
                    response.append(matchedTask);
                }
            }
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
        return response.toString();
    }
}
