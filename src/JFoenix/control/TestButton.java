package JFoenix.control;

import com.jfoenix.controls.JFXButton;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class TestButton extends Application {

	@Override
	public void start(Stage primaryStage) {
		//Container
		VBox pane = new VBox(50);
		pane.setAlignment(Pos.CENTER);
		pane.setPadding(new Insets(0, 50, 0, 50));
		
		//UI Control
		JFXButton button = new JFXButton("Button");
		button.getStyleClass().add("button-raised");
		
		//Add
		pane.getChildren().add(button);
		
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
