Chatgpt helped to break down the original monolithic Companio class into smaller, more maintainable classes such as TaskList and command classes (AddCommand, MarkCommand, UnmarkCommand, DeleteCommand, FindCommand, ViewCommand, ByeCommand).

It also helped in error handling such as by catching exceptions inside commands so that the GUI always returns a user-friendly message (e.g., "No such task found" or "Unknown task type") instead of freezing.

However, sometimes the code given didn’t fully align with the existing architecture, requiring manual adjustments.

Overall, using AI saved 50-60% of the effort needed for OOP implementation as well as error handling.