package theme;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;

/**
 *
 * @author cdea
 */
public class DarkThemeTester extends Application {

	@Override
	public void start(Stage primaryStage) throws IOException {
		BorderPane root = new BorderPane();
		Parent content = FXMLLoader.load(getClass().getResource("DarkTheme.fxml"));
		Scene scene = new Scene(root, 650, 550, Color.WHITE);
		root.setCenter(content);

		primaryStage.setTitle("Look N Feel Chooser");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}