package ColorPicker;

import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * A sample that demonstrates the ColorPicker.
 */
public class ColorPickerApp extends Application {

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Parent createContent() {
		//初始化控件
		final ColorPicker colorPicker = new ColorPicker(Color.GREEN);
		final Label coloredText = new Label("Colors");
		coloredText.setFont(new Font(53));
		final Button coloredButton = new Button("Colored Control");
		
		//init set Color
		Color c = colorPicker.getValue();
		coloredText.setTextFill(c);
		coloredText.setStyle(createRGBString(c));
		coloredButton.setStyle(createRGBString(c));

		//Color Pick ActionEvent
		colorPicker.setOnAction(new EventHandler() {
			@Override
			public void handle(Event t) {
				Color newColor = colorPicker.getValue();
				coloredText.setTextFill(newColor);
				coloredText.setStyle(createRGBString(newColor));
				coloredButton.setStyle(createRGBString(newColor));
			}
		});

		VBox outerVBox = new VBox(coloredText, coloredButton, colorPicker);
		outerVBox.setAlignment(Pos.CENTER);
		outerVBox.setSpacing(20);
		outerVBox.setPrefSize(300, 300);

		return outerVBox;
	}

	private String createRGBString(Color c) {
//		return String.format("-fx-base: rgb(%f,%f,%f);", (c.getRed() * 255), (c.getGreen() * 255), (c.getBlue() * 255));
		return String.format("-fx-background-color : rgb(%f,%f,%f);", (c.getRed() * 255), (c.getGreen() * 255), (c.getBlue() * 255));
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setScene(new Scene(createContent()));
		primaryStage.show();
	}

	/**
	 * Java main for when running without JavaFX launcher
	 */
	public static void main(String[] args) {
		launch(args);
	}
}