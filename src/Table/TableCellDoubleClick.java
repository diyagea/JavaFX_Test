package Table;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Callback;

public class TableCellDoubleClick extends Application {

	private final TableView<Person> table = new TableView<>();
	private final ObservableList<Person> data = FXCollections.observableArrayList(new Person("Jacob", "Smith"), new Person("Isabella", "Johnson"), new Person("Ethan", "Williams"), new Person("Emma", "Jones"), new Person("Michael", "Brown"));

	public static void main(String[] args) {
		launch(args);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void start(Stage stage) {
		stage.setWidth(450);
		stage.setHeight(500);

		//init table
		TableColumn firstNameCol = new TableColumn("First Name");
		firstNameCol.setCellValueFactory(new PropertyValueFactory<>("firstName"));

		TableColumn lastNameCol = new TableColumn("Last Name");
		lastNameCol.setCellValueFactory(new PropertyValueFactory<>("lastName"));

		table.setItems(data);
		table.getColumns().addAll(firstNameCol, lastNameCol);

		//Add Double Click Action for Column
		table.setEditable(false);
		Callback<TableColumn<Person, String>, TableCell<Person, String>> cellFactory = new Callback<TableColumn<Person, String>, TableCell<Person, String>>() {
			public TableCell call(TableColumn p) {
				TableCell cell = new TableCell<Person, String>() {
					@Override
					public void updateItem(String item, boolean empty) {
						super.updateItem(item, empty);
						setText(empty ? null : getString());
						setGraphic(null);
					}

					private String getString() {
						return getItem() == null ? "" : getItem().toString();
					}
				};

				cell.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent event) {
						if (event.getClickCount() > 1) {
							System.out.println("double clicked!");
							TableCell c = (TableCell) event.getSource();
							System.out.println("Cell text: " + c.getText());
						}
					}
				});
				return cell;
			}
		};
		
		firstNameCol.setCellFactory(cellFactory);
		lastNameCol.setCellFactory(cellFactory);

		//init scene
		Scene scene = new Scene(new Group());

		((Group) scene.getRoot()).getChildren().addAll(table);

		stage.setScene(scene);
		stage.show();
	}

	public static class Person {

		private final SimpleStringProperty firstName;
		private final SimpleStringProperty lastName;

		private Person(String fName, String lName) {
			this.firstName = new SimpleStringProperty(fName);
			this.lastName = new SimpleStringProperty(lName);
		}

		public String getFirstName() {
			return firstName.get();
		}

		public void setFirstName(String fName) {
			firstName.set(fName);
		}

		public String getLastName() {
			return lastName.get();
		}

		public void setLastName(String fName) {
			lastName.set(fName);
		}

	}
}