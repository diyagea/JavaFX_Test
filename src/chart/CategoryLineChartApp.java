/* ....Show License.... */

package chart;

 

 

import java.text.SimpleDateFormat;
import java.util.Date;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

 

/**

 * A line chart demonstrating a CategoryAxis implementation.

 */

public class CategoryLineChartApp extends Application {

 

    private static final String[] CATEGORIES = { "Alpha", "Beta", "RC1", "RC2",

                                                 "1.0", "1.1" };

    private LineChart<String, Number> chart;

	private CategoryAxis xAxis;

    private NumberAxis yAxis;
    

    XYChart.Series<String, Number> series1 ;
    XYChart.Series<String, Number> series2 ;

 

    public Parent createContent() {

        xAxis = new CategoryAxis();

        yAxis = new NumberAxis();

        chart = new LineChart<>(xAxis, yAxis);

        // setup chart
        chart.setTitle("LineChart with Category Axis");
        //chart.setAnimated(false);
        chart.setCreateSymbols(false);
		chart.setAnimated(false);
		chart.setTitle("ACME Company Stock");
        
		xAxis.setLabel("X Axis");
		xAxis.setScaleShape(false);
        yAxis.setLabel("Y Axis");

        // add starting data
        series1 = new XYChart.Series<>();
        series2 = new XYChart.Series<>();
        
        series1.setName("功率");
        series2.setName("负载");
        chart.getData().add(series1);
        chart.getData().add(series2);

        return chart;

    }
    
    private void addData(SimpleDateFormat dateFormat){
    	Date date = new Date();
    	String dataStr = dateFormat.format(date);
    	series1.getData().add(new XYChart.Data(dataStr, Math.random() * 500));
    	series2.getData().add(new XYChart.Data(dataStr, Math.random() * 500));
    }

 

    @Override public void start(Stage primaryStage) throws Exception {

        primaryStage.setScene(new Scene(createContent()));

        
        
        Thread addThread = new Thread(new Runnable() {
        	SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
			@Override
			public void run() {
				for(int i = 1; i<=10000; i++){
		        	addData(dateFormat);
		        	try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
					}
		        }
			}
		});
        
        addThread.start();
        
        primaryStage.show();

        
    }

 

    /**

     * Java main for when running without JavaFX launcher

     */

    public static void main(String[] args) {

        launch(args);

    }

}