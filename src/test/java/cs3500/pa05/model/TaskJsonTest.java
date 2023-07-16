package cs3500.pa05.model;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.Node;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test class for TaskJson
 */
public class TaskJsonTest {
  private TaskJson myTask = new TaskJson("task", "this is a task",
      DayOfWeek.WEDNESDAY, new ArrayList<>(List.of(false)));

  private TaskJson linkedTask;
  private TaskJson linkedTask2;
  private TaskJson noDescriptionTask;

  /**
   * Sets up a Task for future test cases
   */
  @BeforeEach
  public void setup() {
    ArrayList<Boolean> completion = new ArrayList<>();
    completion.add(false);
    this.myTask = new TaskJson("task", "this is a task", DayOfWeek.WEDNESDAY,
        completion);

    assertEquals(this.myTask.name(), "task");
    assertEquals(this.myTask.description(), "this is a task");
    assertEquals(this.myTask.dayOfWeek(), DayOfWeek.WEDNESDAY);
    assertEquals(this.myTask.completed().size(), 1);

    this.linkedTask = new TaskJson("task", "https://www.google.com",
        DayOfWeek.SUNDAY, completion);
    this.linkedTask2 = new TaskJson("task", "http://www.google.com",
        DayOfWeek.SUNDAY, completion);
    this.noDescriptionTask = new TaskJson("task", "",
        DayOfWeek.SUNDAY, completion);
  }

  /**
   * Tests the toTextFlow method of TaskJson
   */
  @Test
  public void testToTextFlow() {
    ArrayList<Node> textFlow = new ArrayList<>(this.myTask.toTextFlow().getChildren());
    assertEquals(textFlow.get(0).toString(), "Text[text=\"task (\", x=0.0, y=0.0, "
        + "alignment=LEFT, origin=BASELINE, boundsType=LOGICAL, font=Font[name=System Regular, "
        + "family=System, style=Regular, size=12.0], fontSmoothingType=GRAY, fill=0x000000ff]");
    assertEquals(textFlow.get(1).toString(), "Text[text=\"this\", x=0.0, y=0.0, "
        + "alignment=LEFT, origin=BASELINE, boundsType=LOGICAL, font=Font[name=System Regular, "
        + "family=System, style=Regular, size=12.0], fontSmoothingType=GRAY, fill=0x000000ff]");
    assertEquals(textFlow.get(2).toString(), "Text[text=\"is\", x=0.0, y=0.0, alignment=LEFT, "
        + "origin=BASELINE, boundsType=LOGICAL, font=Font[name=System Regular, family=System, "
        + "style=Regular, size=12.0], fontSmoothingType=GRAY, fill=0x000000ff]");
    assertEquals(textFlow.get(3).toString(), "Text[text=\"a\", x=0.0, y=0.0, alignment=LEFT, "
        + "origin=BASELINE, boundsType=LOGICAL, font=Font[name=System Regular, family=System, "
        + "style=Regular, size=12.0], fontSmoothingType=GRAY, fill=0x000000ff]");
    assertEquals(textFlow.get(4).toString(), "Text[text=\"task\", x=0.0, y=0.0, "
        + "alignment=LEFT, origin=BASELINE, boundsType=LOGICAL, font=Font[name=System Regular, "
        + "family=System, style=Regular, size=12.0], fontSmoothingType=GRAY, fill=0x000000ff]");
    assertEquals(textFlow.get(5).toString(), "Text[text=\"), WEDNESDAY; completed: false\", "
        + "x=0.0, y=0.0, alignment=LEFT, origin=BASELINE, boundsType=LOGICAL, "
        + "font=Font[name=System Regular, family=System, style=Regular, size=12.0], "
        + "fontSmoothingType=GRAY, fill=0x000000ff]");
  }

  /**
   * Tests toTextFlow when there is no description
   */
  @Test
  public void testToTextFlowNoDescription() {
    ArrayList<Node> textFlow = new ArrayList<>(this.noDescriptionTask.toTextFlow().getChildren());
    assertEquals(textFlow.get(0).toString(), "Text[text=\"task, SUNDAY; completed: false\","
        + " x=0.0, y=0.0, alignment=LEFT, origin=BASELINE, boundsType=LOGICAL, "
        + "font=Font[name=System Regular, family=System, style=Regular, size=12.0], "
        + "fontSmoothingType=GRAY, fill=0x000000ff]");
  }

  /**
   * Tests the isCompleted method of TaskJson
   */
  @Test
  public void testIsCompleted() {
    assertFalse(this.myTask.isCompleted());
  }

  /**
   * Tests the getName method of TaskJson
   */
  @Test
  public void testGetName() {
    assertEquals(this.myTask.getName(), "task");
  }

  /**
   * Tests the setCompleted method of TaskJson
   */
  @Test
  public void testSetCompleted() {
    assertFalse(this.myTask.isCompleted());
    this.myTask.setCompleted(true);
    assertTrue(this.myTask.isCompleted());
  }

  /**
   * Tests the handleHyperlink method of TaskJson (does not throw an error)
   */
  @Test
  public void testHandleHyperlink() {
    this.myTask.handleHyperlink("https://google.com");
  }
}