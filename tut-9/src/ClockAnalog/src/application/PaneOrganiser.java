package application;

import java.util.Date;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;
import java.util.Calendar;

public class PaneOrganiser {
	private AnchorPane root;
	private Rotate rotateHour;
	private Rotate rotateMinute;
	private Rotate rotateSecond;
	private Rectangle hourHand;
	private Rectangle minuteHand;
	private Rectangle secondHand;
	private Text digital;
	//private Date now;
	
	public PaneOrganiser() {
		this.root = new AnchorPane();
		//now = new Date();
		initRotate();
		initClockBody(root);
		initClockHands(root);
		digital = new Text();
		digital.setX(Constants.DIGITAL_X);
		digital.setY(Constants.DIGITAL_Y);
		//digital.setText(now.toString());
		digital.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.REGULAR, 30));
		root.getChildren().add(digital);
		this.setupTimeline();
	}
	
	private void initRotate() {
		rotateHour = new Rotate();
		rotateHour.setPivotX(Constants.CLOCK_CENTER_X);
		rotateHour.setPivotY(Constants.CLOCK_CENTER_Y);
		rotateHour.setAngle(Constants.ANGLE_HOURS);
		rotateMinute = new Rotate();
		rotateMinute.setPivotX(Constants.CLOCK_CENTER_X);
		rotateMinute.setPivotY(Constants.CLOCK_CENTER_Y);
		rotateMinute.setAngle(Constants.ANGLE_MINUTES);
		rotateSecond = new Rotate();
		rotateSecond.setPivotX(Constants.CLOCK_CENTER_X);
		rotateSecond.setPivotY(Constants.CLOCK_CENTER_Y);
		rotateSecond.setAngle(Constants.ANGLE_SECONDS);
	}
	
	private void initClockBody(AnchorPane root) {
		Rectangle outerBody = new Rectangle();
		outerBody.setX(50);
		outerBody.setY(50);
		outerBody.setWidth(500);
		outerBody.setHeight(600);
		outerBody.setFill(Color.BLACK);
		outerBody.setArcWidth(90);
		outerBody.setArcHeight(90);
		root.getChildren().add(outerBody);
		
		Circle clock = new Circle();
		clock.setCenterX(Constants.CLOCK_CENTER_X);
		clock.setCenterY(Constants.CLOCK_CENTER_Y);
		clock.setRadius(Constants.CLOCK_RADIUS);
		clock.setFill(Color.AZURE);
		root.getChildren().add(clock);
	}
	
	private void initClockHands(AnchorPane root) {
		hourHand = new Rectangle(Constants.CLOCK_CENTER_X, Constants.CLOCK_CENTER_Y - (Constants.HAND_WIDTH/2), Constants.LENGTH_HOUR_HAND, Constants.HAND_WIDTH);
		hourHand.setFill(Color.BLUE);
		hourHand.setArcHeight(50);
		hourHand.setArcWidth(50);
		root.getChildren().add(hourHand);
		minuteHand = new Rectangle(Constants.CLOCK_CENTER_X, Constants.CLOCK_CENTER_Y - (Constants.HAND_WIDTH/2), Constants.LENGTH_MINUTE_HAND, Constants.HAND_WIDTH);
		minuteHand.setFill(Color.GREEN);
		minuteHand.setArcWidth(50);
		minuteHand.setArcHeight(50);
		root.getChildren().add(minuteHand);
		secondHand = new Rectangle(Constants.CLOCK_CENTER_X, Constants.CLOCK_CENTER_Y - (Constants.HAND_WIDTH/2), Constants.LENGTH_SECOND_HAND, Constants.HAND_WIDTH);
		secondHand.setFill(Color.HOTPINK);
		secondHand.setArcWidth(50);
		secondHand.setArcHeight(50);
		root.getChildren().add(secondHand);
		initHandPos();
	}
	
	private void initHandPos() {
		// currently these are 90deg to y axis or parallel to x
		Calendar now = Calendar.getInstance();
		int s = now.get(Calendar.SECOND);
		int m = now.get(Calendar.MINUTE);
		int h = now.get(Calendar.HOUR);
		//System.out.println(m);
		double angleS = s * 6;
		double angleM = m * 6;
		double angleH = (h + (m/60))* 30;
		Rotate rotate1 = new Rotate();
		Rotate rotate2 = new Rotate();
		Rotate rotate3 = new Rotate();
		rotate1.setPivotX(Constants.CLOCK_CENTER_X);
		rotate1.setPivotY(Constants.CLOCK_CENTER_Y);
		rotate1.setAngle(angleS - 90);
		secondHand.getTransforms().add(rotate1);
		rotate2.setPivotX(Constants.CLOCK_CENTER_X);
		rotate2.setPivotY(Constants.CLOCK_CENTER_Y);
		rotate2.setAngle(angleM - 90);
		minuteHand.getTransforms().add(rotate2);
		rotate3.setPivotX(Constants.CLOCK_CENTER_X);
		rotate3.setPivotY(Constants.CLOCK_CENTER_Y);
		rotate3.setAngle(angleH - 90);
		hourHand.getTransforms().add(rotate3);
	}
	
	public AnchorPane getRoot() {
		return root;
	}
	
	public void setupTimeline() {
		KeyFrame kf = new KeyFrame(Duration.seconds(1), new TimeHandler());
		Timeline timeline = new Timeline(kf);
		timeline.setCycleCount(Animation.INDEFINITE);
		timeline.play();
	}
	
	private class TimeHandler implements EventHandler<ActionEvent> {
		public void handle(ActionEvent event) {
			Date now = new Date();
			digital.setText(now.toString());
			hourHand.getTransforms().add(rotateHour);
			minuteHand.getTransforms().add(rotateMinute);
			secondHand.getTransforms().add(rotateSecond);
			
		}
	}
}
