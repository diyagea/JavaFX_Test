package controlsFX;

import org.controlsfx.control.MaskerPane;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class LoadingMask extends Application {
	MaskerPane masker = new MaskerPane();

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		masker.setVisible(false);
		Button btn = new Button("Show/hide");
		StackPane pane = new StackPane();
		pane.setPrefWidth(400);
		pane.setPrefHeight(400);
		pane.getChildren().add(btn);
		pane.getChildren().add(masker);

		btn.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				//Not-JavaFX-Thread-Exception
				new Thread() {
					@Override
					public synchronized void run() {
						masker.setVisible(true);
						System.out.println("start");
						boolean flag = true;
						int i = 0;
						while (flag) {
							i++;
							if (i == 100000) {
								flag = false;
							}
							System.out.println(i);
						}
						System.out.println("end");
						masker.setVisible(false);
					}
				}.start();
			}
		});

		Scene scene = new Scene(pane, 500, 500);
		primaryStage.setScene(scene);
		primaryStage.sizeToScene();
		primaryStage.show();
	}

}