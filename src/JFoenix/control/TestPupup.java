package JFoenix.control;

import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXPopup;
import com.jfoenix.controls.JFXPopup.PopupHPosition;
import com.jfoenix.controls.JFXPopup.PopupVPosition;
import com.jfoenix.controls.JFXRippler;
import com.jfoenix.controls.JFXRippler.RipplerMask;
import com.jfoenix.controls.JFXRippler.RipplerPos;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class TestPupup extends Application {

	@Override
	public void start(Stage primaryStage) {
		//Container
		StackPane pane = new StackPane();
		pane.setAlignment(Pos.CENTER);
		pane.setPadding(new Insets(0, 50, 0, 50));
		
		StackPane rootStackPane = new StackPane();
		
		//UI Control
		StackPane root = new StackPane();
		JFXListView<String> list = new JFXListView<String>();
		for(int i = 1 ; i < 5 ; i++) list.getItems().add("Item " + i);
		 
		JFXHamburger burger = new JFXHamburger();
		burger.setPadding(new Insets(10,5,10,5));
		JFXRippler popupSource = new JFXRippler(burger,RipplerMask.CIRCLE,RipplerPos.BACK);
		 
		//Add
		
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
