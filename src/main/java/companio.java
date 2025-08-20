public class companio {
    public static void main(String[] args) {
        String greeting = "Hello! I'm COMPANIO \n"
                + "What can I do for you?";

        String bye = "Bye. Hope to see you again soon!";

        printLine();
        System.out.println(greeting);
        printLine();
        System.out.println(bye);
        printLine();
    }

    // To create horizontal line
    public static void printLine() {
        for (int i = 0; i < 50; i++) {
            System.out.print("_");
        }
        System.out.println(); //moves to next line
    }
}
