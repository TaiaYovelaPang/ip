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
import java.util.Scanner;

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

    public static void main(String[] args) throws CompanioException, IOException {
        String greeting = "Hello! I'm COMPANIO\n"
                + "What can I do for you?";

        String bye = "Bye. Till next time!";

        //Initial greeting
        printLine();
        System.out.println(greeting);
        printLine();

        //Reading from file
        tasks = storage.loadTaskList();

        //Getting input from user
        Scanner scanner = new Scanner(System.in);
        String input;

        //Doing what user wants
        while (true) {
            input = scanner.nextLine();
            if (input.equals("bye")) {
                printLine();
                System.out.println(bye);
                printLine();
                break;
            } else if (input.equals("list")) {
                listTasks();
            } else if (input.startsWith("mark ")) {
                markTask(input);
            } else if (input.startsWith("unmark ")) {
                unmarkTask(input);
            } else if (input.startsWith("delete ")) {
                deleteTask(input);
            } else {
                addTask(input);
            }
        }
        scanner.close();
    }

    // To create horizontal line
    private static void printLine() {
        for (int i = 0; i < 50; i++) {
            System.out.print("_");
        }
        System.out.println(); //moves to next line
    }

    // To add tasks given by user
    private static void addTask(String input) {
        try {
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
            storage.save(tasks);
            printLine();
            System.out.println("One task added:\n"
                    + "    " + task + "\n"
                    + "Number of tasks: " + tasks.size());
            printLine();
        } catch (CompanioException | IOException e) {
            printLine();
            System.out.println(e.getMessage());
            printLine();
        }
    }

    //To delete tasks
    private static void deleteTask(String input) {
        try {
            int index = Integer.parseInt(input.split(" ")[1]) - 1; //Tasks are 1 based
            if (index < 0 || index >= tasks.size()) {
                throw new CompanioException("No such task found.");
            }
            Task removedTask = tasks.remove(index);
            storage.save(tasks);
            printLine();
            System.out.println("Yay! One task removed!\n"
                    + "    " + removedTask + "\n"
                    + "Number of tasks: " + tasks.size());
            printLine();
        } catch (CompanioException | IOException e){
            printLine();
            System.out.println(e.getMessage());
            printLine();
        }
    }

    //To list tasks
    private static void listTasks() {
        printLine();
        System.out.println("Showing your to-do list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i+1) + ". " + tasks.get(i));
        }
        printLine();
    }

    //To mark tasks
    private static void markTask(String input) {
        try {
            int index = Integer.parseInt(input.split(" ")[1]) - 1; //Tasks are 1 based
            Task task = tasks.get(index);
            task.markAsDone();
            storage.save(tasks);
            printLine();
            System.out.println("Good job in completing a task! \n"
                    + "    " + task);
            printLine();
        } catch (Exception e){
            printLine();
            System.out.println("No such task found.");
            printLine();
        }
    }

    //To mark tasks as markAsUndone
    private static void unmarkTask(String input) {
        try {
            int index = Integer.parseInt(input.split(" ")[1]) - 1; //Tasks are 1 based
            Task task = tasks.get(index);
            task.markAsUndone();
            storage.save(tasks);
            printLine();
            System.out.println("Oops, one more markAsUndone task. \n"
                    + "    " + task);
            printLine();
        } catch (Exception e) {
            printLine();
            System.out.println("No such task found.");
            printLine();
        }
    }
}
