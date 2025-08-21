import java.util.ArrayList;
import java.util.Scanner;

public class companio {

    //creation of task list
    private static final ArrayList<Task> tasks = new ArrayList<>();

    public static void main(String[] args) {
        String greeting = "Hello! I'm COMPANIO \n"
                + "What can I do for you?";

        String bye = "Bye. Till next time!";

        //Initial greeting
        printLine();
        System.out.println(greeting);
        printLine();

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
                listing();
            } else if (input.startsWith("mark ")) {
                marking(input);
            } else if (input.startsWith("unmark ")) {
                unmarking(input);
            } else {
                adding(input);
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
    private static void adding(String input) {
        Task task;
        if (input.startsWith("todo ")) {
            task = new ToDo(input.substring(5));
        } else if (input.startsWith("deadline ")){
            String[] string = input.substring(9).split("/");
            task = new Deadline(string[0], string[1]);
        } else if (input.startsWith("event ")) {
            String[] string = input.substring(6).split("/");
            task = new Event(string[0], string[1], string[2]);
        } else {
            printLine();
            System.out.println("Unknown task type!");
            printLine();
            return; //Exit early so task isn't used uninitialized
        }
        tasks.add(task);
        printLine();
        System.out.println("One task added: \n"
                + task + "\n"
                + "Number of tasks: " + tasks.size());
        printLine();
    }

    //To list tasks
    private static void listing() {
        printLine();
        System.out.println("Showing your to-do list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i+1) + ". " + tasks.get(i));
        }
        printLine();
    }

    //To mark tasks
    private static void marking(String input) {
        try {
            int index = Integer.parseInt(input.split(" ")[1]) - 1; //Tasks are 1 based
            Task task = tasks.get(index);
            task.done();
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

    //To mark tasks as undone
    private static void unmarking(String input) {
        try {
            int index = Integer.parseInt(input.split(" ")[1]) - 1; //Tasks are 1 based
            Task task = tasks.get(index);
            task.undone();
            printLine();
            System.out.println("Oops, one more undone task. \n"
                    + "    " + task);
            printLine();
        } catch (Exception e) {
            printLine();
            System.out.println("No such task found.");
            printLine();
        }
    }
}
