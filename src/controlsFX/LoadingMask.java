package controlsFX;

import org.controlsfx.control.MaskerPane;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class LoadingMask extends Application {
	MaskerPane mask = new MaskerPane();

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		VBox vbox = new VBox();
		Button btn = new Button("Show/hide");
		StackPane pane = new StackPane();
		pane.setPrefWidth(400);
		pane.setPrefHeight(400);
		pane.getChildren().addAll(mask);

		btn.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				if (mask.isVisible()) {
					mask.setVisible(false);
				} else {
					mask.setVisible(true);
				}
			}
		});
		
		vbox.getChildren().addAll(btn, pane);
		Scene scene = new Scene(vbox, 500, 500);
		primaryStage.setScene(scene);
		primaryStage.sizeToScene();
		primaryStage.show();
	}

}