package validation;

import javax.imageio.spi.ServiceRegistry;

import org.controlsfx.validation.ValidationResult;
import org.controlsfx.validation.ValidationSupport;
import org.controlsfx.validation.Validator;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class TestValidation2 extends Application {
	ValidationSupport validationSupport;
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		GridPane root = new GridPane();
		root.setVgap(10);
		root.setHgap(10);
		root.setPadding(new Insets(30, 30, 0, 30));

		validationSupport = new ValidationSupport();

		int row = 0;
		Button btn = new Button("检测");
		btn.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				submit();
			}
		});
		root.add(new Label("TextField"), 0, row);
		root.add(btn, 1, row);
		
		
		// text field
		row++;
		TextField textField = new TextField();
		validationSupport.registerValidator(textField, Validator.createEmptyValidator("Text is required"));
		root.add(new Label("TextField"), 0, row);
		root.add(textField, 1, row);

		// date picker
		row++;
		TextField textField2 = new TextField();
		//validationSupport.registerValidator(datePicker, false, (Control c, LocalDate newValue) -> ValidationResult.fromWarningIf(datePicker, "The date should be today", !LocalDate.now().equals(newValue)));

		validationSupport.registerValidator(textField2, false, new Validator<String>() {

			@Override
			public ValidationResult apply(Control c, String input) {
				//return ValidationResult.fromWarningIf(c, "This must be number 123456", !"123456".equals(input));
				return ValidationResult.fromErrorIf(c, "This must be number 123456", !"123456".equals(input));
			}
		});
		
		root.add(new Label("Number 123456"), 0, row);
		root.add(textField2, 1, row);

		Scene scene = new Scene(root, 500, 500);
		primaryStage.setScene(scene);
		primaryStage.sizeToScene();
		primaryStage.show();
	}
	
	private void submit(){
		validationSupport.initInitialDecoration();
		if(validationSupport.isInvalid()){
			System.out.println("ERROR");
		}else{
			System.out.println("OK");
		}
	}

}