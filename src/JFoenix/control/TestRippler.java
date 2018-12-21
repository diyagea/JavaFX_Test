package JFoenix.control;

import com.jfoenix.controls.JFXRippler;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * 
 * @author diyagea
 *
 */
public class TestRippler extends Application {

	@Override
	public void start(Stage primaryStage) {
		//Container
		StackPane pane = new StackPane();
		
		//UI Control
		Label label = new Label("TEST");
		label.setStyle("-fx-background-color:RED; -fx-padding:20");
		JFXRippler rippler = new JFXRippler(label);
		rippler.getStyleClass().add("rippler-base");
		pane.getChildren().add(rippler);
		
		//Scene
		Scene scene = new Scene(pane, 500, 500);
		scene.getStylesheets().add(getClass().getResource("All.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.setTitle("I have a title");
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
