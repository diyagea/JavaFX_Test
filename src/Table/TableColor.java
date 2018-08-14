package Table;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;

public class TableColor extends Application {

	private final TableView<Person> table = new TableView<>();
	private final ObservableList<Person> data = FXCollections.observableArrayList(new Person("Jacob", "Smith", "GREEN"), new Person("Isabella", "Johnson", "RED"), new Person("Ethan", "Williams", "BLACK"), new Person("Emma", "Jones", "BLUE"), new Person("Michael", "Brown", "ORANGE"));

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void start(Stage stage) {
		stage.setWidth(450);
		stage.setHeight(500);

		
		TableColumn firstNameCol = new TableColumn("First Name");
		firstNameCol.setCellValueFactory(new PropertyValueFactory<>("firstName"));
		firstNameCol.setPrefWidth(100);

		TableColumn lastNameCol = new TableColumn("Last Name");
		lastNameCol.setCellValueFactory(new PropertyValueFactory<>("lastName"));
		lastNameCol.setPrefWidth(100);

		TableColumn actionCol = new TableColumn("Action");
		actionCol.setCellValueFactory(new PropertyValueFactory<>("DUMMY"));
		actionCol.setPrefWidth(100);

		Callback<TableColumn<Person, String>, TableCell<Person, String>> cellFactory = //
				new Callback<TableColumn<Person, String>, TableCell<Person, String>>() {
					@Override
					public TableCell call(final TableColumn<Person, String> param) {
						final TableCell<Person, String> cell = new TableCell<Person, String>() {

							final Label label = new Label();
							@Override
							public void updateItem(String item, boolean empty) {
								super.updateItem(item, empty);
								if (empty) {
									setGraphic(null);
									setText(null);
								} else {
									Person person = getTableView().getItems().get(getIndex());
									label.setStyle("-fx-background-color:" + person.getColor());
									label.setPrefSize(100, 20);
									setGraphic(label);
									//setText(null);
								}
							}
						};
						return cell;
					}
				};

		actionCol.setCellFactory(cellFactory);

		table.setItems(data);
		table.getColumns().addAll(firstNameCol, lastNameCol, actionCol);

		Scene scene = new Scene(new Group());

		((Group) scene.getRoot()).getChildren().addAll(table);

		stage.setScene(scene);
		stage.show();
	}

	public static class Person {

		private final SimpleStringProperty firstName;
		private final SimpleStringProperty lastName;
		private String color;

		private Person(String fName, String lName, String color) {
			this.firstName = new SimpleStringProperty(fName);
			this.lastName = new SimpleStringProperty(lName);
			this.color = color;
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

		public String getColor() {
			return color;
		}

		public void setColor(String color) {
			this.color = color;
		}

	}
}