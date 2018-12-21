package JFoenix.control;

import com.jfoenix.controls.JFXSlider;
import com.jfoenix.controls.JFXSlider.IndicatorPosition;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class TestSlider extends Application {

	@Override
	public void start(Stage primaryStage) {
		//Container
		VBox pane = new VBox(50);
		ScrollPane scrollPane = new ScrollPane(pane);
		pane.setAlignment(Pos.CENTER);
		pane.setPadding(new Insets(0, 50, 0, 50));
		
		//UI Control
		JFXSlider hor_left = new JFXSlider();
		hor_left.setMinWidth(500);
		JFXSlider hor_right = new JFXSlider();
		hor_left.setMinWidth(500);
		hor_left.setIndicatorPosition(IndicatorPosition.RIGHT);
		JFXSlider ver_left = new JFXSlider();
		ver_left.setMinHeight(500);
		ver_left.setOrientation(Orientation.VERTICAL);
		JFXSlider ver_right = new JFXSlider();
		ver_right.setMinHeight(500);
		ver_right.setOrientation(Orientation.VERTICAL);
		ver_right.setIndicatorPosition(IndicatorPosition.RIGHT);
		
		//Add
		pane.getChildren().add(hor_left);
		pane.getChildren().add(hor_right);
		pane.getChildren().add(ver_left);
		pane.getChildren().add(ver_right);
		
		//Scene
		Scene scene = new Scene(scrollPane, 500, 500);
		scene.getStylesheets().add(getClass().getResource("All.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.setTitle("I have a title");
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
