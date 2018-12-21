package JFoenix.control;

import com.jfoenix.controls.JFXRadioButton;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class TestRadio extends Application {

	@Override
	public void start(Stage primaryStage) {
		//Container
		VBox pane = new VBox(50);
		pane.setAlignment(Pos.CENTER);
		pane.setPadding(new Insets(0, 50, 0, 50));
		
		//UI Control
		final ToggleGroup group = new ToggleGroup();
        
		JFXRadioButton javaRadio = new JFXRadioButton("JavaFX");
		javaRadio.setPadding(new Insets(10));
		javaRadio.setToggleGroup(group);
		JFXRadioButton jfxRadio = new JFXRadioButton("JFoenix");
		jfxRadio.setPadding(new Insets(10));
		jfxRadio.setToggleGroup(group);
		
		//Add
		pane.getChildren().add(javaRadio);
		pane.getChildren().add(jfxRadio);
		
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
