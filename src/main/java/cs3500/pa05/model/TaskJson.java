package cs3500.pa05.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import cs3500.pa05.Driver;
import java.util.ArrayList;
import javafx.scene.control.Hyperlink;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

/**
 * Represents a Json for a task
 *
 * @param name        name of the task
 * @param description task description
 * @param dayOfWeek   day of the task
 * @param completed   whether the task is completed
 */
public record TaskJson(
    @JsonProperty("name") String name,
    @JsonProperty("description") String description,
    @JsonProperty("dayOfWeek") DayOfWeek dayOfWeek,
    @JsonProperty("completed") ArrayList<Boolean> completed) {

  /**
   * Converts this Task to a TextFlow
   *
   * @return TextFlow representing htis Task
   */
  public TextFlow toTextFlow() {
    TextFlow textFlow = new TextFlow();

    if (this.description.equals("")) {
      textFlow.getChildren().add(new Text(this.name + ", " + dayOfWeek.toString()
          + "; completed: " + completed.get(0)));
    } else {
      textFlow.getChildren().add(new Text(this.name + " ("));
      for (String s : this.description.split(" ")) {
        if (s.contains("http://") || s.contains("https://")) {
          Hyperlink hyperlink = new Hyperlink(s);
          hyperlink.setOnAction(e -> handleHyperlink(s));
          textFlow.getChildren().add(hyperlink);
        } else {
          textFlow.getChildren().add(new Text(s));
        }
      }
      textFlow.getChildren().add(new Text("), " + dayOfWeek.toString()
          + "; completed: " + completed.get(0)));
    }

    return textFlow;
  }

  /**
   * Returns whether this task has been completed
   *
   * @return boolean representing if this task has been completed
   */
  public boolean isCompleted() {
    return this.completed.get(0);
  }

  /**
   * Returns the name of this task
   *
   * @return name of this task
   */
  public String getName() {
    return this.name;
  }

  /**
   * updates task completion's status
   *
   * @param completed completed status
   */
  public void setCompleted(boolean completed) {
    this.completed.add(0, completed);
  }

  /**
   * Handles hyperlinks
   *
   * @param hyperlink to link to
   */
  public void handleHyperlink(String hyperlink) {
    Driver driver = new Driver();
    driver.getHostServices().showDocument(hyperlink);
  }
}
