package FileChoose;

import java.io.File;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

public class ExtensionFilterExample extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(final Stage primaryStage) {
		primaryStage.setTitle("Extension Filter Example");

		final Label fileLabel = new Label();

		Button btn = new Button("Open FileChooser");
		btn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				FileChooser fileChooser = new FileChooser();

				// Set extension filter
				ExtensionFilter extFilter = new ExtensionFilter("TEXT files (*.txt)", "*.txt");
				ExtensionFilter extFilter2 = new ExtensionFilter("CAD files (*.dxf)", "*.dxf");
				fileChooser.getExtensionFilters().addAll(extFilter, extFilter2);

				// Show open file dialog
				File file = fileChooser.showOpenDialog(primaryStage);
				if (file != null) {
					fileLabel.setText(file.getPath());
				}
			}
		});

		VBox vBox = new VBox(30);
		vBox.getChildren().addAll(fileLabel, btn);
		vBox.setAlignment(Pos.BASELINE_CENTER);

		StackPane root = new StackPane();
		root.getChildren().add(vBox);
		primaryStage.setScene(new Scene(root, 300, 250));
		primaryStage.show();
	}
}