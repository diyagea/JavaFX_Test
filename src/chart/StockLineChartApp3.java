/* ....Show License.... */

package chart;

import javafx.animation.Animation;

import javafx.animation.KeyFrame;

import javafx.animation.Timeline;

import javafx.application.Application;

import javafx.collections.ObservableList;

import javafx.event.ActionEvent;

import javafx.scene.Parent;

import javafx.scene.Scene;

import javafx.scene.chart.LineChart;

import javafx.scene.chart.NumberAxis;

import javafx.scene.chart.NumberAxis.DefaultFormatter;

import javafx.scene.chart.XYChart;

import javafx.scene.chart.XYChart.Data;

import javafx.scene.chart.XYChart.Series;

import javafx.stage.Stage;

import javafx.util.Duration;

/**
 * 
 * A simulated stock line chart.
 * 
 */

public class StockLineChartApp3 extends Application {

	private LineChart<Number, Number> chart;

	private Series<Number, Number> minuteDataSeries;

	private NumberAxis xAxis;
	private double xUpperBound = 2;
	private Timeline animation;
	private double minute = 0;
	private double second = 0;
	private double timeInMinute = 0;
	private double interval = 500;//100ms interval time

	public StockLineChartApp3() {

		//per interval to run add data method
		final KeyFrame frame = new KeyFrame(Duration.millis(interval), (ActionEvent actionEvent) -> {
			nextTime();
			plotTime();
		});

		// create timeline to add new data every 60th of second
		animation = new Timeline();
		animation.getKeyFrames().add(frame);
		animation.setCycleCount(Animation.INDEFINITE);
	}

	public Parent createContent() {
		xAxis = new NumberAxis(0, xUpperBound, 1);//1 minute
		final NumberAxis yAxis = new NumberAxis(-10, 100, 10);//value -10~100
		chart = new LineChart<>(xAxis, yAxis);
		// setup chart
		final String stockLineChartCss = getClass().getResource("StockLineChart.css").toExternalForm();
		chart.getStylesheets().add(stockLineChartCss);
		chart.setCreateSymbols(false);
		chart.setAnimated(false);
		chart.setLegendVisible(false);
		chart.setTitle("ACME Company Stock");
		xAxis.setLabel("Time");
		xAxis.setForceZeroInRange(false);
		yAxis.setLabel("Value");
		yAxis.setTickLabelFormatter(new DefaultFormatter(yAxis, "$", null));

		// add starting data
		minuteDataSeries = new Series<>();
		minuteDataSeries.setName("Data");

		for (double m = 0; m < 180; m++) {
			nextTime();
			plotTime();
		}

		chart.getData().add(minuteDataSeries);

		return chart;

	}

	private void nextTime() {
		double timeRate = 1000 / interval;
		if (second / timeRate == 59) {
			minute++;
			second = 0;
		} else {
			second++;
		}

		timeInMinute = minute + ((1d / 60d) * (second / timeRate));
	}

	private void plotTime() {

		final ObservableList<Data<Number, Number>> minuteList = minuteDataSeries.getData();

		//TODO add temp data to chart
		double value = Math.random() * 100;

		minuteList.add(new Data<Number, Number>(timeInMinute, value));

		// 75% move xAxis and remove old data
		if (timeInMinute > xUpperBound * 0.75) {
			//move xAxis
			xAxis.setLowerBound(xAxis.getLowerBound() + 1 / (60 * 1000 / interval));
			xAxis.setUpperBound(xAxis.getUpperBound() + 1 / (60 * 1000 / interval));

			//remove old data
			minuteList.remove(0);
		}
	}

	public void play() {
		animation.play();
	}

	@Override
	public void stop() {
		animation.pause();
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setScene(new Scene(createContent()));
		primaryStage.show();
		play();
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