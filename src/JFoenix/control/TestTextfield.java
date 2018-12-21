package JFoenix.control;

import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.NumberValidator;
import com.jfoenix.validation.RequiredFieldValidator;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class TestTextfield extends Application {

	@Override
	public void start(Stage primaryStage) {
		//Container
		VBox pane = new VBox(50);
		pane.setAlignment(Pos.CENTER);
		pane.setPadding(new Insets(0, 50, 0, 50));

		//UI Control
		JFXTextField field = new JFXTextField();
		field.setLabelFloat(true);
		field.setPromptText("Floating prompt");
		 
		JFXTextField validationField = new JFXTextField();
		validationField.setPromptText("With Validation..");
		RequiredFieldValidator validator = new RequiredFieldValidator();
		validator.setMessage("Input Required");	
		FontAwesomeIconView iconView = new FontAwesomeIconView(FontAwesomeIcon.WARNING);
		validator.setIcon(iconView);
		validationField.getValidators().add(validator);
		validationField.focusedProperty().addListener((o,oldVal,newVal)->{
		    if(!newVal) validationField.validate();
		});
		
		JFXTextField validationField2 = new JFXTextField();
		validationField2.setPromptText("With Number Validation..");
		NumberValidator validator2 = new NumberValidator();
		validator2.setMessage("Number Required");
		FontAwesomeIconView iconView2 = new FontAwesomeIconView(FontAwesomeIcon.WARNING);
		validator2.setIcon(iconView2);
		validationField2.getValidators().add(validator2);
		validationField2.focusedProperty().addListener((o,oldVal,newVal)->{
		    if(!newVal) validationField2.validate();
		});
		
		pane.getChildren().add(field);
		pane.getChildren().add(validationField);
		pane.getChildren().add(validationField2);

		//Scene
		Scene scene = new Scene(pane, 500, 500);
		scene.getStylesheets().add(getClass().getResource("All.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.setTitle("I have a title");
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
