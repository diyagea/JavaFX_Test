package Table;

import java.util.HashMap;
import java.util.Map;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.MapValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
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
		//tableView.setPrefSize(400, 400);
//		tableView.setColumnResizePolicy(TableView.UNCONSTRAINED_RESIZE_POLICY);
//		tableView.getSelectionModel().setCellSelectionEnabled(true);
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

		VBox box = new VBox();
		Tab tab = new Tab();
		TabPane tabPane = new TabPane(tab);
		StackPane pane = new StackPane();
		pane.getChildren().addAll(tableView);
		tab.setContent(pane);

		box.getChildren().add(tabPane);
		scene.setRoot(box);
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