package chart.CurveFittedAreaChart;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;
import javafx.stage.Stage;

/**
 * An area chart that demonstrates curve fitting. Styling is done through CSS.
 */
public class CurveFittedAreaChartApp extends Application {

	private CurveFittedAreaChart chart;
	private NumberAxis xAxis;
	private NumberAxis yAxis;

	public Parent createContent() {
		//final String curveFittedChartCss = getClass().getResource("CurveFittedAreaChart.css").toExternalForm();
		final XYChart.Series<Number, Number> series = new XYChart.Series<>();
		series.setName("扭矩");
		series.getData().add(new Data<Number, Number>(0, 950));
		xAxis = new NumberAxis(0, 5000, 1000);
		yAxis = new NumberAxis(0, 1000, 200);
		chart = new CurveFittedAreaChart(xAxis, yAxis);
		//		chart.setLegendVisible(false);//series names
		chart.setHorizontalGridLinesVisible(false);//X-Grid
		chart.setVerticalGridLinesVisible(false);//Y-Grid
		chart.setAlternativeColumnFillVisible(false);
		chart.setAlternativeRowFillVisible(false);
		//chart.getStylesheets().add(curveFittedChartCss);
		chart.getData().add(series);

		AnimationTimer timer = new AnimationTimer() {
			boolean ascending = true;
			int x = 0;
			int y = 900;

			@Override
			public void handle(long l) {
				if (ascending) {
					series.getData().add(new Data<Number, Number>(x, y));

					x = x + (int) (Math.random() * 200)+100;
					y = y - (int) (Math.random() * 20);
				}

				if (x >= 4500 || y <= 0) { // Ends up making max speed ~80MPH
					ascending = false;
					return;
				}

				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		};
		timer.start();

		return chart;
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setScene(new Scene(createContent()));
		primaryStage.show();
	}

	/**
	 * Java main for when running without JavaFX launcher
	 */
	public static void main(String[] args) {
		launch(args);
	}
}
