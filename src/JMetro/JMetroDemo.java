package JMetro;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import jfxtras.styles.jmetro8.JMetro;

public class JMetroDemo extends Application {

	@Override
	public void start(Stage primaryStage) {
		Button button1 = new Button("Go to the second scene");
		Label label1 = new Label("I am the first scene");
		VBox vBox = new VBox(20);
		vBox.getChildren().addAll(label1, button1);

		Scene scene = new Scene(vBox, 500, 800);
		new JMetro(JMetro.Style.DARK).applyTheme(scene);
		primaryStage.setScene(scene);
		primaryStage.setTitle("I have a title");
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
