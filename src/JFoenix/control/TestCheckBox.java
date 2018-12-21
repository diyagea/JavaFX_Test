package JFoenix.control;

import com.jfoenix.controls.JFXCheckBox;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class TestCheckBox extends Application {

	@Override
	public void start(Stage primaryStage) {
		//Container
		StackPane pane = new StackPane();
		
		//UI Control
		JFXCheckBox checkBox = new JFXCheckBox("JFX CheckBox");
		checkBox.getStyleClass().add("custom-CheckBox");
		checkBox.setSelected(true);
		pane.getChildren().add(checkBox);
		
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
