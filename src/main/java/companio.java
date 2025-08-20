import java.util.Scanner;

public class companio {
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

        //Echoing
        while (true) {
            input = scanner.nextLine();
            if (input.equals("bye")) {
                printLine();
                System.out.println(bye);
                printLine();
                break;
            } else {
                printLine();
                System.out.println(input);
                printLine();
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
}
