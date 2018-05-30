package Scene;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ChangeScene extends Application{
	Scene scene1;
	Scene scene2;

	@Override
	public void start(Stage primaryStage) {
		Button button1 = new Button("Go to the second scene");
		Label label1 = new Label("I am the first scene");
		VBox layout1 = new VBox();
		layout1.getChildren().addAll(label1, button1);
		scene1 = new Scene(layout1, 400, 300);

		Button button2 = new Button("Go to the first scene");
		StackPane layout2 = new StackPane();
		layout2.getChildren().add(button2);
		scene2 = new Scene(layout2, 400, 300);

		button1.setOnAction(e -> {
			primaryStage.setScene(scene2);
		});
		button2.setOnAction(e -> {
			primaryStage.setScene(scene1);
		});

		//需要初始化一个stage上的scene，不要忘了QAQ
		primaryStage.setScene(scene1);
		primaryStage.setTitle("I have a title");
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
