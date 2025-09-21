package companio;

import companio.command.*;

/**
 * The Parser class is responsible for interpreting and processing user input
 * for the Companio chatbot.
 *
 *  <p>It analyzes raw text input, determines the intended command or task type,
 *  and converts it into the corresponding Command object that can be executed
 *  by the chatbot.</p>
 */
public class Parser {
    public static Command parse(String input) throws CompanioException {
        try {
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
        } catch (CompanioException e) {
            return new ErrorCommand(e.getMessage());
        }
    }
}
