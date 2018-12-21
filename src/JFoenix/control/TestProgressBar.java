package JFoenix.control;

import com.jfoenix.controls.JFXProgressBar;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class TestProgressBar extends Application {

	@Override
	public void start(Stage primaryStage) {
		//Container
		StackPane pane = new StackPane();
		
		//UI Control
		JFXProgressBar jfxBar = new JFXProgressBar();
		jfxBar.setPrefWidth(500);
		JFXProgressBar jfxBarInf = new JFXProgressBar();
		jfxBarInf.setPrefWidth(500);
		jfxBarInf.setProgress(-1.0f);
		Timeline timeline = new Timeline(
		    new KeyFrame(Duration.ZERO, new KeyValue(jfxBar.progressProperty(), 0), new KeyValue(jfxBar.progressProperty(), 0)),
		    new KeyFrame(Duration.seconds(2), new KeyValue(jfxBar.progressProperty(), 1), new KeyValue(jfxBar.progressProperty(), 1)));
		timeline.setCycleCount(Timeline.INDEFINITE);
		timeline.play();
		
		//Add
		pane.getChildren().add(jfxBar);
		
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
