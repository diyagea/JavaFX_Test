package Preloader;

import javafx.application.Preloader;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class PreloaderTest2 extends Preloader {
	ProgressBar loadProgress;
	Label progressText; 
    Stage stage;
    
	private static final int SPLASH_WIDTH = 676;
	private static final int SPLASH_HEIGHT = 227;
    
    private Scene createPreloaderScene() {
    	ImageView splash = new ImageView(new Image("http://fxexperience.com/wp-content/uploads/2010/06/logo.png"));
    	loadProgress = new ProgressBar();
    	loadProgress.setPrefWidth(SPLASH_WIDTH - 20);

    	progressText = new Label();
    	progressText.setText("All hobbits are full.");
    	progressText.setAlignment(Pos.CENTER);
    	
    	Pane splashLayout = new VBox();
		splashLayout.getChildren().addAll(splash, loadProgress, progressText);
		splashLayout.setStyle("-fx-padding: 5; -fx-background-color: cornsilk; -fx-border-width:5; -fx-border-color: linear-gradient(to bottom, chocolate, derive(chocolate, 50%));");
		splashLayout.setEffect(new DropShadow());
		
		Scene splashScene = new Scene(splashLayout);
		stage.initStyle(StageStyle.UNDECORATED);
		final Rectangle2D bounds = Screen.getPrimary().getBounds();
		stage.setScene(splashScene);
		stage.setX(bounds.getMinX() + bounds.getWidth() / 2 - SPLASH_WIDTH / 2);
		stage.setY(bounds.getMinY() + bounds.getHeight() / 2 - SPLASH_HEIGHT / 2);
		
		return splashScene;
    }
    
    public void start(Stage stage) throws Exception {
        this.stage = stage;
        stage.setScene(createPreloaderScene());        
        stage.show();
    }
    
    @Override
    public void handleProgressNotification(ProgressNotification pn) {
    	//loadProgress.setProgress(pn.getProgress());
    }
 
    @Override
    public void handleStateChangeNotification(StateChangeNotification evt) {
    	if(evt.getType() == StateChangeNotification.Type.BEFORE_INIT){
    		progressText.setText("loading data now!!!");
    	}
    	
    	//Application Init finished, Now go to Start
        if (evt.getType() == StateChangeNotification.Type.BEFORE_START) {
            stage.hide();
        }
    }    
    
    public static void main(String[] args) {
		launch(args);
	}
}