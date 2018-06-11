package controlsFX;

import org.controlsfx.control.ToggleSwitch;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class HelloToggleSwitch extends Application {

	public Pane getPanel() {

		AnchorPane anchorPane = new AnchorPane();
		anchorPane.setPrefHeight(316);
		anchorPane.setPrefWidth(444);

		Label headerLabel = new Label("Toggle Switch");
		headerLabel.getStyleClass().add("header");
		headerLabel.setLayoutX(44);
		headerLabel.setLayoutY(34);

		Label itemTitle1 = new Label("Normal unselected");
		itemTitle1.getStyleClass().add("item-title");
		itemTitle1.setLayoutX(70);
		itemTitle1.setLayoutY(145);
		ToggleSwitch toggleSwitch1 = new ToggleSwitch("Off");
		toggleSwitch1.setLayoutX(70);
		toggleSwitch1.setLayoutY(168);

		Label itemTitle2 = new Label("Disabled");
		itemTitle2.getStyleClass().add("item-title");
		itemTitle2.setLayoutX(271);
		itemTitle2.setLayoutY(145);
		ToggleSwitch toggleSwitch2 = new ToggleSwitch("Off");
		toggleSwitch2.setLayoutX(271);
		toggleSwitch2.setLayoutY(168);
		toggleSwitch2.setDisable(true);

		Label itemTitle3 = new Label("Normal selected");
		itemTitle3.getStyleClass().add("item-title");
		itemTitle3.setLayoutX(70);
		itemTitle3.setLayoutY(227);
		ToggleSwitch toggleSwitch3 = new ToggleSwitch("On");
		toggleSwitch3.setLayoutX(70);
		toggleSwitch3.setLayoutY(250);
		toggleSwitch3.setSelected(true);

		anchorPane.getChildren().addAll(headerLabel, itemTitle1, toggleSwitch1, itemTitle2, toggleSwitch2, itemTitle3,
				toggleSwitch3);
		return anchorPane;

	}

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		Scene scene = new Scene(getPanel(), 500, 500);
		primaryStage.setScene(scene);
		primaryStage.sizeToScene();
		primaryStage.show();
	}

}