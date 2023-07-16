package cs3500.pa05.model;


import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tester class for Day
 */
public class DayTest {
  private Day day;

  /**
   * Sets up a Day variable for future test cases
   */
  @BeforeEach
  public void setup() {
    this.day = new Day(DayOfWeek.SUNDAY);
    assertEquals(this.day.getEvents().size(), 0);
    assertEquals(this.day.getEvents().size(), 0);
  }

  /**
   * Tests the addEvent method of Day
   */
  @Test
  public void testAddEvent() {
    EventJson event = new EventJson("event", "this is an event", DayOfWeek.FRIDAY,
        "1:00", "10");
    assertEquals(this.day.getEvents().size(), 0);
    this.day.addEvent(event);
    assertEquals(this.day.getEvents().size(), 1);
  }

  /**
   * Tests the addTask method of Day
   */
  @Test
  public void testAddTask() {
    TaskJson task = new TaskJson("Task", "this is a task", DayOfWeek.MONDAY,
        new ArrayList<>());
    assertEquals(this.day.getTasks().size(), 0);
    this.day.addTask(task);
    assertEquals(this.day.getTasks().size(), 1);
  }
}