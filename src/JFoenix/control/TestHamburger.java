package JFoenix.control;

import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.*;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class TestHamburger extends Application {

	@Override
	public void start(Stage primaryStage) {
		//Container
		VBox pane = new VBox(50);
		pane.setAlignment(Pos.CENTER);

		//UI Control
		JFXHamburger h1 = new JFXHamburger();
		h1.getStyleClass().add("jfx-hamburger-icon");

		//HamburgerBasicCloseTransition
		//HamburgerSlideCloseTransition
		//HamburgerNextArrowBasicTransition
		//HamburgerBackArrowBasicTransition
		HamburgerBackArrowBasicTransition burgerTask = new HamburgerBackArrowBasicTransition(h1);
		burgerTask.setRate(-1);
		h1.addEventHandler(MouseEvent.MOUSE_PRESSED, (e) -> {
			burgerTask.setRate(burgerTask.getRate() * -1);
			burgerTask.play();
		});
		pane.getChildren().add(h1);

		//Scene
		Scene scene = new Scene(pane, 500, 500);
		scene.getStylesheets().add(getClass().getResource("All.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.setTitle("I have a title");
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
