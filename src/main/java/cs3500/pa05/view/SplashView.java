package cs3500.pa05.view;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

/**
 * Represents a splash screen
 */
public class SplashView implements BujoView {
  FXMLLoader loader;

  /**
   * Instantiates FXML and loads it
   */
  public SplashView() {
    this.loader = new FXMLLoader();
    this.loader.setLocation(getClass().getClassLoader().getResource("pa05Splash.fxml"));
  }

  /**
   * Loads the scene
   *
   * @return the scene
   * @throws IOException throws if it can not load FXML
   */
  public Scene load() {
    try {
      return this.loader.load();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}

