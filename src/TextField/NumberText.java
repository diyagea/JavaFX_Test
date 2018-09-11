package TextField;

import java.text.DecimalFormat;
import java.text.ParsePosition;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class NumberText extends Application {

	@Override
	public void start(Stage stage) throws Exception {
		Pane pane = new VBox();

		//整数
		TextField textField = new TextField();
		pane.getChildren().add(textField);
		// force the field to be numeric only
		textField.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (!newValue.matches("\\d*")) {
					textField.setText(newValue.replaceAll("[^\\d]", ""));
				}
			}
		});

		//小数
		DecimalFormat format = new DecimalFormat("#.0");
		TextFormatter<String> numberFormat = new TextFormatter<>(c -> {
			if (c.getControlNewText().isEmpty()) {
				return c;
			}

			ParsePosition parsePosition = new ParsePosition(0);
			Object object = format.parse(c.getControlNewText(), parsePosition);

			if (object == null || parsePosition.getIndex() < c.getControlNewText().length()) {
				return null;
			} else {
				return c;
			}
		});
		
		TextField field = new TextField();
		pane.getChildren().add(field);
		field.setTextFormatter(numberFormat);

		Scene scene = new Scene(pane);
		stage.setTitle("Table View Sample");
		stage.setWidth(300);
		stage.setHeight(500);
		stage.setScene(scene);
		stage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
