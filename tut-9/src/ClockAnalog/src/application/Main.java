package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) throws Exception {
		try {
			PaneOrganiser pane = new PaneOrganiser();
			AnchorPane root = pane.getRoot();
			Scene scene = new Scene(root,Constants.PANE_WIDTH,Constants.PANE_HEIGHT);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Dadaji ki Ghadi");
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
