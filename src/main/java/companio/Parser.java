package companio;

import companio.CompanioException;
import companio.command.*;

public class Parser {
    public static Command parse(String input) throws CompanioException {
        if (input.equals("bye")) {
            return new ByeCommand();
        } else if (input.equals("list")) {
            return new ListCommand();
        } else if (input.startsWith("mark ")) {
            return new MarkCommand(input);
        } else if (input.startsWith("unmark ")) {
            return new UnmarkCommand(input);
        } else if (input.startsWith("delete ")) {
            return new DeleteCommand(input);
        } else if (input.startsWith("find ")) {
            return new FindCommand(input);
        } else if (input.startsWith("view ")) {
            return new ViewCommand(input);
        } else {
            return new AddCommand(input); // default add
        }
    }
}
