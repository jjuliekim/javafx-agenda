package cs3500.pa05.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;

/**
 * represents a week
 *
 * @param days list of days in the week
 */
public record WeekJson(
    @JsonProperty("days") ArrayList<Day> days,
    @JsonProperty("maxEvents") int maxEvents,
    @JsonProperty("maxTasks") int maxTasks,
    @JsonProperty("quotesAndNotes") String quotesAndNotes,
    @JsonProperty("title") String title,
    @JsonProperty("menuBarStyle") String menuBarStyle,
    @JsonProperty("mainVboxStyle") String mainVboxStyle
) {
}
