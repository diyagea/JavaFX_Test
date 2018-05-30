package Table;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.CustomMenuItem;
import javafx.scene.control.Label;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class TableViewSample extends Application {

	@SuppressWarnings("rawtypes")
	private final TableView table = new TableView();

	public static void main(String[] args) {
		launch(args);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void start(Stage stage) {
		Scene scene = new Scene(new Group());
		stage.setTitle("Table View Sample");
		stage.setWidth(300);
		stage.setHeight(500);

		// create table columns
		TableColumn firstNameCol = new TableColumn("First Name");
		TableColumn lastNameCol = new TableColumn("Last Name");
		TableColumn emailCol = new TableColumn("Email");

		table.getColumns().addAll(firstNameCol, lastNameCol, emailCol);

		// add context menu
		CustomMenuItem cmi;
		ContextMenu cm = new ContextMenu();

		// select all item
		Label selectAll = new Label("Select all");
		selectAll.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				for (Object obj : table.getColumns()) {
					((TableColumn) obj).setVisible(true);
				}
			}

		});

		cmi = new CustomMenuItem(selectAll);
		cmi.setHideOnClick(false);
		cm.getItems().add(cmi);

		// deselect all item
		Label deselectAll = new Label("Deselect all");
		deselectAll.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				for (Object obj : table.getColumns()) {
					((TableColumn) obj).setVisible(false);
				}
			}

		});

		cmi = new CustomMenuItem(deselectAll);
		cmi.setHideOnClick(false);
		cm.getItems().add(cmi);

		// separator
		cm.getItems().add(new SeparatorMenuItem());

		// menu item for all columns
		for (Object obj : table.getColumns()) {

			TableColumn tableColumn = (TableColumn) obj;

			CheckBox cb = new CheckBox(tableColumn.getText());
			cb.selectedProperty().bindBidirectional(tableColumn.visibleProperty());

			cmi = new CustomMenuItem(cb);
			cmi.setHideOnClick(false);

			cm.getItems().add(cmi);
		}

		// set context menu
		table.setContextMenu(cm);

		final VBox vbox = new VBox();
		vbox.setSpacing(5);
		vbox.setPadding(new Insets(10, 0, 0, 10));
		vbox.getChildren().addAll(table);

		((Group) scene.getRoot()).getChildren().addAll(vbox);

		stage.setScene(scene);
		stage.show();
	}
}