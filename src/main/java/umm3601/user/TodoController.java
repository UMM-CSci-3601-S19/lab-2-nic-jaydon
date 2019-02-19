package umm3601.user;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import spark.Request;
import spark.Response;

import java.io.IOException;

import static umm3601.Util.*;

/**
 * Controller that manages requests for info about todos.
 */
public class TodoController {

  private final Gson gson;
  private Database database;

  /**
   * Construct a controller for todos.
   * <p>
   * This loads the "database" of todo info from a JSON file and
   * stores that internally so that (subsets of) todos can be returned
   * in response to requests.
   *
   * @param database the database containing todo data
   */
  public TodoController(Database database) {
    gson = new Gson();
    this.database = database;
  }

  /**
   * Get the single todo specified by the `owner` parameter in the request.
   *
   * @param req the HTTP request
   * @param res the HTTP response
   * @return a success JSON object if the todo with that owner is found, a fail
   * JSON object if no todo with that owner is found
   */
  public JsonObject getTodoOwner(Request req, Response res) {
    res.type("application/json");
    String owner = req.params("owner");
    Todo todo = database.getTodoOwner(owner);
    if (todo != null) {
      return buildSuccessJsonResponse("todo", gson.toJsonTree(todo));
    } else {
      String message = "Todo with Owner " + owner + " wasn't found.";
      return buildFailJsonResponse("owner", message);
    }
  }
  public JsonObject getTodoCategory(Request req, Response res) {
    res.type("application/json");
    String category = req.params("category");
    Todo todo = database.getTodoCategory(category);
    if (todo != null) {
      return buildSuccessJsonResponse("todo", gson.toJsonTree(todo));
    } else {
      String message = "Todo with Owner " + category + " wasn't found.";
      return buildFailJsonResponse("category", message);
    }
  }
  /**
   * Get a JSON response with a list of all the todos in the "database".
   *
   * @param req the HTTP request
   * @param res the HTTP response
   * @return a success JSON object containing all the todos
   */
  public JsonObject getTodos(Request req, Response res) {
    res.type("application/json");
    Todo[] todos = database.listTodos(req.queryMap().toMap());
    return buildSuccessJsonResponse("todos", gson.toJsonTree(todos));
  }

}
