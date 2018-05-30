package TreeView;

import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class TreeViewSample extends Application {

	private final Node rootIcon = new ImageView(
	//new Image(getClass().getResourceAsStream("folder_16.png"))
	);

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {
		
		primaryStage.setTitle("Tree View Sample");

		TreeItem<String> rootItem = new TreeItem<>("Frist Level", rootIcon);
		rootItem.setExpanded(true);
		for (int i = 1; i < 6; i++) {
			TreeItem<String> item = new TreeItem<>("Second Level" + i);
			rootItem.getChildren().add(item);
			for(int j=1; j<4; j++){
				TreeItem<String> subItem = new TreeItem<>("Thrid Level" + j);
				item.getChildren().add(subItem);
			}
		}
		TreeView<String> tree = new TreeView<>(rootItem);
		StackPane root = new StackPane();
		root.getChildren().add(tree);
		primaryStage.setScene(new Scene(root, 300, 250));
		primaryStage.show();
	}
}
