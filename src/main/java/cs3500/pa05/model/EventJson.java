package cs3500.pa05.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import cs3500.pa05.Driver;
import javafx.scene.control.Hyperlink;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

/**
 * Represents a Json for an event
 *
 * @param name        name of the event
 * @param description description of the event
 * @param dayOfWeek   day of the event
 * @param startTime   time of the event
 * @param duration    duration of the event
 */
public record EventJson(
    @JsonProperty("name") String name,
    @JsonProperty("description") String description,
    @JsonProperty("dayOfWeek") DayOfWeek dayOfWeek,
    @JsonProperty("startTime") String startTime,
    @JsonProperty("duration") String duration) {

  /**
   * Converts this Event to a TextFlow
   *
   * @return TextFlow representing this event as a textflow
   */
  public TextFlow toTextFlow() {
    TextFlow textFlow = new TextFlow();
    if (this.description.equals("")) {
      textFlow.getChildren().add(new Text(this.name + ", " + dayOfWeek.toString()
          + ", " + startTime + "; " + duration));
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
          + ", " + startTime + "; " + duration));
    }

    return textFlow;
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
