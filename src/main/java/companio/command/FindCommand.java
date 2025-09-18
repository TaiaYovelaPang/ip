package companio.command;

import companio.CompanioException;
import companio.task.Task;
import companio.task.TaskList;
import companio.task.TaskStorage;

import java.util.List;
import java.util.stream.Collectors;

public class FindCommand implements Command {
    private final String keyword;

    public FindCommand(String input) throws CompanioException {
        if (input.trim().equals("find")) {
            throw new CompanioException("find description is empty!");
        }
        this.keyword = input.substring(5).trim();
    }

    @Override
    public String execute(TaskList tasks, TaskStorage storage) {
        List<Task> matches = tasks.find(keyword);
        if (matches.isEmpty()) {
            return "No task matching your input found :(";
        }
        return matches.stream()
                .map(Task::toString)
                .collect(Collectors.joining("\n"));
    }
}
