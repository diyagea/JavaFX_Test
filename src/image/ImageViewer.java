package image;

import java.io.File;
import java.io.IOException;

import javafx.beans.property.DoubleProperty;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Cursor;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

public class ImageViewer extends ScrollPane {
	private static double offSetX, offSetY, zoomlvl;
	private static double initx, inity;
	private static int width, height;
	private static int preWidth, preHeight;
	private static ImageView imageView;

	public static ImageViewer createViewer() {
		return new ImageViewer();
	}

	/*
	 * Constructor
	 */
	private ImageViewer() {
		initialize();
	}

	/**
	 * load dxf file
	 * 
	 * @throws IOException
	 */
	public String load(File file) throws IOException {
		String uri = null;
		if (file.exists()) {
			uri = file.toURI().toASCIIString();
			load(uri);
		} else {
			System.out.println("no file:" + file);
		}
		return uri;
	}

	/**
	 * load image url
	 * 
	 * @throws IOException
	 */
	public void load(String uri) throws IOException {
		imageView.setImage(new Image(uri));
	}

	/**
	 * Reset graphics default size & position
	 */
	public void reset() {
		imageView.setFitWidth(preWidth);
		imageView.setFitHeight(preHeight);
	}

	/*
	 * Clear Canvas
	 */
	public void clear() {
		imageView.setImage(new Image("file:resources/img/Address.png"));
	}

	private void initialize() {

		//load Image
		imageView = new ImageView(new Image("file:resources/img/Address.png"));
		//		Image source = new Image(ZoomExample.class.getResourceAsStream("test.jpg"));
		Image sourceImage = imageView.getImage();
		//ImageView Fit Pre Size
		int preSize = 500;
		double ratio = sourceImage.getWidth() / sourceImage.getHeight();
		if (preSize / ratio < preSize) {
			preWidth = preSize;
			preHeight = (int) (preSize / ratio);
		} else if (preSize * ratio < preSize) {
			preWidth = (int) (preSize * ratio);
			preHeight = preSize;
		} else {
			preWidth = preSize;
			preHeight = preSize;
		}
		imageView.setPreserveRatio(false);
		imageView.setFitWidth(preWidth);
		imageView.setFitHeight(preHeight);

		//source image size
		height = (int) sourceImage.getHeight();
		width = (int) sourceImage.getWidth();

		//Zoom Bar
		HBox zoom = new HBox(10);
		zoom.setAlignment(Pos.CENTER);
		Slider zoomLvl = new Slider();
		zoomLvl.setMax(10);
		zoomLvl.setMin(1);
		zoomLvl.setMaxWidth(200);
		zoomLvl.setMinWidth(200);
		Label hint = new Label("Zoom Level");
		Label value = new Label("1.0");
		offSetX = width / 2;
		offSetY = height / 2;
		zoom.getChildren().addAll(hint, zoomLvl, value);

		//Move Scroll Bar
		Slider Hscroll = new Slider();
		Hscroll.setMin(0);
		Hscroll.setMax(width);
		Hscroll.setMaxWidth(imageView.getFitWidth());
		Hscroll.setMinWidth(imageView.getFitWidth());
		Hscroll.setTranslateY(-20);
		Slider Vscroll = new Slider();
		Vscroll.setMin(0);
		Vscroll.setMax(height);
		Vscroll.setMaxHeight(imageView.getFitHeight());
		Vscroll.setMinHeight(imageView.getFitHeight());
		Vscroll.setOrientation(Orientation.VERTICAL);
		Vscroll.setTranslateX(-20);

		BorderPane borderLayout = new BorderPane();
		borderLayout.setCenter(imageView);
		BorderPane.setAlignment(Hscroll, Pos.CENTER);
		BorderPane.setAlignment(Vscroll, Pos.CENTER_LEFT);

		//Move Action Listener
		Hscroll.valueProperty().addListener(e -> {
			offSetX = Hscroll.getValue();
			zoomlvl = zoomLvl.getValue();
			double newValue = (double) ((int) (zoomlvl * 10)) / 10;
			value.setText(newValue + "");
			if (offSetX < (width / newValue) / 2) {
				offSetX = (width / newValue) / 2;
			}
			if (offSetX > width - ((width / newValue) / 2)) {
				offSetX = width - ((width / newValue) / 2);
			}

			imageView.setViewport(new Rectangle2D(offSetX - ((width / newValue) / 2), offSetY - ((height / newValue) / 2), width / newValue, height / newValue));
		});
		Vscroll.valueProperty().addListener(e -> {
			offSetY = height - Vscroll.getValue();
			zoomlvl = zoomLvl.getValue();
			double newValue = (double) ((int) (zoomlvl * 10)) / 10;
			value.setText(newValue + "");
			if (offSetY < (height / newValue) / 2) {
				offSetY = (height / newValue) / 2;
			}
			if (offSetY > height - ((height / newValue) / 2)) {
				offSetY = height - ((height / newValue) / 2);
			}
			imageView.setViewport(new Rectangle2D(offSetX - ((width / newValue) / 2), offSetY - ((height / newValue) / 2), width / newValue, height / newValue));
		});

		//Zoom Action Listener
		zoomLvl.valueProperty().addListener(e -> {
			zoomlvl = zoomLvl.getValue();
			double newValue = (double) ((int) (zoomlvl * 10)) / 10;
			value.setText(newValue + "");
			if (offSetX < (width / newValue) / 2) {
				offSetX = (width / newValue) / 2;
			}
			if (offSetX > width - ((width / newValue) / 2)) {
				offSetX = width - ((width / newValue) / 2);
			}
			if (offSetY < (height / newValue) / 2) {
				offSetY = (height / newValue) / 2;
			}
			if (offSetY > height - ((height / newValue) / 2)) {
				offSetY = height - ((height / newValue) / 2);
			}
			Hscroll.setValue(offSetX);
			Vscroll.setValue(height - offSetY);
			imageView.setViewport(new Rectangle2D(offSetX - ((width / newValue) / 2), offSetY - ((height / newValue) / 2), width / newValue, height / newValue));
		});

		//Hand Cursor
		borderLayout.setCursor(Cursor.OPEN_HAND);
		imageView.setOnMousePressed(e -> {
			initx = e.getSceneX();
			inity = e.getSceneY();
			borderLayout.setCursor(Cursor.CLOSED_HAND);
		});
		imageView.setOnMouseReleased(e -> {
			borderLayout.setCursor(Cursor.OPEN_HAND);
		});
		imageView.setOnMouseDragged(e -> {
			Hscroll.setValue(Hscroll.getValue() + (initx - e.getSceneX()));
			Vscroll.setValue(Vscroll.getValue() - (inity - e.getSceneY()));
			initx = e.getSceneX();
			inity = e.getSceneY();
		});

		//ScrollPane For Zoom Action
		this.addEventFilter(ScrollEvent.ANY, new EventHandler<ScrollEvent>() {
			public void handle(ScrollEvent event) {
				DoubleProperty zoomProperty = zoomLvl.valueProperty();
				if (event.getDeltaY() > 0) {
					zoomProperty.set(zoomProperty.get() * 1.1);
				} else if (event.getDeltaY() < 0) {
					zoomProperty.set(zoomProperty.get() / 1.1);
				}
			}
		});
		this.setContent(borderLayout);
	}
}
