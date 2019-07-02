package alert;

import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.TrayIcon.MessageType;
import java.awt.image.BufferedImage;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Optional;
import java.util.function.Consumer;

import javax.imageio.ImageIO;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Pair;

public class AlertUtil extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		//showSimpleAlert("title", "content");
		//showErrorMessage("error", "error happens!");
		//showErrorMessage(new Exception("Exception！"));
		//showErrorMessage(new Exception("Exception BBB！"), "TITLE", "CONTENT");
		//showTrayMessage("Hello", "MSG");
		//showTrayMessage(String.format("Hello %s!", System.getProperty("user.name")), "Thanks for trying out Library Assistant");
		//showReasonInputAlert("请输入原因！", false);
		inputDialog(true);
	}

	public static void showInfoAlert(String header, String content) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("信息");
		alert.setHeaderText(header);
		alert.setContentText(content);
		alert.showAndWait();
	}

	public static void showWarningAlert(String header, String content) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("警告");
		alert.setHeaderText(header);
		alert.setContentText(content);
		alert.showAndWait();
	}

	public static void showErrorAlert(String header, String content) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("错误");
		alert.setHeaderText(header);
		alert.setContentText(content);
		alert.showAndWait();
	}

	public static void showErrorMessage(Exception ex) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("System Exception");
		alert.setHeaderText("系统异常！");
		alert.setContentText(ex.getLocalizedMessage());

		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		ex.printStackTrace(pw);
		String exceptionText = sw.toString();

		Label label = new Label("异常详细信息如下：");

		TextArea textArea = new TextArea(exceptionText);
		textArea.setEditable(false);
		textArea.setWrapText(true);

		textArea.setMaxWidth(Double.MAX_VALUE);
		textArea.setMaxHeight(Double.MAX_VALUE);
		GridPane.setVgrow(textArea, Priority.ALWAYS);
		GridPane.setHgrow(textArea, Priority.ALWAYS);

		GridPane expContent = new GridPane();
		expContent.setMaxWidth(Double.MAX_VALUE);
		expContent.add(label, 0, 0);
		expContent.add(textArea, 0, 1);

		alert.getDialogPane().setExpandableContent(expContent);
		alert.showAndWait();
	}

	public static void showErrorMessage(Exception ex, String title, String content) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("System Exception");
		alert.setHeaderText(title);
		alert.setContentText(content);

		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		ex.printStackTrace(pw);
		String exceptionText = sw.toString();

		Label label = new Label("异常详细信息如下：");

		TextArea textArea = new TextArea(exceptionText);
		textArea.setEditable(false);
		textArea.setWrapText(true);

		textArea.setMaxWidth(Double.MAX_VALUE);
		textArea.setMaxHeight(Double.MAX_VALUE);
		GridPane.setVgrow(textArea, Priority.ALWAYS);
		GridPane.setHgrow(textArea, Priority.ALWAYS);

		GridPane expContent = new GridPane();
		expContent.setMaxWidth(Double.MAX_VALUE);
		expContent.add(label, 0, 0);
		expContent.add(textArea, 0, 1);

		alert.getDialogPane().setExpandableContent(expContent);
		alert.showAndWait();
	}

	public static void showTrayMessage(String title, String message) {
		try {
			SystemTray tray = SystemTray.getSystemTray();
			BufferedImage image = ImageIO.read(AlertUtil.class.getResource("5.png"));
			TrayIcon trayIcon = new TrayIcon(image, "Library Assistant");
			trayIcon.setImageAutoSize(true);
			trayIcon.setToolTip("Library Assistant");
			tray.add(trayIcon);
			trayIcon.displayMessage(title, message, MessageType.INFO);
			tray.remove(trayIcon);
		} catch (Exception exp) {
			exp.printStackTrace();
		}
	}

	public static String showReasonInputAlert(String header, boolean isRequired) {
		// Create the custom dialog.
		Dialog<String> dialog = new Dialog<>();
		dialog.setTitle("Input Dialog");
		dialog.setHeaderText("\n" + header + "\n\n");
		dialog.initModality(Modality.APPLICATION_MODAL);
		//set dialog Image
		Image i = new Image("file:src/alert/write.png");
		ImageView iv = new ImageView(i);
		iv.setFitWidth(64);
		iv.setFitHeight(64);
		dialog.setGraphic(iv);

		// Get the Stage title.
		Stage stage = (Stage) dialog.getDialogPane().getScene().getWindow();
		Image icon = new Image("file:src/alert/5.png");
		// Add a custom icon.
		stage.getIcons().add(icon);

		// Set the button types.
		ButtonType loginButtonType = new ButtonType("确认", ButtonData.OK_DONE);
		dialog.getDialogPane().getButtonTypes().addAll(loginButtonType, ButtonType.CANCEL);

		// Create the username and password labels and fields.
		GridPane grid = new GridPane();
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(20, 30, 20, 20));

		TextArea textArea = new TextArea();
		textArea.setPrefRowCount(6);

		//put into content pane
		grid.add(new Label("请输入："), 1, 0);
		grid.add(textArea, 1, 1);

		//required text input
		if (isRequired) {
			// Enable/Disable login button depending on whether a username was entered.
			Node loginButton = dialog.getDialogPane().lookupButton(loginButtonType);
			loginButton.setDisable(true);
			// Do some validation (using the Java 8 lambda syntax).
			textArea.textProperty().addListener((observable, oldValue, newValue) -> {
				loginButton.setDisable(newValue.trim().isEmpty());
			});
		}

		// set dialog pane content
		dialog.getDialogPane().setContent(grid);

		// Request focus on the username field by default.
		Platform.runLater(() -> textArea.requestFocus());

		// Convert the result to input value when the ok button clicked.
		dialog.setResultConverter(dialogButton -> {
			if (dialogButton == loginButtonType) {
				return textArea.getText();
			}
			return null;
		});

		Optional<String> result = dialog.showAndWait();

		if (result.isPresent()) {//click confirm
			System.out.println("input:" + result.get());
			return result.get();
		} else {//click cancel or close
			System.out.println("null");
			return null;
		}
	}

	private String inputDialog(boolean isRequired) {
		TextInputDialog dialog = new TextInputDialog();
		dialog.setTitle("Input Dialog");
		dialog.setHeaderText("\n请输入用户名\n\n");
		dialog.setContentText("请输入：");

		//set dialog Image
		Image i = new Image("file:src/alert/write.png");
		ImageView iv = new ImageView(i);
		iv.setFitWidth(64);
		iv.setFitHeight(64);
		dialog.setGraphic(iv);

		//required text input
		if (isRequired) {
			// Enable/Disable login button depending on whether a username was entered.
			Node loginButton = dialog.getDialogPane().lookupButton(ButtonType.OK);
			loginButton.setDisable(true);
			TextField text = (TextField) dialog.getDialogPane().lookup(".text-field");
			// Do some validation (using the Java 8 lambda syntax).
			text.textProperty().addListener((observable, oldValue, newValue) -> {
				loginButton.setDisable(newValue.trim().isEmpty());
			});
		}

		// Traditional way to get the response value.
		Optional<String> result = dialog.showAndWait();
		if (result.isPresent()) {
			System.out.println("Your name: " + result.get());
		}

		if (result.isPresent()) {//click confirm
			System.out.println("input:" + result.get());
			return result.get();
		} else {//click cancel or close
			System.out.println("null");
			return null;
		}
	}

}
