package chart.PieChart;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Side;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class PieChartSample extends Application {

	@Override
	public void start(Stage stage) {
		Scene scene = new Scene(new Group());
		stage.setTitle("Imported Fruits");
		stage.setWidth(500);
		stage.setHeight(500);

		ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(new PieChart.Data("Grapefruit", 13), new PieChart.Data("Oranges", 25), new PieChart.Data("Plums", 10), new PieChart.Data("Pears", 22), new PieChart.Data("Apples", 30));
		final PieChart chart = new PieChart(pieChartData);
		chart.setTitle("Imported Fruits");

		chart.setLabelLineLength(10);
		chart.setLegendSide(Side.LEFT);

		final Tooltip tooltip = new Tooltip();
		for (final PieChart.Data data : chart.getData()) {
			data.setName(data.getName() + " : " + data.getPieValue() + "%");
			data.getNode().addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent e) {
					tooltip.setText(data.getName() + " : " + data.getPieValue() + "%");
					tooltip.setAutoHide(true);
					tooltip.setX(stage.getX() + e.getSceneX());
					tooltip.setY(stage.getY() + e.getSceneY());
					tooltip.show(stage);

				}
			});
		}

		((Group) scene.getRoot()).getChildren().add(chart);
		stage.setScene(scene);
		stage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
