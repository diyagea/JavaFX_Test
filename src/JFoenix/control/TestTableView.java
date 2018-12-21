package JFoenix.control;

import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.cells.editors.TextFieldEditorBuilder;
import com.jfoenix.controls.cells.editors.base.GenericEditableTreeTableCell;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableColumn.CellEditEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class TestTableView extends Application {

	@Override
	public void start(Stage primaryStage) {
		//Container
		VBox pane = new VBox(50);
		pane.setAlignment(Pos.CENTER);
		pane.setPadding(new Insets(0, 50, 0, 50));

		//UI Control
		JFXTreeTableColumn<User, String> deptColumn = new JFXTreeTableColumn<>("Department");
		deptColumn.setPrefWidth(150);
		deptColumn.setCellValueFactory((TreeTableColumn.CellDataFeatures<User, String> param) -> {
			if (deptColumn.validateValue(param))
				return param.getValue().getValue().department;
			else
				return deptColumn.getComputedValue(param);
		});

		JFXTreeTableColumn<User, String> empColumn = new JFXTreeTableColumn<>("Employee");
		empColumn.setPrefWidth(150);
		empColumn.setCellValueFactory((TreeTableColumn.CellDataFeatures<User, String> param) -> {
			if (empColumn.validateValue(param))
				return param.getValue().getValue().userName;
			else
				return empColumn.getComputedValue(param);
		});

		JFXTreeTableColumn<User, String> ageColumn = new JFXTreeTableColumn<>("Age");
		ageColumn.setPrefWidth(150);
		ageColumn.setCellValueFactory((TreeTableColumn.CellDataFeatures<User, String> param) -> {
			if (ageColumn.validateValue(param))
				return param.getValue().getValue().age;
			else
				return ageColumn.getComputedValue(param);
		});

		ageColumn.setCellFactory((TreeTableColumn<User, String> param) -> new GenericEditableTreeTableCell<User, String>(new TextFieldEditorBuilder()));
		ageColumn.setOnEditCommit((CellEditEvent<User, String> t) -> {
			((User) t.getTreeTableView().getTreeItem(t.getTreeTablePosition().getRow()).getValue()).age.set(t.getNewValue());
		});

		empColumn.setCellFactory((TreeTableColumn<User, String> param) -> new GenericEditableTreeTableCell<User, String>(new TextFieldEditorBuilder()));
		empColumn.setOnEditCommit((CellEditEvent<User, String> t) -> {
			((User) t.getTreeTableView().getTreeItem(t.getTreeTablePosition().getRow()).getValue()).userName.set(t.getNewValue());
		});

		deptColumn.setCellFactory((TreeTableColumn<User, String> param) -> new GenericEditableTreeTableCell<User, String>(new TextFieldEditorBuilder()));
		deptColumn.setOnEditCommit((CellEditEvent<User, String> t) -> {
			((User) t.getTreeTableView().getTreeItem(t.getTreeTablePosition().getRow()).getValue()).department.set(t.getNewValue());
		});

		// data
		ObservableList<User> users = FXCollections.observableArrayList();
		users.add(new User("Computer Department", "23", "CD 1"));
		users.add(new User("Sales Department", "22", "Employee 1"));
		users.add(new User("Sales Department", "22", "Employee 2"));
		users.add(new User("Sales Department", "25", "Employee 4"));
		users.add(new User("Sales Department", "25", "Employee 5"));
		users.add(new User("IT Department", "42", "ID 2"));
		users.add(new User("HR Department", "22", "HR 1"));
		users.add(new User("HR Department", "22", "HR 2"));

		for (int i = 0; i < 100; i++) {
			users.add(new User("HR Department", i % 10 + "", "HR 2" + i));
		}
		for (int i = 0; i < 100; i++) {
			users.add(new User("Computer Department", i % 20 + "", "CD 2" + i));
		}

		for (int i = 0; i < 100; i++) {
			users.add(new User("IT Department", i % 5 + "", "HR 2" + i));
		}

		// build tree
		final TreeItem<User> root = new RecursiveTreeItem<User>(users, RecursiveTreeObject::getChildren);

		JFXTreeTableView<User> treeView = new JFXTreeTableView<User>(root);
		treeView.setShowRoot(false);
		treeView.setEditable(true);
		treeView.getColumns().setAll(deptColumn, ageColumn, empColumn);

		JFXTextField filterField = new JFXTextField();
		filterField.textProperty().addListener((o, oldVal, newVal) -> {
			treeView.setPredicate(user -> user.getValue().age.get().contains(newVal) || user.getValue().department.get().contains(newVal) || user.getValue().userName.get().contains(newVal));
		});

		Label size = new Label();
		size.textProperty().bind(Bindings.createStringBinding(() -> treeView.getCurrentItemsCount() + "", treeView.currentItemsCountProperty()));

		//Add
		pane.getChildren().add(treeView);

		//Scene
		Scene scene = new Scene(pane, 500, 500);
		scene.getStylesheets().add(getClass().getResource("All.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.setTitle("I have a title");
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

	class User extends RecursiveTreeObject<User> {
		StringProperty userName;
		StringProperty age;
		StringProperty department;

		public User(String department, String age, String userName) {
			this.department = new SimpleStringProperty(department);
			this.userName = new SimpleStringProperty(userName);
			this.age = new SimpleStringProperty(age);
		}
	}
}
