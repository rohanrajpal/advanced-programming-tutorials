import javafx.scene.control.*;
import javafx.event.*;
import javafx.util.Duration;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import java.util.Date;

import javafx.scene.layout.VBox;

public class PaneOrganizer {

  private VBox _root;
  private Label _label;

  public PaneOrganizer() {
    _root = new VBox();
    _label = new Label();
    _root.getChildren().add(_label);
    this.setupTimeline();
  }

  public VBox getRoot() {
    return _root;
  }

  public void setupTimeline() {
    KeyFrame kf = new KeyFrame(Duration.seconds(1), new TimeHandler());
    Timeline timeline = new Timeline(kf);
    timeline.setCycleCount(Animation.INDEFINITE);
    timeline.play();
    timeline.play();
  }

  private class TimeHandler implements EventHandler<ActionEvent> {
    public void handle(ActionEvent event) {
      Date now = new Date();
      // toString converts the Date into a
      // String with year, day, time etc.
      _label.setText(now.toString());
    }
  }
}
