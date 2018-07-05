package spinner;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;

/**
 * 
 * A sample that demonstrates the Spinner control.
 * 
 */

public class SpinnerApp extends Application {

	/**
	 * 
	 * Java main for when running without JavaFX launcher
	 * 
	 */

	public static void main(String[] args) {

		Application.launch(args);

	}

	@Override

	public void start(Stage primaryStage) throws Exception {

		primaryStage.setScene(new Scene(createContent()));

		primaryStage.show();

	}

	public Parent createContent() {

		String[] styles = {

				"spinner", // defaults to arrows on right stacked vertically

				Spinner.STYLE_CLASS_ARROWS_ON_RIGHT_HORIZONTAL,

				Spinner.STYLE_CLASS_ARROWS_ON_LEFT_VERTICAL,

				Spinner.STYLE_CLASS_ARROWS_ON_LEFT_HORIZONTAL,

				Spinner.STYLE_CLASS_SPLIT_ARROWS_VERTICAL,

				Spinner.STYLE_CLASS_SPLIT_ARROWS_HORIZONTAL

		};

		TilePane tilePane = new TilePane();

		tilePane.setPrefColumns(6); //preferred columns

		tilePane.setPrefRows(3); //preferred rows

		tilePane.setHgap(20);

		tilePane.setVgap(30);

		Pane root = new Pane();

		root.setMinSize(Pane.USE_PREF_SIZE, Pane.USE_PREF_SIZE);

		root.setMaxSize(Pane.USE_PREF_SIZE, Pane.USE_PREF_SIZE);

		//Row 1 = Int
		for (int i = 0; i < styles.length; i++) {

			/* Integer spinners */

			SpinnerValueFactory svf = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 99);

			Spinner sp = new Spinner();

			sp.setValueFactory(svf);

			sp.getStyleClass().add(styles[i]);

			sp.setPrefWidth(80);

			tilePane.getChildren().add(sp);

		}

		//Row 2 = double
		for (int i = 0; i < styles.length; i++) {

			/* Double spinners */

			SpinnerValueFactory svf = new SpinnerValueFactory.DoubleSpinnerValueFactory(0.0, 1.0, 0.5, 0.01);

			Spinner sp = new Spinner();

			sp.setValueFactory(svf);

			sp.getStyleClass().add(styles[i]);

			sp.setPrefWidth(90);

			tilePane.getChildren().add(sp);

		}

		//Row 3 = String
		for (int i = 0; i < styles.length; i++) {

			/* String spinners */

			ObservableList<String> items = FXCollections.observableArrayList("Grace", "Matt", "Katie");

			SpinnerValueFactory svf = new SpinnerValueFactory.ListSpinnerValueFactory<>(items);

			Spinner sp = new Spinner();

			sp.setValueFactory(svf);

			sp.setPrefWidth(100);

			sp.getStyleClass().add(styles[i]);

			tilePane.getChildren().add(sp);

		}

		root.getChildren().add(tilePane);

		return root;

	}

}