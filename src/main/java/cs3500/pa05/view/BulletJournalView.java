package cs3500.pa05.view;

import cs3500.pa05.controller.BulletJournalController;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

/**
 * Represents a bullet journal GUI cs3500.pa05.view
 */
public class BulletJournalView implements BujoView {
  FXMLLoader loader;

  /**
   * Instantiates FXML and loads it
   *
   * @param controller cs3500.pa05.controller for the bullet journal
   */
  public BulletJournalView(BulletJournalController controller) {
    this.loader = new FXMLLoader();
    this.loader.setLocation(getClass().getClassLoader().getResource("pa05bujo.fxml"));
    this.loader.setController(controller);
  }

  /**
   * Loads a scene from the bullet journal GUI layout
   *
   * @return the scene
   */
  public Scene load() {
    try {
      return this.loader.load();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
