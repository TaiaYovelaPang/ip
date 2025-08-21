import java.util.ArrayList;
import java.util.Scanner;

public class companio {

    //creation of task list
    private static final ArrayList<String> tasks = new ArrayList<>();

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
            } else {
                adding(input);
            }
        }
        scanner.close();
    }

    // To create horizontal line
    public static void printLine() {
        for (int i = 0; i < 50; i++) {
            System.out.print("_");
        }
        System.out.println(); //moves to next line
    }

    // To store text given by user
    public static void adding(String input) {
        tasks.add(input);
        printLine();
        System.out.println("added task: " + input);
        printLine();
    }

    //To list tasks
    public static void listing() {
        printLine();
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i+1) + ". " + tasks.get(i));
        }
        printLine();
    }
}
