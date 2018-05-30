/* ....Show License.... */

package chart;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.Side;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.chart.XYChart.Series;
import javafx.stage.Stage;

/**
 * 
 * A simulated stock line chart.
 * 
 */

public class StockLineChartApp2 extends Application {

	private LineChart<Number, Number> chart;
	private Series<Number, Number> series1;
	private Series<Number, Number> series2;
	private Series<Number, Number> series3;
	private Series<Number, Number> series4;
	private Series<Number, Number> series5;
	private NumberAxis xAxis;

	public Parent createContent() {
		long now = System.currentTimeMillis();
		long nextTime = now + 1000 * 60;
		xAxis = new NumberAxis(now, nextTime, 1000 * 10);
		final NumberAxis yAxis = new NumberAxis(0, 200, 10);
		chart = new LineChart<>(xAxis, yAxis);

		// setup chart
		chart.setCreateSymbols(false);
		//数据说明
		chart.setLegendSide(Side.TOP);
		//chart.setLegendVisible(false);
		chart.setTitle("动态数据");
		//xAxis.setLabel("时间");
		xAxis.setForceZeroInRange(false);
		//刻度标
		xAxis.setTickLabelsVisible(false);
		//刻度线
		xAxis.setMinorTickVisible(false);
		yAxis.setLabel("数值");

		// add starting data
		series1 = new Series<>();
		series1.setName("下限");
		series2 = new Series<>();
		series2.setName("上限");
		series3 = new Series<>();
		series3.setName("实际值");
		series4 = new Series<>();
		series4.setName("警戒线");
		series5 = new Series<>();
		series5.setName("稳定度");

		chart.getData().add(series1);
		chart.getData().add(series2);
		chart.getData().add(series3);
		chart.getData().add(series4);
		chart.getData().add(series5);

		return chart;
	}

	private double minute = 0;
	private double second = 0;
	private double secInMin = 0;
	private void nextTime() {
		if (second == 59) {
			minute++;
			second = 0;
		} else {
			second++;
		}

		secInMin = minute + ((1d / 60d) * second);
	}
	
	private void plotTime() {
		long time = System.currentTimeMillis();
		
		double x = Math.random();

		final ObservableList<Data<Number, Number>> list1 = series1.getData();
		list1.add(new Data<Number, Number>(time, 0));

		final ObservableList<Data<Number, Number>> list2 = series2.getData();
		list2.add(new Data<Number, Number>(time, 200));
		
		final ObservableList<Data<Number, Number>> list3 = series3.getData();
		list3.add(new Data<Number, Number>(time, 50 * x + 100));
		
		final ObservableList<Data<Number, Number>> list4 = series4.getData();
		list4.add(new Data<Number, Number>(time, 180));
		
		final ObservableList<Data<Number, Number>> list5 = series5.getData();
		list5.add(new Data<Number, Number>(time, 5 * x + 60));

		if (secInMin > 1.1) {
			list1.remove(0);
			list2.remove(0);
			list3.remove(0);
			list4.remove(0);
			list5.remove(0);
		}

		// every hour after 24 move range 1 hour
		if (secInMin > 1) {
			xAxis.setLowerBound(xAxis.getLowerBound() + 1000);
			xAxis.setUpperBound(xAxis.getUpperBound() + 1000);
		}
	}

	public void play() {
		//		animation.play();
		Thread t = new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i < 10000; i++) {
					nextTime();
					plotTime();
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		});
		t.start();
	}

	public void start(Stage primaryStage) throws Exception {
		primaryStage.setScene(new Scene(createContent()));
		play();
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