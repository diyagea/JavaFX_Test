package Pagination;

import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;

public class PaginationSample extends Application {
	private Pagination pagination;
	private int pageSize = 8;

	public VBox createPage(int pageIndex) {
		VBox box = new VBox(5);
		int page = pageIndex * pageSize;
		for (int i = page; i < page + pageSize; i++) {
			VBox element = new VBox();
			Hyperlink link = new Hyperlink("Item " + (i + 1));
			link.setVisited(true);
			Label text = new Label("Search results\nfor " + link.getText());
			element.getChildren().addAll(link, text);
			box.getChildren().add(element);
		}
		return box;
	}

	public GridPane createGoodsPane(int pageIndex) {
		GridPane pane = new GridPane();
		pane.setHgap(20);
		pane.setVgap(20);
		for (int i = 0, j = 0; i < 8; i++) {
			if (i != 0 && i % 4 == 0) {//奇数放上面
				j++;
			}
			VBox element = new VBox();
			Hyperlink link = new Hyperlink("Item " + (i + 1));
			link.setVisited(true);
			Label text = new Label("Search results\nfor " + link.getText());
			element.getChildren().addAll(link, text);

			GridPane.setVgrow(element, Priority.ALWAYS);
			GridPane.setHgrow(element, Priority.ALWAYS);
			pane.add(element, (i - j * 4), j);
		}

		return pane;
	}

	@Override
	public void start(final Stage stage) throws Exception {
		pagination = new Pagination(10, 0);
		pagination.setMaxPageIndicatorCount(5);
		pagination.setStyle("-fx-border-color:red;");
		pagination.setPrefWidth(800);
		pagination.setPrefHeight(500);
		//pagination.setPageFactory((Integer pageIndex) -> createPage(pageIndex));
		pagination.setPageFactory(new Callback<Integer, Node>() {
			@Override
			public Node call(Integer pageIndex) {
				return createGoodsPane(pageIndex);
			}
		});

		AnchorPane anchor = new AnchorPane();
		AnchorPane.setTopAnchor(pagination, 10.0);
		AnchorPane.setRightAnchor(pagination, 10.0);
		AnchorPane.setBottomAnchor(pagination, 10.0);
		AnchorPane.setLeftAnchor(pagination, 10.0);
		anchor.getChildren().addAll(pagination);
		Scene scene = new Scene(anchor);
		stage.setScene(scene);
		stage.setTitle("PaginationSample");
		stage.show();
	}

	public static void main(String[] args) throws Exception {
		launch(args);
	}
}
