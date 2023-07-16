package cs3500.pa05;

import cs3500.pa05.controller.BulletJournalController;
import cs3500.pa05.model.Day;
import cs3500.pa05.model.DayOfWeek;
import cs3500.pa05.view.BujoView;
import cs3500.pa05.view.BulletJournalView;
import cs3500.pa05.view.SplashView;
import java.util.ArrayList;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * Represents a Bullet Journal application
 */
public class Driver extends Application {
  /**
   * Starts the GUI for the bullet journal
   *
   * @param stage the stage to add elements to
   */
  @Override
  public void start(Stage stage) {
    ArrayList<Day> week = new ArrayList<>();
    for (DayOfWeek day : DayOfWeek.values()) {
      week.add(new Day(day));
    }
    BulletJournalController controller = new BulletJournalController(week);
    BujoView view = new BulletJournalView(controller);

    // show splash screen before bullet journal
    stage.setScene(new SplashView().load());
    stage.show();

    Timeline timeline = new Timeline();
    KeyFrame keyFrame = new KeyFrame(Duration.seconds(2), // after 2 seconds show bujo
        event -> {
          Scene scene = view.load();
          stage.setScene(scene);
          controller.run(scene);
          stage.show();
        });
    timeline.getKeyFrames().add(keyFrame);
    timeline.play();

  }

  /**
   * Calls launch on the application. Entry point for the bullet journal application
   *
   * @param args command line arguments
   */
  public static void main(String[] args) {
    launch();
  }
}
