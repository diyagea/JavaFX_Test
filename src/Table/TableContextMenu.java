package Table;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Callback;

public class TableContextMenu extends Application {

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

		//init context menu
		final ContextMenu cm = new ContextMenu();
		cm.addEventFilter(MouseEvent.MOUSE_RELEASED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				if (event.getButton() == MouseButton.SECONDARY) {
					System.out.println("consuming right release button in ContexMenu filter");
					event.consume();
				}
			}
		});
		cm.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				Person p = table.getSelectionModel().getSelectedItem();
				String menuID = ((MenuItem) event.getTarget()).getId();
				if("new".equals(menuID)){
					System.out.println("new action");
				}else if("edit".equals(menuID)){
					if(p == null){
						System.out.println("no select");
					}else{
						System.out.println("edit action on "+ p.getFirstName() );
					}
				}else if("save".equals(menuID)){
					if(p == null){
						System.out.println("no select");
					}else{
						System.out.println("save action on " + p.getFirstName());
					}
				}
				table.getSelectionModel().clearSelection();
			}
		});

		MenuItem menuItem1 = new MenuItem("new");
		menuItem1.setId("new");
		menuItem1.setGraphic(new ImageView(new Image(getClass().getResource("New.png").toString())));
		MenuItem menuItem2 = new MenuItem("edit");
		menuItem2.setId("edit");
		menuItem2.setGraphic(new ImageView(new Image(getClass().getResource("Edit.png").toString())));
		MenuItem menuItem3 = new MenuItem("save");
		menuItem3.setGraphic(new ImageView(new Image(getClass().getResource("Save.png").toString())));
		menuItem3.setId("save");

		cm.getItems().addAll(menuItem1, menuItem2, menuItem3);

		//init table
		TableColumn firstNameCol = new TableColumn("First Name");
		firstNameCol.setCellValueFactory(new PropertyValueFactory<>("firstName"));

		TableColumn lastNameCol = new TableColumn("Last Name");
		lastNameCol.setCellValueFactory(new PropertyValueFactory<>("lastName"));

		TableColumn actionCol = new TableColumn("Action");
		actionCol.setCellValueFactory(new PropertyValueFactory<>("DUMMY"));

		Callback<TableColumn<Person, String>, TableCell<Person, String>> cellFactory = new Callback<TableColumn<Person, String>, TableCell<Person, String>>() {
			@Override
			public TableCell call(final TableColumn<Person, String> param) {
				final TableCell<Person, String> cell = new TableCell<Person, String>() {

					final Button btn = new Button("Just Do It");

					@Override
					public void updateItem(String item, boolean empty) {
						super.updateItem(item, empty);
						if (empty) {
							setGraphic(null);
							setText(null);
						} else {
							btn.setOnAction(event -> {
								Person person = getTableView().getItems().get(getIndex());
								System.out.println(person.getFirstName() + "   " + person.getLastName());
							});
							setGraphic(btn);
							setText(null);
						}
					}
				};
				return cell;
			}
		};

		actionCol.setCellFactory(cellFactory);

		table.setItems(data);
		table.getColumns().addAll(firstNameCol, lastNameCol, actionCol);

		//add right mouse action to active contextMenu
		table.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				if (e.getButton() == MouseButton.SECONDARY) {
					cm.show(table, e.getScreenX(), e.getScreenY());
				}
			}
		});

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