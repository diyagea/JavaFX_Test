package controlsFX;

import org.controlsfx.control.MaskerPane;
import org.controlsfx.control.NotificationPane;
import org.controlsfx.control.Notifications;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Duration;

public class ControlFXUtil {

	public static Node getNode() {
		return null;
	}
	
	/**
	 * show a shadow mask
	 */
	public static MaskerPane getLoadingMask() {
		return new MaskerPane();
	}

	/**
	 * show a notification
	 * @param alertType
	 * @param pos
	 * @param imgName - image file name
	 * @param title
	 * @param content
	 * @param duringTime - show time in Millisecond
	 * @param isDark - dark color
	 * @param owner
	 */
	public static void showNotification(AlertType alertType, Pos pos,  String imgName, String title, String content, double duringTime, boolean isDark, Stage owner) {
		ImageView image = null;
		
		if (imgName.length() > 0) {
			String imagePath = ControlFXUtil.class.getResource(imgName).toExternalForm();
			image = new ImageView(imagePath);
		}

		Notifications notificationBuilder = Notifications.create()
				.title(title)
				.text(content)
				.graphic(image)
				.hideAfter(Duration.millis(duringTime))
				.position(pos)
				.onAction(e -> System.out.println("Notification clicked on!"));

		if (owner != null) {
			notificationBuilder.owner(owner);
		}

		if (isDark) {
			notificationBuilder.darkStyle();
		}
		
		switch (alertType) {
			case INFORMATION:
				notificationBuilder.showInformation();
				break;
			case CONFIRMATION:
				notificationBuilder.showConfirm();
				break;
			case WARNING:
				notificationBuilder.showWarning();
				break;
			case ERROR:
				notificationBuilder.showError();
				break;
			default:
				notificationBuilder.show();
		}
	}

	/**
	 * 
	 * @param content
	 * @param imgName
	 * @param isTop
	 * @return
	 */
	public static NotificationPane getNotificationPane(Node content, String imgName, boolean isTop) {
		NotificationPane notificationPane = new NotificationPane();

		if (imgName.length() > 0) {
			String imagePath = ControlFXUtil.class.getResource(imgName).toExternalForm();
			ImageView image = new ImageView(imagePath);
			notificationPane.setGraphic(image);
		}
		notificationPane.setShowFromTop(isTop);

		notificationPane.setContent(content);

		return notificationPane;
	}

}
