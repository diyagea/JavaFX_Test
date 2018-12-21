package FontAwesome;

import com.jfoenix.controls.JFXButton;

import de.jensd.fx.glyphs.GlyphsDude;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class TestIcon extends Application {

	@Override
	public void start(Stage primaryStage) {
		//Container
		FlowPane pane = new FlowPane(20, 20);
		ScrollPane scrollPane = new ScrollPane(pane);
		pane.setAlignment(Pos.CENTER);

		//UI Control
		JFXButton button = new JFXButton();
		//FontAwesomeIconView iconView = new FontAwesomeIconView(FontAwesomeIcon.REFRESH);
		//button.setGraphic(iconView);
		Text icon = GlyphsDude.createIcon(FontAwesomeIcon.BARS, "20px");
		icon.setFill(Color.WHITE);
		button.setGraphic(icon); 
		button.setStyle("-jfx-button-type: RAISED; -fx-background-color: transparent;");
		pane.getChildren().add(button);

		//Scene
		Scene scene = new Scene(scrollPane);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Awesome");
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
