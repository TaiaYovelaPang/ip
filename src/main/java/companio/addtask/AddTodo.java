package companio.addtask;

import companio.CompanioException;
import companio.task.Task;
import companio.task.ToDo;

/**
 * A class that helps Companio to handle new todos added by the user.
 *
 * <p> It will ensure validity of the input given by the user and creates the todo task. </p>
 */

public class AddTodo {
     private String input;

     public AddTodo(String input) {
         this.input = input;
     }

    /**
     * Checks validity of the input given by user.
     * @throws CompanioException exception to catch missing description
     */
     public void checkInput() throws CompanioException {
         if (input.trim().equals("todo")) {
             throw new CompanioException("todo description is empty!");
         }
     }

    /**
     * Creates a new todo task.
     * @return todo task
     */
     public Task create() {
         Task task = new ToDo(input.substring(5));
         return task;
     }
}
