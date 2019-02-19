package umm3601.user;

import com.google.gson.Gson;

import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Map;

/**
 * A fake "database" of user info
 * <p>
 * Since we don't want to complicate this lab with a real database,
 * we're going to instead just read a bunch of user data from a
 * specified JSON file, and then provide various database-like
 * methods that allow the `UserController` to "query" the "database".
 */
public class Database {

  private User[] allUsers;
  private Todo[] allTodos;

  public Database(String userDataFile, String todoDataFile) throws IOException {
    Gson gson = new Gson();
    FileReader reader = new FileReader(userDataFile);
    allUsers = gson.fromJson(reader, User[].class);
    FileReader reader2 = new FileReader(todoDataFile);
    allTodos = gson.fromJson(reader2, Todo[].class);
  }

  /**
   * Get the single user specified by the given ID. Return
   * `null` if there is no user with that ID.
   *
   * @param id the ID of the desired user
   * @return the user with the given ID, or null if there is no user
   * with that ID
   */
  public User getUser(String id) {
    return Arrays.stream(allUsers).filter(x -> x._id.equals(id)).findFirst().orElse(null);
  }
  /**
   * @param owner the owner of the desired todo
   * @return the todo with the given owner, or null if there is no todo
   * with that owner
   */
  public Todo getTodoOwner(String owner) {
    return Arrays.stream(allTodos).filter(x -> x.owner.equals(owner)).findFirst().orElse(null);
  }
  /**
   * @param category the owner of the desired todo
   * @return the todo with the given owner, or null if there is no todo
   * with that owner
   */
  public Todo getTodoCategory(String category) {
    return Arrays.stream(allTodos).filter(x -> x.category.equals(category)).findFirst().orElse(null);
  }

  /**
   * Get an array of all the users satisfying the queries in the params.
   *
   * @param queryParams map of required key-value pairs for the query
   * @return an array of all the users matching the given criteria
   */
  public User[] listUsers(Map<String, String[]> queryParams) {
    User[] filteredUsers = allUsers;

    // Filter age if defined
    if (queryParams.containsKey("age")) {
      int targetAge = Integer.parseInt(queryParams.get("age")[0]);
      filteredUsers = filterUsersByAge(filteredUsers, targetAge);
    }
    // Process other query parameters here...

    return filteredUsers;
  }
  /**
   * Get an array of all the users satisfying the queries in the params.
   *
   * @param queryParams map of required key-value pairs for the query
   * @return an array of all the users matching the given criteria
   */
  public Todo[] listTodos(Map<String, String[]> queryParams) {
    Todo[] filteredTodos = allTodos;

    // Filter owner if defined
    if (queryParams.containsKey("owner")) {
      String targetOwner = queryParams.get("owner")[0];
      filteredTodos = filterTodosByOwner(filteredTodos, targetOwner);
    }
    if (queryParams.containsKey("category")) {
      String targetCategory = queryParams.get("category")[0];
      filteredTodos = filterTodosByCategory(filteredTodos, targetCategory);
    }
    // Process other query parameters here...

    return filteredTodos;
  }

  /**
   * Get an array of all the users having the target age.
   *
   * @param users     the list of users to filter by age
   * @param targetAge the target age to look for
   * @return an array of all the users from the given list that have
   * the target age
   */
  public User[] filterUsersByAge(User[] users, int targetAge) {
    return Arrays.stream(users).filter(x -> x.age == targetAge).toArray(User[]::new);
  }
  /**
   * Get an array of all the users having the target age.
   *
   * @param todos     the list of users to filter by age
   * @param targetOwner the target age to look for
   * @return an array of all the users from the given list that have
   * the target age
   */
  public Todo[] filterTodosByOwner(Todo[] todos, String targetOwner) {
    return Arrays.stream(todos).filter(x -> x.owner.equals(targetOwner)).toArray(Todo[]::new);
  }
  public Todo[] filterTodosByCategory(Todo[] todos, String targetCategory) {
    return Arrays.stream(todos).filter(x -> x.category.equals(targetCategory)).toArray(Todo[]::new);
  }
}
