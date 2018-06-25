package icon;

import org.controlsfx.glyphfont.FontAwesome;
import org.controlsfx.glyphfont.Glyph;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * 
 * A sample that demonstrates the ProgressBar control.
 * 
 */

public class IconTest extends Application {

	public static Glyph getIcon(FontAwesome.Glyph icon) {
		return new Glyph("FontAwesome", icon);
	}

	public Parent createContent() {

		ToolBar toolbar = new ToolBar(

				// There are many ways how you can define a Glyph:
				new Button("test", getIcon(FontAwesome.Glyph.CARET_DOWN).size(50).color(Color.RED).useGradientEffect()), //test
				new Button("", new Glyph("FontAwesome", "TRASH_ALT")), // Use the Glyph-class with a icon name
				new Button("", new Glyph("FontAwesome", FontAwesome.Glyph.STAR)), // Use the Glyph-class with a known enum value
				new Button("", Glyph.create("FontAwesome|BUG")) // Use the static Glyph-class create protocol
		);

		Group group = new Group();
		group.getChildren().addAll(toolbar);

		return group;

	}

	@Override

	public void start(Stage primaryStage) throws Exception {

		primaryStage.setScene(new Scene(createContent(), 300, 300));

		primaryStage.show();

	}

	/**
	 * 
	 * Java main for when running without JavaFX launcher
	 * 
	 */

	public static void main(String[] args) {

		launch(args);

	}

}