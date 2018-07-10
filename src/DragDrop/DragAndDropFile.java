package DragDrop;

import java.io.File;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.stage.Stage;

/**
 * Demonstrates a drag-and-drop feature.
 */
public class DragAndDropFile extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage primaryStage) {
		Group root = new Group();
		Scene scene = new Scene(root, 551, 400);
		scene.setOnDragOver(new EventHandler<DragEvent>() {
			@Override
			public void handle(DragEvent event) {
				Dragboard db = event.getDragboard();
				if (db.hasFiles()) {
					String fileName = null;
					for (File file : db.getFiles()) {
						if (file.exists()) {
							fileName = file.getName();
							String prefix = fileName.substring(fileName.lastIndexOf(".") + 1);
							if("dxf".equals(prefix)){
								event.acceptTransferModes(TransferMode.ANY);
							}else{
								event.acceptTransferModes(TransferMode.NONE);
							}
						}
					}
					//event.acceptTransferModes(TransferMode.COPY);
				} else {
					event.consume();
				}
			}
		});

		// Dropping over surface
		scene.setOnDragDropped(new EventHandler<DragEvent>() {
			@Override
			public void handle(DragEvent event) {
				Dragboard db = event.getDragboard();
				boolean success = false;
				if (db.hasFiles()) {
					success = true;
					String filePath = null;
					String fileName = null;
					for (File file : db.getFiles()) {
						if (file.exists()) {
							filePath = file.getAbsolutePath();
							fileName = file.getName();
							System.out.println(fileName);
							String prefix = filePath.substring(filePath.lastIndexOf(".") + 1);
							System.out.println(prefix);
						}
					}
				}
				event.setDropCompleted(success);
				event.consume();
			}
		});

		primaryStage.setScene(scene);
		primaryStage.show();
	}
}
