package umm3601.user;

import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static junit.framework.TestCase.assertEquals;

public class FilterTodosByCategory {

  @Test
  public void filterTodosOfCategory() throws IOException {
    Database db = new Database("src/main/data/users.json","src/main/data/todos.json");
    Todo[] allTodos = db.listTodos(new HashMap<>());

    Todo[] videoGameTodos = db.filterTodosByCategory(allTodos,"video games");
    assertEquals("Incorrect number of todos in the category Video Games", 71, videoGameTodos.length);

    Todo[] softwareDesignTodos = db.filterTodosByCategory(allTodos, "software design");
    assertEquals("Incorrect number of todos in the category Software Design", 74, softwareDesignTodos.length);

    Todo[] groceriesTodos = db.filterTodosByCategory(allTodos, "groceries");
    assertEquals("Incorrect number of todos in the category Groceries", 76, groceriesTodos.length);
  }
}
