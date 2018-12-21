package JFoenix.control;

import com.jfoenix.controls.JFXColorPicker;
import com.jfoenix.controls.JFXDatePicker;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class TestPicker extends Application {

	@Override
	public void start(Stage primaryStage) {
		//Container
		StackPane pane = new StackPane();
		pane.setAlignment(Pos.CENTER);
		pane.setPadding(new Insets(0, 50, 0, 50));
		
		//UI Control
		JFXDatePicker blueDatePicker = new JFXDatePicker();
		//blueDatePicker.setDefaultColor(Color.valueOf("#3f51b5"));
		blueDatePicker.setOverLay(true);
		//blueDatePicker.setShowTime(true);
		 
		JFXColorPicker colorPicker = new JFXColorPicker();
		
		//Add
		pane.getChildren().add(blueDatePicker);
		//pane.getChildren().add(colorPicker);
		
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
