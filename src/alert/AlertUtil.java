package alert;

import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.TrayIcon.MessageType;
import java.awt.image.BufferedImage;
import java.io.PrintWriter;
import java.io.StringWriter;

import javax.imageio.ImageIO;

import javafx.application.Application;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;

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
		showTrayMessage("Hello", "MSG");
		//showTrayMessage(String.format("Hello %s!", System.getProperty("user.name")), "Thanks for trying out Library Assistant");
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

}
