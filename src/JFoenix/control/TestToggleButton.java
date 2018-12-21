package JFoenix.control;

import com.jfoenix.controls.JFXToggleButton;
import com.jfoenix.controls.JFXToggleNode;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class TestToggleButton extends Application {

	@Override
	public void start(Stage primaryStage) {
		//Container
		VBox pane = new VBox(50);
		pane.setAlignment(Pos.CENTER);
		pane.setPadding(new Insets(0, 50, 0, 50));

		//UI Control
		JFXToggleButton toggleButton = new JFXToggleButton();

		JFXToggleNode node = new JFXToggleNode();
		node.setText("123");
		Text value = new Text("HEART");
		node.setGraphic(value);

		//Add
		pane.getChildren().add(toggleButton);

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
