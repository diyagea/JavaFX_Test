package JFoenix.control;

import com.jfoenix.controls.JFXTabPane;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class TestTabPane extends Application {

	@Override
	public void start(Stage primaryStage) {
		//Container
		VBox pane = new VBox(50);
		pane.setAlignment(Pos.CENTER);
		pane.setPadding(new Insets(0, 50, 0, 50));

		//UI Control

		JFXTabPane tabPane = new JFXTabPane();
		tabPane.setPrefSize(300, 200);
		Tab tab = new Tab();
		tab.setText("Tab 1");
		tab.setContent(new Label("Content"));
		tabPane.getTabs().add(tab);

		//Add
		pane.getChildren().add(tabPane);

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
