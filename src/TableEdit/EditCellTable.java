package TableEdit;

import javafx.application.Application;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Callback;

/*
 * 自定义EditCell，失去焦点是更新textfield
 */
public class EditCellTable extends Application {

	private final TableView<Person> table = new TableView<>();
	private final ObservableList<Person> data = FXCollections.observableArrayList(new Person("Jacob", "Smith", "jacob.smith@example.com"), new Person("Isabella", "Johnson", "isabella.johnson@example.com"), new Person("Ethan", "Williams", "ethan.williams@example.com"), new Person("Emma", "Jones", "emma.jones@example.com"), new Person("Michael", "Brown", "michael.brown@example.com"));

	public static void main(String[] args) {
		launch(args);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void start(Stage stage) {
		Scene scene = new Scene(new Group());
		stage.setTitle("Table View Sample");
		stage.setWidth(450);
		stage.setHeight(550);

		final Label label = new Label("Address Book");
		label.setFont(new Font("Arial", 20));

		table.setEditable(true);

		//自定义Edit Cell
		Callback<TableColumn<Person, String>, TableCell<Person, String>> cellFactory = (TableColumn<Person, String> p) -> new EditingCell();

		TableColumn<Person, String> firstNameCol = new TableColumn<>("First Name");
		TableColumn<Person, String> lastNameCol = new TableColumn<>("Last Name");
		TableColumn<Person, String> emailCol = new TableColumn<>("Email");

		firstNameCol.setMinWidth(100);
		firstNameCol.setCellValueFactory(new PropertyValueFactory<>("firstName"));

		lastNameCol.setMinWidth(100);
		lastNameCol.setCellValueFactory(new PropertyValueFactory<>("lastName"));

		emailCol.setMinWidth(200);
		emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
		emailCol.setCellFactory(cellFactory);
		emailCol.setOnEditCommit((CellEditEvent<Person, String> t) -> {
			((Person) t.getTableView().getItems().get(t.getTablePosition().getRow())).setEmail(t.getNewValue());
			//t.getTableView().getSelectionModel().getSelectedItem().setEmail(t.getNewValue());
		});

		table.setItems(data);
		table.getColumns().addAll(firstNameCol, lastNameCol, emailCol);

		final VBox vbox = new VBox();
		vbox.setSpacing(5);
		vbox.setPadding(new Insets(10, 0, 0, 10));
		vbox.getChildren().addAll(label, table);

		((Group) scene.getRoot()).getChildren().addAll(vbox);

		stage.setScene(scene);
		stage.show();
	}

}