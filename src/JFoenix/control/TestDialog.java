package JFoenix.control;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class TestDialog extends Application {

	@Override
	public void start(Stage primaryStage) {
		//Container
		StackPane pane = new StackPane();
		pane.setAlignment(Pos.CENTER);
		pane.setPadding(new Insets(0, 50, 0, 50));
		
		StackPane rootStackPane = new StackPane();
		
		//UI Control
		JFXDialog dialog = new JFXDialog();
		dialog.setContent(new Label("Content"));
		JFXButton button = new JFXButton("Button");
		button.getStyleClass().add("button-raised");
		button.setOnAction((action)->{
			System.out.println("click");
			dialog.show(rootStackPane);
		});
		
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
