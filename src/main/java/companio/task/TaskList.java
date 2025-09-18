package companio.task;

import companio.CompanioException;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class TaskList {
    private final List<Task> tasks;

    public TaskList(List<Task> initialTasks) {
        this.tasks = new ArrayList<>(initialTasks);
    }

    public void add(Task task) {
        tasks.add(task);
    }

    public Task delete(int index) throws CompanioException {
        checkIndex(index);
        return tasks.remove(index);
    }

    public Task get(int index) throws CompanioException {
        checkIndex(index);
        return tasks.get(index);
    }

    public int size() {
        return tasks.size();
    }

    public List<Task> find(String keyword) {
        return tasks.stream()
                .filter(t -> t.getDescription().contains(keyword))
                .toList();
    }

    public List<Task> view(LocalDate date) {
        return tasks.stream()
                .filter(t -> Objects.equals(t.getDate(), date))
                .sorted(Comparator.comparing(Task::getTime))
                .toList();
    }

    public String listAsString() {
        StringBuilder sb = new StringBuilder("Showing your to-do list:\n");
        for (int i = 0; i < tasks.size(); i++) {
            sb.append(i + 1).append(". ").append(tasks.get(i)).append("\n");
        }
        return sb.toString();
    }

    private void checkIndex(int index) throws CompanioException {
        if (index < 0 || index >= tasks.size()) {
            throw new CompanioException("No such task found.");
        }
    }

    public List<Task> asUnmodifiableList() {
        return Collections.unmodifiableList(tasks);
    }
}

