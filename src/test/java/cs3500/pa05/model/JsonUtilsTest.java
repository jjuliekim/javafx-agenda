package cs3500.pa05.model;


import static org.junit.jupiter.api.Assertions.assertEquals;

import com.fasterxml.jackson.databind.JsonNode;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;

/**
 * Test class for JsonUtils
 */
public class JsonUtilsTest {
  /**
   * Tests that the serializeRecord method of JsonUtils does not throw an error
   */
  @Test
  public void testValidSerializeRecord() {
    JsonUtils utils = new JsonUtils();

    WeekJson week = new WeekJson(new ArrayList<>(), 1, 1, "notes",
        "title", "-fx-background-color: #ebfaf4",
        "-fx-background-color: #468e7f");
    JsonNode node = JsonUtils.serializeRecord(week);
  }

  /**
   * Tests that the serializeRecord method of JsonUtils throws an error when appropriate
   */
  @Test
  public void testInvalidSerializeRecord() {
    try {
      JsonNode node = JsonUtils.serializeRecord(null);
    } catch (Exception exc) {
      assertEquals(exc.toString(), "IllegalArgumentException");
    }
  }
}