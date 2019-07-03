package MaskerPane;

import org.controlsfx.control.MaskerPane;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MaskerPaneSample extends Application {
	private MaskerPane masker = new MaskerPane();

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("ActionGroup Test");
		Button btn1 = new Button("Test1");
		Button btn2 = new Button("Test2");
		CheckBox switchBtn = new CheckBox("enable/disable");

		switchBtn.selectedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				masker.setVisible(newValue);
			}
		});

		VBox vBox = new VBox();
		vBox.getChildren().addAll(btn1, btn2);
		StackPane body = new StackPane();
		body.setPrefWidth(800);
		body.setPrefHeight(600);
		body.getChildren().addAll(vBox, masker);

		BorderPane root = new BorderPane();
		root.setCenter(body);
		root.setTop(switchBtn);
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
