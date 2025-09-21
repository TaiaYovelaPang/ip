package companio.task;

import companio.CompanioException;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Represents a collection of {@link Task} objects managed by Companio.
 * <p>
 * Provides operations for adding, deleting, retrieving, searching, and viewing tasks
 * by date. The {@code TaskList} acts as the main in-memory data structure to track
 * the user's tasks during a session.
 */
public class TaskList {
    private final List<Task> tasks;

    public TaskList(List<Task> initialTasks) {
        this.tasks = new ArrayList<>(initialTasks);
    }

    /**
     * Adds a new task to the list.
     * @param task the task to be added
     */
    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * Deletes the task at the specified index.
     * @param index the zero-based index of the task to delete
     * @return the deleted {@link Task}
     * @throws CompanioException if the index is out of bounds
     */
    public Task delete(int index) throws CompanioException {
        checkIndex(index);
        return tasks.remove(index);
    }

    /**
     * Retrieves the task at the specified index.
     * @param index the zero-based index of the task to retrieve
     * @return the {@link Task} at the given index
     * @throws CompanioException if the index is out of bounds
     */
    public Task get(int index) throws CompanioException {
        checkIndex(index);
        return tasks.get(index);
    }

    /**
     * Returns the number of tasks currently in the list.
     * @return the number of tasks
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Finds all tasks whose description contains the given keyword.
     * @param keyword the search keyword
     * @return a list of matching tasks
     */
    public List<Task> find(String keyword) {
        return tasks.stream()
                .filter(t -> t.getDescription().contains(keyword))
                .toList();
    }

    /**
     * Retrieves all tasks scheduled for the specified date,
     * sorted by their start time (if any).
     * @param date the date to filter by
     * @return a list of tasks on that date, sorted by time
     */
    public List<Task> view(LocalDate date) {
        return tasks.stream()
                .filter(t -> Objects.equals(t.getDate(), date))
                .sorted(Comparator.comparing(Task::getTime))
                .toList();
    }

    /**
     * Returns a string representation of all tasks in the list,
     * formatted as a numbered to-do list.
     * @return a formatted string of tasks
     */
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

    /**
     * Returns an unmodifiable view of the current task list.
     * <p>
     * Useful for exposing tasks to read-only consumers without
     * risking modification of the internal list.
     * @return an unmodifiable list of tasks
     */
    public List<Task> asUnmodifiableList() {
        return Collections.unmodifiableList(tasks);
    }
}

