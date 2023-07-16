package cs3500.pa05.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import org.junit.jupiter.api.Test;

/**
 * Test class for WeekJson
 */
public class WeekJsonTest {
  /**
   * Tests that a WeekJson can be successfully created
   */
  @Test
  public void testWeek() {
    WeekJson week = new WeekJson(new ArrayList<Day>(), 3, 2, "quote",
        "title", "-fx-background-color: #ebfaf4",
        "-fx-background-color: #468e7f");

    assertEquals(week.days().size(), 0);
    assertEquals(week.maxEvents(), 3);
    assertEquals(week.maxTasks(), 2);
    assertEquals(week.quotesAndNotes(), "quote");
    assertEquals(week.title(), "title");
  }
}