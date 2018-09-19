package touchScreen;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.TouchEvent;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Main extends Application {
	int touchId = -1;
	double touchx, touchy;

	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage primaryStage) {
		Group root = new Group();
		Scene scene = new Scene(root, 300, 250);

		final Rectangle rect = new Rectangle();
		rect.setCursor(Cursor.HAND);
		rect.setWidth(100);
		rect.setHeight(100);
		root.getChildren().add(rect);

		rect.setOnTouchPressed(new EventHandler<TouchEvent>() {
			@Override
			public void handle(TouchEvent event) {
				if (touchId == -1) {
					touchId = event.getTouchPoint().getId();
					touchx = event.getTouchPoint().getSceneX() - rect.getTranslateX();
					touchy = event.getTouchPoint().getSceneY() - rect.getTranslateY();
				}
				System.out.println(touchId + "-" + touchx + "-" + touchy);
				event.consume();
			}
		});

		rect.setOnTouchReleased(new EventHandler<TouchEvent>() {
			@Override
			public void handle(TouchEvent event) {
				if (event.getTouchPoint().getId() == touchId) {
					touchId = -1;
				}
				System.out.println(touchId + "-" + touchx + "-" + touchy);
				event.consume();
			}
		});

		rect.setOnTouchMoved(new EventHandler<TouchEvent>() {
			@Override
			public void handle(TouchEvent event) {
				if (event.getTouchPoint().getId() == touchId) {
					rect.setTranslateX(event.getTouchPoint().getSceneX() - touchx);
					rect.setTranslateY(event.getTouchPoint().getSceneY() - touchy);
				}
				System.out.println(touchId + "-" + touchx + "-" + touchy);
				event.consume();
			}
		});

		primaryStage.setScene(scene);
		primaryStage.show();
	}
}
