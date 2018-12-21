package JFoenix.control;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class TestComboBox extends Application {

	@Override
	public void start(Stage primaryStage) {
		//Container
		VBox pane = new VBox(50);
		pane.setAlignment(Pos.CENTER);
		
		//UI Control
		JFXComboBox<Label> jfxCombo = new JFXComboBox<Label>();
		 
		jfxCombo.getItems().add(new Label("Java 1.8"));
		jfxCombo.getItems().add(new Label("Java 1.7"));
		jfxCombo.getItems().add(new Label("Java 1.6"));
		jfxCombo.getItems().add(new Label("Java 1.5"));
		 
		jfxCombo.setPromptText("Select Java Version");
		
		jfxCombo.getStyleClass().add("custom-ComboBox");
		pane.getChildren().add(jfxCombo);
		
		JFXButton button = new JFXButton("Button");
		button.getStyleClass().add("button-raised");
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
