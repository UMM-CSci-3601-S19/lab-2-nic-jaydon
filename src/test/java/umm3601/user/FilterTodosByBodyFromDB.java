package umm3601.user;

import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;

import static junit.framework.TestCase.assertEquals;

public class FilterTodosByBodyFromDB {

  @Test
  public void filterTodosByBodyFromDB() throws IOException {
    Database db = new Database("src/main/data/users.json","src/main/data/todos.json");
    Todo[] allTodos = db.listTodos(new HashMap<>());

    Todo[] bananaTodos = db.filterTodosByBody(allTodos, "banana");
    assertEquals("Incorrect number of todos containing the string banana", 0, bananaTodos.length);
  }
}
