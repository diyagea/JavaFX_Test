package login;

import org.controlsfx.dialog.LoginDialog;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class LoginDialogTest extends Application{
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage state) throws Exception {
		Button Hyperlink12c = new Button("Login");
        Hyperlink12c.setOnAction((ActionEvent e) -> {
                LoginDialog dlg = new LoginDialog(null, null);
                dlg.showAndWait().ifPresent(result -> {
                	System.out.println(result.getKey());
                	System.out.println(result.getValue());
                });
        });
        
        StackPane pane = new StackPane();
        pane.getChildren().add(Hyperlink12c);
        state.setScene(new Scene(pane, 300, 300));
        state.show();
	}
}
