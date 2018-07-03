package validation;

import java.time.LocalDate;
import java.util.Arrays;

import org.controlsfx.validation.ValidationResult;
import org.controlsfx.validation.ValidationSupport;
import org.controlsfx.validation.Validator;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Control;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class TestValidation extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		GridPane root = new GridPane();
		root.setVgap(10);
		root.setHgap(10);
		root.setPadding(new Insets(30, 30, 0, 30));

		ValidationSupport validationSupport = new ValidationSupport();

		int row = 0;

		// text field
		TextField textField = new TextField();
		validationSupport.registerValidator(textField, Validator.createEmptyValidator("Text is required"));
		root.add(new Label("TextField"), 0, row);
		root.add(textField, 1, row);

		//combobox
		row++;
		ComboBox<String> combobox = new ComboBox<String>();
		combobox.getItems().addAll("Item A", "Item B", "Item C");
		validationSupport.registerValidator(combobox, Validator.createEmptyValidator("ComboBox Selection required"));

		root.add(new Label("ComboBox"), 0, row);
		root.add(combobox, 1, row);

		//choicebox
		row++;
		ChoiceBox<String> choiceBox = new ChoiceBox<String>();
		choiceBox.getItems().addAll("Item A", "Item B", "Item C");
		validationSupport.registerValidator(choiceBox, Validator.createEmptyValidator("ChoiceBox Selection required"));

		root.add(new Label("ChoiceBox"), 0, row);
		root.add(choiceBox, 1, row);

		//checkbox
		row++;
		CheckBox checkBox = new CheckBox();
		validationSupport.registerValidator(checkBox, (Control c, Boolean newValue) -> ValidationResult.fromErrorIf(c, "Checkbox should be checked", !newValue));
		root.add(new Label("CheckBox"), 0, row);
		root.add(checkBox, 1, row);

		//slider
		row++;
		Slider slider = new Slider(-50d, 50d, -10d);
		slider.setShowTickLabels(true);
		validationSupport.registerValidator(slider, (Control c, Double newValue) -> ValidationResult.fromErrorIf(slider, "Slider value should be > 0", newValue <= 0));

		root.add(new Label("Slider"), 0, row);
		root.add(slider, 1, row);

		// color picker
		row++;
		ColorPicker colorPicker = new ColorPicker(Color.RED);
		validationSupport.registerValidator(colorPicker, Validator.createEqualsValidator("Color should be WHITE or BLACK", Arrays.asList(Color.WHITE, Color.BLACK)));

		root.add(new Label("Color Picker"), 0, row);
		root.add(colorPicker, 1, row);

		// date picker
		row++;
		DatePicker datePicker = new DatePicker();
		//validationSupport.registerValidator(datePicker, false, (Control c, LocalDate newValue) -> ValidationResult.fromWarningIf(datePicker, "The date should be today", !LocalDate.now().equals(newValue)));

		validationSupport.registerValidator(datePicker, true, new Validator<LocalDate>() {

			@Override
			public ValidationResult apply(Control arg0, LocalDate arg1) {
				return ValidationResult.fromWarningIf(arg0, "The date should be today", !LocalDate.now().equals(arg1));
			}
		});
		
		root.add(new Label("Date Picker"), 0, row);
		root.add(datePicker, 1, row);

		Scene scene = new Scene(root, 500, 500);
		primaryStage.setScene(scene);
		primaryStage.sizeToScene();
		primaryStage.show();
	}

}