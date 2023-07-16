package cs3500.pa05.model;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;

/**
 * represents an empty application
 */
public class EmptyApplication extends Application {
  /**
   * Starts the GUI for the bullet journal
   *
   * @param stage the stage to add elements to
   */
  @Override
  public void start(Stage stage) {
    stage.close();
    Platform.exit();
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
