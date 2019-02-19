package umm3601.user;

import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;

import static junit.framework.TestCase.assertEquals;

public class FilterTodosByStatusFromDB {

  @Test
  public void filterTodosOfCategory() throws IOException {
    Database db = new Database("src/main/data/users.json","src/main/data/todos.json");
    Todo[] allTodos = db.listTodos(new HashMap<>());

    Todo[] completedTodos = db.filterTodosByStatus(allTodos,true);
    assertEquals("Incorrect number of todos completed", 143, completedTodos.length);

    Todo[] incompleteTodos = db.filterTodosByStatus(allTodos, false);
    assertEquals("Incorrect number of todos incomplete", 157, incompleteTodos.length);
  }
}
