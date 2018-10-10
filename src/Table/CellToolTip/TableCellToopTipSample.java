package Table.CellToolTip;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;

public class TableCellToopTipSample extends Application {

	private final TableView<Person> table = new TableView<>();
	private final ObservableList<Person> data = FXCollections.observableArrayList(new Person("Jacob", "Smith11111111111111111111111111111111111111111111111"), new Person("Isabella", "Johnson"), new Person("Ethan", "Williams"), new Person("Emma", "Jones"), new Person("Michael", "Brown"));

	public static void main(String[] args) {
		launch(args);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void start(Stage stage) {
		stage.setWidth(450);
		stage.setHeight(500);

		TableColumn firstNameCol = new TableColumn("First Name");
		firstNameCol.setCellValueFactory(new PropertyValueFactory<>("firstName"));

		TableColumn lastNameCol = new TableColumn("Last Name");
		lastNameCol.setCellValueFactory(new PropertyValueFactory<>("lastName"));

		addTooltipToColumnCells(lastNameCol);

		table.setItems(data);
		table.getColumns().addAll(firstNameCol, lastNameCol);

		Scene scene = new Scene(new Group());

		((Group) scene.getRoot()).getChildren().addAll(table);

		stage.setScene(scene);
		stage.show();
	}

	private <T> void addTooltipToColumnCells(TableColumn<T, T> column) {

		Callback<TableColumn<T, T>, TableCell<T, T>> existingCellFactory = column.getCellFactory();

		column.setCellFactory(c -> {
			TableCell<T, T> cell = existingCellFactory.call(c);

			Tooltip tooltip = new Tooltip();
			// can use arbitrary binding here to make text depend on cell
			// in any way you need:
			tooltip.textProperty().bind(cell.itemProperty().asString());

			cell.setTooltip(tooltip);
			return cell;
		});
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