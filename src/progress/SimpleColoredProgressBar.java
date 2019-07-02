package progress;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

// shows multiple progress bars drawn in different colors.
public class SimpleColoredProgressBar extends Application {
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) {
		final VBox layout = new VBox(10);
		layout.setAlignment(Pos.CENTER);
		layout.getChildren().setAll(new ColoredProgressBar("red-bar", 0.2), new ColoredProgressBar("orange-bar", 0.4), new ColoredProgressBar("yellow-bar", 0.6), new ColoredProgressBar("green-bar", 0.8));
		stage.setScene(new Scene(layout));
		stage.show();
	}

	class ColoredProgressBar extends ProgressBar {
		ColoredProgressBar(String styleClass, double progress) {
			super(progress);
			getStyleClass().add(styleClass);
		}
	}
}