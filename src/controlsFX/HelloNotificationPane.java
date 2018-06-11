package controlsFX;

import org.controlsfx.control.NotificationPane;
import org.controlsfx.control.action.Action;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class HelloNotificationPane extends Application {
	private NotificationPane notificationPane;
	private CheckBox cbUseDarkTheme;
	private CheckBox cbHideCloseBtn;
	private TextField textField;

	public Node getPanel() {
		notificationPane = new NotificationPane();

		String imagePath = this.getClass().getResource("notification-pane-warning.png").toExternalForm();
		ImageView image = new ImageView(imagePath);
		notificationPane.setGraphic(image);

		notificationPane.getActions().addAll(new Action("Sync", ae -> {
			// do sync

			// then hide...
			notificationPane.hide();
		}));

		Button showBtn = new Button("Show / Hide");
		showBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				if (notificationPane.isShowing()) {
					notificationPane.hide();
				} else {
					notificationPane.show();
				}
			}
		});

		CheckBox cbSlideFromTop = new CheckBox("Slide from top");
		cbSlideFromTop.setSelected(true);
		notificationPane.showFromTopProperty().bind(cbSlideFromTop.selectedProperty());

		cbUseDarkTheme = new CheckBox("Use dark theme");
		cbUseDarkTheme.setSelected(false);
		cbUseDarkTheme.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				updateBar();
			}
		});

		cbHideCloseBtn = new CheckBox("Hide close button");
		cbHideCloseBtn.setSelected(false);
		cbHideCloseBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				notificationPane.setCloseButtonVisible(!cbHideCloseBtn.isSelected());
			}
		});

		textField = new TextField();
		textField.setPromptText("Type text to display and press Enter");
		textField.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				notificationPane.show(textField.getText());
			}
		});

		VBox root = new VBox(10);
		root.setPadding(new Insets(50, 0, 0, 10));
		root.getChildren().addAll(showBtn, cbSlideFromTop, cbUseDarkTheme, cbHideCloseBtn, textField);

		notificationPane.setContent(root);
		updateBar();

		return notificationPane;

	}
	
    private void updateBar() {
        boolean useDarkTheme = cbUseDarkTheme.isSelected();
        
        if (useDarkTheme) {
            notificationPane.setText("Hello World! Using the dark theme");
            notificationPane.getStyleClass().add(NotificationPane.STYLE_CLASS_DARK);
        } else {
            notificationPane.setText("Hello World! Using the light theme");
            notificationPane.getStyleClass().remove(NotificationPane.STYLE_CLASS_DARK);
        }
    }

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		StackPane pane  = new StackPane();
		pane.getChildren().add(getPanel());
		pane.setPrefHeight(500);
		pane.setPrefWidth(500);
		Scene scene = new Scene(pane);
		primaryStage.setScene(scene);
		primaryStage.sizeToScene();
		primaryStage.show();
	}

}