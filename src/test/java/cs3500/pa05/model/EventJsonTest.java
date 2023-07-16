package cs3500.pa05.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import javafx.scene.Node;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test class for EventJson
 */
public class EventJsonTest {
  public EventJson myEvent = new EventJson("event", "this is an event",
      DayOfWeek.TUESDAY, "1:00", "1");
  public EventJson linkedEvent;
  public EventJson linkedEvent2;
  public EventJson noDescriptionEvent;
  private TaskJson linkedTask;
  private TaskJson linkedTask2;

  /**
   * Sets up the test EventJson for future test cases
   */
  @BeforeEach
  public void setup() {
    this.myEvent = new EventJson("event", "this is an event", DayOfWeek.TUESDAY,
        "1:00", "1");

    assertEquals(this.myEvent.name(), "event");
    assertEquals(this.myEvent.description(), "this is an event");
    assertEquals(this.myEvent.dayOfWeek(), DayOfWeek.TUESDAY);
    assertEquals(this.myEvent.startTime().toString(), "1:00");
    assertEquals(this.myEvent.duration().toString(), "1");

    this.linkedEvent = new EventJson("event", "https://www.google.com",
        DayOfWeek.SUNDAY, "2:30", "2 weeks");

    this.linkedEvent2 = new EventJson("event", "http://www.google.com",
        DayOfWeek.SUNDAY, "2:30", "2 weeks");

    this.noDescriptionEvent = new EventJson("event", "", DayOfWeek.SUNDAY,
        "1:20 AM", "2 hours");

    ArrayList<Boolean> completion = new ArrayList<>();
    completion.add(false);
    this.linkedTask = new TaskJson("task", "https://www.google.com",
        DayOfWeek.SUNDAY, completion);
    this.linkedTask2 = new TaskJson("task", "http://www.google.com",
        DayOfWeek.SUNDAY, completion);
  }

  /**
   * Tests the toTextFlow() method of EventJson
   */
  @Test
  public void testToTextFlow() {
    ArrayList<Node> textFlowChildren = new ArrayList<>(this.myEvent.toTextFlow().getChildren());
    assertEquals(textFlowChildren.get(0).toString(), "Text[text=\"event (\", x=0.0, y=0.0, "
        + "alignment=LEFT, origin=BASELINE, boundsType=LOGICAL, font=Font[name=System Regular, "
        + "family=System, style=Regular, size=12.0], fontSmoothingType=GRAY, fill=0x000000ff]");
    assertEquals(textFlowChildren.get(1).toString(), "Text[text=\"this\", x=0.0, y=0.0, "
        + "alignment=LEFT, origin=BASELINE, boundsType=LOGICAL, font=Font[name=System Regular, "
        + "family=System, style=Regular, size=12.0], fontSmoothingType=GRAY, fill=0x000000ff]");
    assertEquals(textFlowChildren.get(2).toString(), "Text[text=\"is\", x=0.0, y=0.0, "
        + "alignment=LEFT, origin=BASELINE, boundsType=LOGICAL, font=Font[name=System Regular, "
        + "family=System, style=Regular, size=12.0], fontSmoothingType=GRAY, fill=0x000000ff]");
    assertEquals(textFlowChildren.get(3).toString(), "Text[text=\"an\", x=0.0, y=0.0, "
        + "alignment=LEFT, origin=BASELINE, boundsType=LOGICAL, font=Font[name=System Regular, "
        + "family=System, style=Regular, size=12.0], fontSmoothingType=GRAY, fill=0x000000ff]");
    assertEquals(textFlowChildren.get(4).toString(), "Text[text=\"event\", x=0.0, y=0.0, "
        + "alignment=LEFT, origin=BASELINE, boundsType=LOGICAL, font=Font[name=System Regular, "
        + "family=System, style=Regular, size=12.0], fontSmoothingType=GRAY, fill=0x000000ff]");
    assertEquals(textFlowChildren.get(5).toString(), "Text[text=\"), TUESDAY, 1:00; 1\", "
        + "x=0.0, y=0.0, alignment=LEFT, origin=BASELINE, boundsType=LOGICAL, "
        + "font=Font[name=System Regular, family=System, style=Regular, size=12.0], "
        + "fontSmoothingType=GRAY, fill=0x000000ff]");
  }

  /**
   * Tests the toTextFlow() method when there's no description
   */
  @Test
  public void testToTextFlowNoDescription() {
    ArrayList<Node> textFlowChildren = new ArrayList<>(this.noDescriptionEvent.toTextFlow()
        .getChildren());
    assertEquals(textFlowChildren.get(0).toString(), "Text[text=\"event, SUNDAY, 1:20 AM; "
        + "2 hours\", x=0.0, y=0.0, alignment=LEFT, origin=BASELINE, boundsType=LOGICAL, "
        + "font=Font[name=System Regular, family=System, style=Regular, size=12.0], "
        + "fontSmoothingType=GRAY, fill=0x000000ff]");
  }

  /**
   * Tests the toTextFlow() method when there's no hyperlink
   */
  @Test
  public void testToTextFlowHyperlink() {
    EmptyApplication.main(new String[1]);
    ArrayList<Node> textFlowChildren = new ArrayList<>(this.linkedEvent.toTextFlow().getChildren());
    assertEquals(textFlowChildren.get(0).toString(), "Text[text=\"event (\", x=0.0, y=0.0, "
        + "alignment=LEFT, origin=BASELINE, boundsType=LOGICAL, font=Font[name=System Regular, "
        + "family=System, style=Regular, size=12.0], fontSmoothingType=GRAY, fill=0x000000ff]");
    assertTrue(textFlowChildren.get(1).toString().contains("https://www.google.com"));
    assertEquals(textFlowChildren.get(2).toString(), "Text[text=\"), SUNDAY, 2:30; "
        + "2 weeks\", "
        + "x=0.0, y=0.0, alignment=LEFT, origin=BASELINE, boundsType=LOGICAL, "
        + "font=Font[name=System Regular, family=System, style=Regular, size=12.0], "
        + "fontSmoothingType=GRAY, fill=0x000000ff]");

    textFlowChildren = new ArrayList<>(this.linkedEvent2.toTextFlow().getChildren());
    assertEquals(textFlowChildren.get(0).toString(), "Text[text=\"event (\", x=0.0, y=0.0, "
        + "alignment=LEFT, origin=BASELINE, boundsType=LOGICAL, font=Font[name=System Regular, "
        + "family=System, style=Regular, size=12.0], fontSmoothingType=GRAY, fill=0x000000ff]");
    assertTrue(textFlowChildren.get(1).toString().contains("http://www.google.com"));
    assertEquals(textFlowChildren.get(2).toString(), "Text[text=\"), SUNDAY, 2:30; "
        + "2 weeks\", "
        + "x=0.0, y=0.0, alignment=LEFT, origin=BASELINE, boundsType=LOGICAL, "
        + "font=Font[name=System Regular, family=System, style=Regular, size=12.0], "
        + "fontSmoothingType=GRAY, fill=0x000000ff]");

    textFlowChildren = new ArrayList<>(this.linkedTask.toTextFlow().getChildren());
    assertEquals(textFlowChildren.get(0).toString(), "Text[text=\"task (\", x=0.0, y=0.0, "
        + "alignment=LEFT, origin=BASELINE, boundsType=LOGICAL, font=Font[name=System Regular, "
        + "family=System, style=Regular, size=12.0], fontSmoothingType=GRAY, fill=0x000000ff]");
    assertTrue(textFlowChildren.get(1).toString().contains("https://www.google.com"));
    assertEquals(textFlowChildren.get(2).toString(), "Text[text=\"), SUNDAY; completed: false"
        + "\", x=0.0, y=0.0, alignment=LEFT, origin=BASELINE, boundsType=LOGICAL, "
        + "font=Font[name=System Regular, family=System, style=Regular, size=12.0], "
        + "fontSmoothingType=GRAY, fill=0x000000ff]");

    textFlowChildren = new ArrayList<>(this.linkedTask2.toTextFlow().getChildren());
    assertEquals(textFlowChildren.get(0).toString(), "Text[text=\"task (\", x=0.0, y=0.0, "
        + "alignment=LEFT, origin=BASELINE, boundsType=LOGICAL, font=Font[name=System Regular, "
        + "family=System, style=Regular, size=12.0], fontSmoothingType=GRAY, fill=0x000000ff]");
    assertTrue(textFlowChildren.get(1).toString().contains("http://www.google.com"));
    assertEquals(textFlowChildren.get(2).toString(), "Text[text=\"), SUNDAY; completed: false"
        + "\", x=0.0, y=0.0, alignment=LEFT, origin=BASELINE, boundsType=LOGICAL, "
        + "font=Font[name=System Regular, family=System, style=Regular, size=12.0], "
        + "fontSmoothingType=GRAY, fill=0x000000ff]");
  }

  /**
   * Tests the handleHyperlink method of EventJson (does not throw an error)
   */
  @Test
  public void testHandleHyperlink() {
    this.myEvent.handleHyperlink("https://google.com");
  }
}