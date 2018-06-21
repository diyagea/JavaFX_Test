package i18n.bundledemo;
// imports are ignored.

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Main extends Application {

	@Override
	public void start(Stage primaryStage) {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader();
			fxmlLoader.setResources(ResourceBundle.getBundle("i18n.bundles.MyBundle", new Locale("en", "EN")));
			fxmlLoader.setLocation(this.getClass().getResource("MyView.fxml"));
			Pane root;
			root = fxmlLoader.load();

			primaryStage.setScene(new Scene(root, 800, 600));
			primaryStage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}