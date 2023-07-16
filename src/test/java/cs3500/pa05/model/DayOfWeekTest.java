package cs3500.pa05.model;


import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Tester class for DayOfWeek
 */
public class DayOfWeekTest {
  /**
   * Tests the DayOfWeek enums are created successfully
   */
  @Test
  public void testDayOfWeek() {
    DayOfWeek sunday = DayOfWeek.SUNDAY;
    assertEquals(sunday.toString(), "SUNDAY");
    DayOfWeek monday = DayOfWeek.MONDAY;
    assertEquals(monday.toString(), "MONDAY");
    DayOfWeek tuesday = DayOfWeek.TUESDAY;
    assertEquals(tuesday.toString(), "TUESDAY");
    DayOfWeek wednesday = DayOfWeek.WEDNESDAY;
    assertEquals(wednesday.toString(), "WEDNESDAY");
    DayOfWeek thursday = DayOfWeek.THURSDAY;
    assertEquals(thursday.toString(), "THURSDAY");
    DayOfWeek friday = DayOfWeek.FRIDAY;
    assertEquals(friday.toString(), "FRIDAY");
    DayOfWeek saturday = DayOfWeek.SATURDAY;
    assertEquals(saturday.toString(), "SATURDAY");
  }
}