package controlsFX;

import static org.controlsfx.control.action.ActionUtils.ACTION_SEPARATOR;
import static org.controlsfx.control.action.ActionUtils.ACTION_SPAN;

import java.util.Arrays;
import java.util.Collection;

import org.controlsfx.control.action.Action;
import org.controlsfx.control.action.ActionCheck;
import org.controlsfx.control.action.ActionGroup;
import org.controlsfx.control.action.ActionUtils;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Callback;

public class TableContextMenu extends Application {
	
	private static final ImageView image = null;
	
	private Collection<? extends Action> actions = Arrays.asList(
			new ActionGroup("Group 1", image, 
					new DummyAction("Action 1.1", image), 
					new CheckDummyAction("Action 1.2")),
			new ActionGroup("Group 2", image, 
					new DummyAction("Action 2.1"), 
					ACTION_SEPARATOR, 
					new ActionGroup("Action 2.2", 
							new DummyAction("Action 2.2.1"), 
							new CheckDummyAction("Action 2.2.2")), 
					new DummyAction("Action 2.3")), 
			ACTION_SPAN, 
			ACTION_SEPARATOR, 
			new CheckDummyAction("Action 3", image), 
			new ActionGroup("Group 4", image, 
					new DummyAction("Action 4.1", image), 
					new CheckDummyAction("Action 4.2")));

	protected final TableView<Person> table = new TableView<>();
	final ObservableList<Person> data = FXCollections.observableArrayList(new Person("Jacob", "Smith"),
			new Person("Isabella", "Johnson"), new Person("Ethan", "Williams"), new Person("Emma", "Jones"),
			new Person("Michael", "Brown"));

	
	@ActionCheck
    private class CheckDummyAction extends Action {
        public CheckDummyAction(String name, Node image) {
            super(name);
            setGraphic(image);
            setEventHandler(ae -> {
            	String a = String.format("Action '%s' is executed", getText());
            	System.out.println("TEST:"+a);
            	Person p = table.getSelectionModel().getSelectedItem();
            	if(p!=null){
            		System.out.println(p.getFirstName()+"-"+p.getLastName());
            	}else{
            		System.out.println("no data select!!!");
            	}
            });
        }

        public CheckDummyAction( String name ) {
            super(name);
        }

        @Override public String toString() {
            return getText();
        }
        
    }
    private static class DummyAction extends Action {
        public DummyAction(String name, Node image) {
            super(name);
            setGraphic(image);
            setEventHandler(ae -> String.format("Action '%s' is executed", getText()) );
        }
        
        public DummyAction( String name ) {
            super(name);
        }

        @Override public String toString() {
            return getText();
        }
    }
    
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) {
		stage.setWidth(450);
		stage.setHeight(500);

		TableColumn firstNameCol = new TableColumn("First Name");
		firstNameCol.setCellValueFactory(new PropertyValueFactory<>("firstName"));

		TableColumn lastNameCol = new TableColumn("Last Name");
		lastNameCol.setCellValueFactory(new PropertyValueFactory<>("lastName"));

		TableColumn actionCol = new TableColumn("Action");
		actionCol.setCellValueFactory(new PropertyValueFactory<>("DUMMY"));

		Callback<TableColumn<Person, String>, TableCell<Person, String>> cellFactory = //
				new Callback<TableColumn<Person, String>, TableCell<Person, String>>() {
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
		
		table.setContextMenu(ActionUtils.createContextMenu(actions)); 

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