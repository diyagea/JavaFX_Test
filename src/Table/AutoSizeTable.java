package Table;

import java.util.HashMap;
import java.util.Map;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.MapValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.StringConverter;

public class AutoSizeTable extends Application {

	public static final String Column1MapKey = "A";
	public static final String Column2MapKey = "B";
	public static final String Column3MapKey = "C";
	public static final String Column4MapKey = "D";

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

		final Label label = new Label("Student IDs");
		label.setFont(new Font("Arial", 20));

		TableColumn<Map, String> Data1Column = new TableColumn<>("Class A");
		TableColumn<Map, String> Data2Column = new TableColumn<>("Class B");
		TableColumn<Map, String> Data3Column = new TableColumn<>("Class C");
		TableColumn<Map, String> Data4Column = new TableColumn<>("Class D");

		Data1Column.setCellValueFactory(new MapValueFactory(Column1MapKey));
		Data1Column.setMinWidth(130);
		Data2Column.setCellValueFactory(new MapValueFactory(Column2MapKey));
		Data2Column.setMinWidth(130);
		Data3Column.setCellValueFactory(new MapValueFactory(Column3MapKey));
		Data3Column.setMinWidth(130);
		Data4Column.setCellValueFactory(new MapValueFactory(Column4MapKey));
		Data4Column.setMinWidth(130);

		TableView tableView = new TableView<>(generateDataInMap());
		tableView.setPrefSize(200, 200);
		tableView.setColumnResizePolicy(TableView.UNCONSTRAINED_RESIZE_POLICY);
		tableView.setEditable(true);
		tableView.getSelectionModel().setCellSelectionEnabled(true);
		tableView.getColumns().setAll(Data1Column, Data2Column, Data3Column, Data4Column);
		Callback<TableColumn<Map, String>, TableCell<Map, String>> cellFactoryForMap = (TableColumn<Map, String> p) -> new TextFieldTableCell(new StringConverter() {
			@Override
			public String toString(Object t) {
				return t.toString();
			}

			@Override
			public Object fromString(String string) {
				return string;
			}
		});
		Data1Column.setCellFactory(cellFactoryForMap);
		Data2Column.setCellFactory(cellFactoryForMap);
		Data3Column.setCellFactory(cellFactoryForMap);
		Data4Column.setCellFactory(cellFactoryForMap);

		final VBox vbox = new VBox();

		vbox.setSpacing(5);
		vbox.setPadding(new Insets(10, 0, 0, 10));
		vbox.getChildren().addAll(label, tableView);
		stage.widthProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> ov, Number t, Number t1) {
				System.out.println("Window Width Change:" + t.toString() + "," + t1.toString());
				tableView.setPrefWidth(t1.doubleValue() - 50);
			}
		});

		stage.heightProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> ov, Number t, Number t1) {
				System.out.println("Window Height Change:" + t.toString() + "," + t1.toString());
				tableView.setPrefHeight(t1.doubleValue() - 150);
			}
		});

		((Group) scene.getRoot()).getChildren().addAll(vbox);

		stage.setScene(scene);

		stage.show();
	}

	private ObservableList<Map> generateDataInMap() {
		int max = 30;
		ObservableList<Map> allData = FXCollections.observableArrayList();
		for (int i = 1; i < max; i++) {
			Map<String, String> dataRow = new HashMap<>();

			String value1 = "A" + i;
			String value2 = "B" + i;
			String value3 = "C" + i;
			String value4 = "D" + i;

			dataRow.put(Column1MapKey, value1);
			dataRow.put(Column2MapKey, value2);
			dataRow.put(Column3MapKey, value3);
			dataRow.put(Column4MapKey, value4);

			allData.add(dataRow);
		}
		return allData;
	}
}