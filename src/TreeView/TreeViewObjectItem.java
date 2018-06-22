package TreeView;

import java.util.HashMap;
import java.util.Map;

import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class TreeViewObjectItem extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {
		TreeItem<MenuTreeItem> rootItem = new TreeItem<>(new MenuTreeItem("1", "配置"));
		rootItem.setExpanded(true);
		for (int i = 1; i < 6; i++) {
			TreeItem<MenuTreeItem> item = new TreeItem<>(new MenuTreeItem("1"+i, "配置"+i));
			rootItem.getChildren().add(item);
			for(int j=1; j<4; j++){
				TreeItem<MenuTreeItem> subItem = new TreeItem<>(new MenuTreeItem("1"+i+j, "配置"+i+j));
				item.getChildren().add(subItem);
			}
		}
		TreeView<MenuTreeItem> tree = new TreeView<>(rootItem);
		
		StackPane root = new StackPane();
		root.getChildren().add(tree);
		primaryStage.setScene(new Scene(root, 300, 250));
		primaryStage.show();
	}
	
	private class MenuTreeItem{
		String id;
		String name;
		
		public MenuTreeItem(String id, String name) {
			this.id = id;
			this.name = name;
		}
		
		@Override
		public String toString() {
			return name;
		}
	}
}
