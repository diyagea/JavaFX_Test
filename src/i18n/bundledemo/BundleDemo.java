package i18n.bundledemo;
// imports are ignored.

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class BundleDemo extends Application {

    private Stage stage;

    @Override
    public void start(Stage primaryStage) {
        stage = primaryStage;
        Button btnEN = new Button();
        btnEN.setText("English");
        btnEN.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent event) {
                loadView(new Locale("en", "EN"));
            }
        });

        Button btnCN = new Button();
        btnCN.setText("汉语");
        btnCN.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent event) {
                loadView(new Locale("cn", "CN"));
            }
        });
        
        Button btnKG = new Button();
        btnKG.setText("Kyrgyz");
        btnKG.setOnAction(new EventHandler<ActionEvent>() {
        	@Override public void handle(ActionEvent event) {
        		loadView(new Locale("kg", "KG"));
        	}
        });

        VBox root = new VBox(20);
        root.getChildren().add(btnEN);
        root.getChildren().add(btnCN);
        root.getChildren().add(btnKG);
        root.getChildren().add(new StackPane());
        primaryStage.setScene(new Scene(root, 300, 250));
        primaryStage.show();
    }

    private void loadView(Locale locale) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setResources(ResourceBundle.getBundle("i18n.bundles.MyBundle", locale));
            fxmlLoader.setLocation(this.getClass().getResource("MyView.fxml"));
            Pane pane  = fxmlLoader.load();
            
            // replace the content
            StackPane content = (StackPane) ((VBox) stage.getScene().getRoot()).getChildren().get(3);
            content.getChildren().clear();
            content.getChildren().add(pane);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}