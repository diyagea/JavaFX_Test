package TreeView;

import org.controlsfx.control.CheckTreeView;

import javafx.application.Application;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.CheckBoxTreeItem;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TreeItem;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class HelloCheckTreeView extends Application {

	private final Label checkedItemsLabel = new Label();
	private final Label selectedItemsLabel = new Label();

	private CheckTreeView<String> checkTreeView;

	private CheckBoxTreeItem<String> treeItem_Jonathan = new CheckBoxTreeItem<>("Jonathan");
	private CheckBoxTreeItem<String> treeItem_Eugene = new CheckBoxTreeItem<>("Eugene");
	private CheckBoxTreeItem<String> treeItem_Henry = new CheckBoxTreeItem<>("Henry");
	private CheckBoxTreeItem<String> treeItem_Samir = new CheckBoxTreeItem<>("Samir");

	@Override
	public void start(Stage primaryStage) {
		primaryStage.setTitle("Check Tree View Sample");
		StackPane root = getPanel();
		primaryStage.setScene(new Scene(root, 300, 250));
		primaryStage.show();
	}

	public StackPane getPanel() {
		CheckBoxTreeItem<String> rootTree = new CheckBoxTreeItem<String>("Root");
		rootTree.setExpanded(true);
		rootTree.getChildren().addAll(treeItem_Jonathan, treeItem_Eugene, treeItem_Henry, treeItem_Samir);

		// CheckListView
		checkTreeView = new CheckTreeView<>(rootTree);
		
		//Select Action Listener
		checkTreeView.getSelectionModel().getSelectedItems().addListener(new ListChangeListener<TreeItem<String>>() {
			@Override
			public void onChanged(ListChangeListener.Change<? extends TreeItem<String>> c) {
				System.out.println("==================Select==========================");
				System.out.println(c.getList().get(0).getValue());
			}
		});
		
		//Check Action Listener
		checkTreeView.getCheckModel().getCheckedItems().addListener(new ListChangeListener<TreeItem<String>>() {
			@Override
			public void onChanged(ListChangeListener.Change<? extends TreeItem<String>> change) {
				while (change.next()) {
					System.out.println("=====================Check=======================");
//					System.out.println("Change: " + change);
					System.out.println("Added sublist " + change.getAddedSubList());
					System.out.println("Removed sublist " + change.getRemoved());
					System.out.println("List " + change.getList());
//					System.out.println("Added " + change.wasAdded() + " Permutated " + change.wasPermutated() + " Removed " + change.wasRemoved() + " Replaced " + change.wasReplaced() + " Updated " + change.wasUpdated());
					System.out.println("============================================");
				}
			}
		});

		StackPane stackPane = new StackPane(checkTreeView);
		stackPane.setPadding(new Insets(30));
		return stackPane;
	}

	public static void main(String[] args) {
		launch(args);
	}
}
