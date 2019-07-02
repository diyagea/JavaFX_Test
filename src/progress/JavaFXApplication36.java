package progress;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class JavaFXApplication36 extends Application {

	@Override
	public void start(Stage primaryStage) {
		AnchorPane root = new AnchorPane();
		ProgressBar pbRed = new ProgressBar(0.4);
		ProgressBar pbGreen = new ProgressBar(0.6);
		pbRed.setLayoutY(10);
		pbGreen.setLayoutY(30);

		pbRed.setStyle("-fx-accent: red;"); // line (1)
		pbGreen.setStyle("-fx-accent: green;"); // line (2)

		root.getChildren().addAll(pbRed, pbGreen);
		Scene scene = new Scene(root, 150, 50);
		primaryStage.setTitle("Hello World!");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
