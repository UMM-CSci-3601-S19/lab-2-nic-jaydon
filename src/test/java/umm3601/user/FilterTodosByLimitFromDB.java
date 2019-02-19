package umm3601.user;

import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;

import static junit.framework.TestCase.assertEquals;

public class FilterTodosByLimitFromDB {

  @Test
  public void filterTodosByLimitFromDB() throws IOException{
    Database db = new Database("src/main/data/users.json","src/main/data/todos.json");
    Todo[] allTodos = db.listTodos(new HashMap<>());

    Todo[] sevenTodos = db.filterTodosByLimit(allTodos, 7);
    assertEquals("Number of todos not within the limit", 7, sevenTodos.length);

    Todo[] eightTodos = db.filterTodosByLimit(allTodos, 8);
    assertEquals("Number of todos not within the limit", 8, eightTodos.length);
  }
}
