import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class TaskStorage {

    private final Path filePath;

    public TaskStorage(String path) {
        this.filePath = Paths.get(path);
    }

    public ArrayList<Task> load() throws IOException, CompanioException {
        ArrayList<Task> tasks = new ArrayList<>();

        if (!Files.exists(filePath)) {
            System.out.println("No tasklist found. Creating a new one for you!");
            // Create missing directories and file
            Files.createDirectories(filePath.getParent());
            Files.createFile(filePath);
            return tasks; // empty task list
        }

        //Reading file that exists
        BufferedReader reader = Files.newBufferedReader(filePath);
        String line;

        while ((line = reader.readLine()) != null) {
            tasks.add(parseLine(line));
        }

        return tasks;
    }

    public void save(ArrayList<Task> tasks) throws IOException {
        BufferedWriter writer = Files.newBufferedWriter(filePath);
        for (Task task : tasks) {
            writer.write(task.toSave());
            writer.newLine();
        }
        writer.close();
    }

    private Task parseLine(String line) throws CompanioException {
        String[] strings = line.split("\\|");
        String type = strings[0];
        boolean isDone = strings[1].equals("X");
        String description = strings[2];

        switch (type) {
        case "T":
            return new ToDo(description, isDone);
        case "D":
            return new Deadline(description, isDone, strings[3]);
        case "E":
            return new Event(description, isDone, strings[3], strings[4],  strings[5]);
        default:
            throw new CompanioException("Unknown task type!");
        }
    }
}
