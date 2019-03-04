package theme.hpt;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 *
 * @author allen
 */
public class Demo extends Application {

	@Override
	public void init() {
		Font.loadFont(Demo.class.getResourceAsStream("Roboto-Thin.ttf"), 10).getName();
		Font.loadFont(Demo.class.getResourceAsStream("Roboto-Light.ttf"), 10).getName();
	}

	@Override
	public void start(Stage primaryStage) throws IOException {
		BorderPane root = new BorderPane();
		Parent content = FXMLLoader.load(getClass().getResource("Demo.fxml"));
		Scene scene = new Scene(root, 1280, 800);
		root.setCenter(content);

		scene.getStylesheets().add(getClass().getResource("flatred.css").toExternalForm());

		primaryStage.setTitle("HPTool");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}