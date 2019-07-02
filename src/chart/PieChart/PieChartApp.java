package chart.PieChart;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * A circular chart divided into segments. The value of each segment represents a proportion of the total.
 *
 * @sampleName Pie Chart
 * @preview preview.png
 * @docUrl https://docs.oracle.com/javafx/2/charts/jfxpub-charts.htm Using JavaFX Charts Tutorial
 * @playground chart.data
 * @playground chart.clockwise
 * @playground chart.labelLineLength
 * @playground chart.labelsVisible
 * @playground chart.startAngle (max=360)
 * @playground chart.animated
 * @playground chart.legendSide
 * @playground chart.legendVisible
 * @playground chart.title
 * @playground chart.titleSide
 * @see javafx.scene.chart.PieChart
 * @embedded
 *
 * @related /Charts/Pie/Drilldown Pie Chart
 */
public class PieChartApp extends Application {
	final Label caption = new Label("");

	private PieChart chart;

	public static ObservableList<PieChart.Data> generateData() {
		return FXCollections.observableArrayList(new PieChart.Data("Sun", 20), new PieChart.Data("IBM", 12), new PieChart.Data("HP", 25), new PieChart.Data("Dell", 22), new PieChart.Data("Apple", 30));
	}

	public Parent createContent() {
		chart = new PieChart(generateData());
		chart.setClockwise(false);
		caption.setTextFill(Color.DARKORANGE);
		caption.setStyle("-fx-font: 24 arial;");
		for (final PieChart.Data data : chart.getData()) {
			data.getNode().addEventHandler(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent e) {
					caption.setTranslateX(e.getSceneX());
					caption.setTranslateY(e.getSceneY());
					caption.setText(String.valueOf(data.getPieValue()) + "%");
				}
			});
		}
		return chart;
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setScene(new Scene(createContent()));
		primaryStage.show();
	}

	/**
	 * Java main for when running without JavaFX launcher
	 * 
	 * @param args command line arguments
	 */
	public static void main(String[] args) {
		launch(args);
	}
}
