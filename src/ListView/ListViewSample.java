package ListView;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class ListViewSample extends Application {

	public static final ObservableList data = FXCollections.observableArrayList();

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {
		primaryStage.setTitle("List View Sample");

		final ListView<Button> listView = new ListView<>();
		listView.setPrefSize(200, 250);
		listView.setEditable(true);

		for (int i = 0; i < 18; i++) {
			data.add(new Button("Btn" + i));
		}

		listView.setItems(data);

		StackPane root = new StackPane();
		root.getChildren().add(listView);
		primaryStage.setScene(new Scene(root, 200, 250));
		primaryStage.show();
	}
}