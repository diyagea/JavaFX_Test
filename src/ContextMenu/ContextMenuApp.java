package ContextMenu;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * A sample that demonstrates the ColorPicker.
 */
public class ContextMenuApp extends Application {

	//ContextMenu Right Click
	private ContextMenu cm;
	private MenuItem addMenu;

	public Parent createContent(Stage primaryStage) {
		cm = new ContextMenu();
		addMenu = new MenuItem("新建");
		addMenu.setId("add");
		cm.getItems().addAll(addMenu);
		//set action event on context menu
		cm.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				if (event.getButton() == MouseButton.SECONDARY) {
					System.out.println("consuming right release button in ContexMenu filter");
					event.consume();
				}
			}
		});

		//handle action for click menu item
		cm.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				String menuID = ((MenuItem) event.getTarget()).getId();

				switch (menuID) {
				case "add":
					//coloredLabel.setText("add" + i++);
					System.out.println("add");
					break;
				}
			}
		});

		Image img = new Image("file:src/alert/write.png");

		GridPane grid = new GridPane();
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				Label coloredLabel = new Label("Colors");
				grid.add(coloredLabel, i, j);

				coloredLabel.setGraphic(new ImageView(img));
				coloredLabel.setStyle("-fx-background-color:red");
				coloredLabel.setContentDisplay(ContentDisplay.TOP);
				
				//add right mouse action event to table view
				coloredLabel.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent e) {
						if (e.getButton() == MouseButton.SECONDARY) {
							cm.show(primaryStage, e.getScreenX(), e.getScreenY());
						}
					}
				});
			}
		}

		return grid;
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setScene(new Scene(createContent(primaryStage)));
		primaryStage.show();
	}

	/**
	 * Java main for when running without JavaFX launcher
	 */
	public static void main(String[] args) {
		launch(args);
	}
}