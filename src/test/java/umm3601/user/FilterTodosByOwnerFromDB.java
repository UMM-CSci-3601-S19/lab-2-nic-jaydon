package umm3601.user;

import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static junit.framework.TestCase.assertEquals;

public class FilterTodosByOwnerFromDB {

  @Test
  public void filterTodosByOwnerFromDB() throws IOException{
    Database db = new Database("src/main/data/users.json","src/main/data/todos.json");
    Todo[] allTodos = db.listTodos(new HashMap<>());

    Todo[] blancheTodos = db.filterTodosByOwner(allTodos, "Blanche");
    assertEquals("Incorrect number of todos with the owner Blanche", 43, blancheTodos.length);
  }

}
