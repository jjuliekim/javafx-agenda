package cs3500.pa05.view;

import javafx.scene.Scene;

/**
 * Represents a GUI view for the bullet journal application
 */
public interface BujoView {
  /**
   * Loads a scene from a Bullet Journal GUI layout.
   *
   * @return the layout
   */
  Scene load() throws IllegalStateException;
}
