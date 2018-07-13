package ComboBox;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Callback;

public class ComboBoxSample extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) {
		stage.setTitle("ComboBoxSample");
		Scene scene = new Scene(new Group(), 500, 270);

		//Editable ComboBox
		final ComboBox<String> emailComboBox = new ComboBox<>();
		emailComboBox.getItems().addAll("jacob.smith@example.com", "isabella.johnson@example.com", "ethan.williams@example.com", "emma.jones@example.com", "michael.brown@example.com");
		emailComboBox.setPromptText("Email address");
		emailComboBox.setEditable(true);
		emailComboBox.setOnAction((ActionEvent e) -> {
			//copy value to address variable
			String select = emailComboBox.getSelectionModel().getSelectedItem().toString();
			System.out.println(select);
		});

		//Color ComboBox
		final ComboBox<String> priorityComboBox = new ComboBox<>();
		priorityComboBox.getItems().addAll("Highest", "High", "Normal", "Low", "Lowest");
		//default select
		priorityComboBox.setValue("Normal");

		//Customized ListCell
		priorityComboBox.setCellFactory(new Callback<ListView<String>, ListCell<String>>() {
			@Override
			public ListCell<String> call(ListView<String> param) {
				final ListCell<String> cell = new ListCell<String>() {
					{
						super.setPrefWidth(100);
					}

					@Override
					public void updateItem(String item, boolean empty) {
						super.updateItem(item, empty);
						if (item != null) {
							setText(item);
							if (item.contains("High")) {
								setTextFill(Color.RED);
							} else if (item.contains("Low")) {
								setTextFill(Color.GREEN);
							} else {
								setTextFill(Color.BLACK);
							}
						} else {
							setText(null);
						}
					}
				};
				return cell;
			}
		});

		//Editable ComboBox
		final ComboBox<ComboBoxNode> objBox = new ComboBox<>();
		ComboBoxNode node1 = new ComboBoxNode("node1");
		node1.putData("test", "test");
		ComboBoxNode node2 = new ComboBoxNode("node2");
		ComboBoxNode node3 = new ComboBoxNode("node3");
		objBox.getItems().addAll(node1, node2, node3);
		objBox.setValue(node3);
		objBox.setOnAction((ActionEvent e) -> {
			//copy value to address variable
			ComboBoxNode select = objBox.getSelectionModel().getSelectedItem();
			System.out.println(select.getDataMap());
		});

		GridPane grid = new GridPane();
		grid.setVgap(10);
		grid.setHgap(10);
		grid.setPadding(new Insets(5, 5, 5, 5));

		grid.add(new Label("Editable: "), 0, 0);
		grid.add(emailComboBox, 1, 0);

		grid.add(new Label("Customized Color: "), 0, 1);
		grid.add(priorityComboBox, 1, 1);
		
		grid.add(new Label("Object Node: "), 0, 2);
		grid.add(objBox, 1, 2);

		Group root = (Group) scene.getRoot();
		root.getChildren().add(grid);
		stage.setScene(scene);
		stage.show();

	}
}
