package cs3500.pa05.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

/**
 * Represents a day of the week
 */
public class Day {
  public DayOfWeek dayOfWeek;
  public ArrayList<EventJson> events;
  public ArrayList<TaskJson> tasks;

  /**
   * Constructor for Day
   *
   * @param dayOfWeek day of the week
   */
  @JsonCreator
  public Day(
      @JsonProperty("dayOfWeek") DayOfWeek dayOfWeek) {
    this.dayOfWeek = dayOfWeek;
    this.events = new ArrayList<>();
    this.tasks = new ArrayList<>();
  }

  /**
   * Adds the given event to this day's events
   *
   * @param e Event to add
   */
  public void addEvent(EventJson e) {
    this.events.add(e);
  }

  /**
   * Adds the given task to this day's tasks
   *
   * @param t Task to add
   */
  public void addTask(TaskJson t) {
    this.tasks.add(t);
  }

  /**
   * Returns this day's events
   *
   * @return this day's events
   */
  public ArrayList<EventJson> getEvents() {
    return this.events;
  }

  /**
   * Returns this day's tasks
   *
   * @return this day's tasks
   */
  public ArrayList<TaskJson> getTasks() {
    return this.tasks;
  }
}
