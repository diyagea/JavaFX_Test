package ListView;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Orientation;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollBar;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class LazyLoadListView extends Application {
	private BorderPane root;
	private Scene scene;
	private ListView<String> listView;
	private ObservableList<String> listItems;
	private ObservableList<String> bigData;
	private int start = 0;
	private int step = 4;

	@Override
	public void start(Stage primaryStage) {
		root = new BorderPane();
		scene = new Scene(root, 250, 200);

		listView = new ListView<String>();
		listItems = FXCollections.observableArrayList();
		populateInitialList();
		bigData = FXCollections.observableArrayList();
		populateBigData();

		listView.setItems(listItems);

		root.setCenter(listView);

		primaryStage.setTitle("www.superglobals.net");
		primaryStage.setScene(scene);
		primaryStage.show();

		ScrollBar listViewScrollBar = getListViewScrollBar(listView);
		listViewScrollBar.valueProperty().addListener((observable, oldValue, newValue) -> {
			double position = newValue.doubleValue();
			System.out.println(position);
			ScrollBar scrollBar = getListViewScrollBar(listView);
			if (position >= scrollBar.getMax() - 0.2) {
				if (step <= bigData.size()) {
					listItems.addAll(bigData.subList(start, step));
					start = step;
					step += 4;
				}
			}
		});
	}

	public static void main(String[] args) {
		launch(args);
	}

	private void populateInitialList() {
		listItems = FXCollections.observableArrayList();
		listItems.add("John Smith");
		listItems.add("George Town");
		listItems.add("Vladimir Johnson");
		listItems.add("Sam Jackson");
		listItems.add("Adam Pen");
		listItems.add("Abraham Lincoln");
		listItems.add("Christopher Lopez");
		listItems.add("Nicholas Scott");
		listItems.add("Scott Lewis");
		listItems.add("Peter Adams");
		listItems.add("Gerald Morris");
		listItems.add("John Smith");
		listItems.add("George Town");
		listItems.add("Vladimir Johnson");
		listItems.add("Sam Jackson");
		listItems.add("Adam Pen");
		listItems.add("Abraham Lincoln");
		listItems.add("Christopher Lopez");
		listItems.add("Nicholas Scott");
		listItems.add("Scott Lewis");
		listItems.add("Peter Adams");
		listItems.add("Gerald Morris");
		listItems.add("John Smith");
		listItems.add("George Town");
		listItems.add("Vladimir Johnson");
		listItems.add("Sam Jackson");
		listItems.add("Adam Pen");
		listItems.add("Abraham Lincoln");
		listItems.add("Christopher Lopez");
		listItems.add("Nicholas Scott");
		listItems.add("Scott Lewis");
		listItems.add("Peter Adams");
		listItems.add("Gerald Morris");
		listItems.add("John Smith");
		listItems.add("George Town");
		listItems.add("Vladimir Johnson");
		listItems.add("Sam Jackson");
		listItems.add("Adam Pen");
		listItems.add("Abraham Lincoln");
		listItems.add("Christopher Lopez");
		listItems.add("Nicholas Scott");
		listItems.add("Scott Lewis");
		listItems.add("Peter Adams");
		listItems.add("Gerald Morris");
	}

	private void populateBigData() {
		bigData.add("Jesse Morgan");
		bigData.add("Willie Moore");
		bigData.add("John James");
		bigData.add("Billy Johnson");
		bigData.add("Ryan Campbell");
		bigData.add("Joseph Ramirez");
		bigData.add("Larry Alexander");
		bigData.add("Patrick Henderson");
		bigData.add("Jeffrey King");
		bigData.add("Eugene Evans");
		bigData.add("Harry Bell");
		bigData.add("Harold Perry");
		bigData.add("Stephen Patterson");
		bigData.add("Charles Carter");
		bigData.add("Paul Martinez");
		bigData.add("Steve Russell");
		bigData.add("Earl Hill");
		bigData.add("Bruce Reed");
		bigData.add("Jack Rogers");
		bigData.add("Richard Coleman");
		bigData.add("Jesse Morgan");
		bigData.add("Willie Moore");
		bigData.add("John James");
		bigData.add("Billy Johnson");
		bigData.add("Ryan Campbell");
		bigData.add("Joseph Ramirez");
		bigData.add("Larry Alexander");
		bigData.add("Patrick Henderson");
		bigData.add("Jeffrey King");
		bigData.add("Eugene Evans");
		bigData.add("Harry Bell");
		bigData.add("Harold Perry");
		bigData.add("Stephen Patterson");
		bigData.add("Charles Carter");
		bigData.add("Paul Martinez");
		bigData.add("Steve Russell");
		bigData.add("Earl Hill");
		bigData.add("Bruce Reed");
		bigData.add("Jack Rogers");
		bigData.add("Richard Coleman");
		bigData.add("Jesse Morgan");
		bigData.add("Willie Moore");
		bigData.add("John James");
		bigData.add("Billy Johnson");
		bigData.add("Ryan Campbell");
		bigData.add("Joseph Ramirez");
		bigData.add("Larry Alexander");
		bigData.add("Patrick Henderson");
		bigData.add("Jeffrey King");
		bigData.add("Eugene Evans");
		bigData.add("Harry Bell");
		bigData.add("Harold Perry");
		bigData.add("Stephen Patterson");
		bigData.add("Charles Carter");
		bigData.add("Paul Martinez");
		bigData.add("Steve Russell");
		bigData.add("Earl Hill");
		bigData.add("Bruce Reed");
		bigData.add("Jack Rogers");
		bigData.add("Richard Coleman");
		bigData.add("Jesse Morgan");
		bigData.add("Willie Moore");
		bigData.add("John James");
		bigData.add("Billy Johnson");
		bigData.add("Ryan Campbell");
		bigData.add("Joseph Ramirez");
		bigData.add("Larry Alexander");
		bigData.add("Patrick Henderson");
		bigData.add("Jeffrey King");
		bigData.add("Eugene Evans");
		bigData.add("Harry Bell");
		bigData.add("Harold Perry");
		bigData.add("Stephen Patterson");
		bigData.add("Charles Carter");
		bigData.add("Paul Martinez");
		bigData.add("Steve Russell");
		bigData.add("Earl Hill");
		bigData.add("Bruce Reed");
		bigData.add("Jack Rogers");
		bigData.add("Richard Coleman");
		bigData.add("Jesse Morgan");
		bigData.add("Willie Moore");
		bigData.add("John James");
		bigData.add("Billy Johnson");
		bigData.add("Ryan Campbell");
		bigData.add("Joseph Ramirez");
		bigData.add("Larry Alexander");
		bigData.add("Patrick Henderson");
		bigData.add("Jeffrey King");
		bigData.add("Eugene Evans");
		bigData.add("Harry Bell");
		bigData.add("Harold Perry");
		bigData.add("Stephen Patterson");
		bigData.add("Charles Carter");
		bigData.add("Paul Martinez");
		bigData.add("Steve Russell");
		bigData.add("Earl Hill");
		bigData.add("Bruce Reed");
		bigData.add("Jack Rogers");
		bigData.add("Richard Coleman");
		bigData.add("Jesse Morgan");
		bigData.add("Willie Moore");
		bigData.add("John James");
		bigData.add("Billy Johnson");
		bigData.add("Ryan Campbell");
		bigData.add("Joseph Ramirez");
		bigData.add("Larry Alexander");
		bigData.add("Patrick Henderson");
		bigData.add("Jeffrey King");
		bigData.add("Eugene Evans");
		bigData.add("Harry Bell");
		bigData.add("Harold Perry");
		bigData.add("Stephen Patterson");
		bigData.add("Charles Carter");
		bigData.add("Paul Martinez");
		bigData.add("Steve Russell");
		bigData.add("Earl Hill");
		bigData.add("Bruce Reed");
		bigData.add("Jack Rogers");
		bigData.add("Richard Coleman");
		bigData.add("Jesse Morgan");
		bigData.add("Willie Moore");
		bigData.add("John James");
		bigData.add("Billy Johnson");
		bigData.add("Ryan Campbell");
		bigData.add("Joseph Ramirez");
		bigData.add("Larry Alexander");
		bigData.add("Patrick Henderson");
		bigData.add("Jeffrey King");
		bigData.add("Eugene Evans");
		bigData.add("Harry Bell");
		bigData.add("Harold Perry");
		bigData.add("Stephen Patterson");
		bigData.add("Charles Carter");
		bigData.add("Paul Martinez");
		bigData.add("Steve Russell");
		bigData.add("Earl Hill");
		bigData.add("Bruce Reed");
		bigData.add("Jack Rogers");
		bigData.add("Richard Coleman");
		bigData.add("Jesse Morgan");
		bigData.add("Willie Moore");
		bigData.add("John James");
		bigData.add("Billy Johnson");
		bigData.add("Ryan Campbell");
		bigData.add("Joseph Ramirez");
		bigData.add("Larry Alexander");
		bigData.add("Patrick Henderson");
		bigData.add("Jeffrey King");
		bigData.add("Eugene Evans");
		bigData.add("Harry Bell");
		bigData.add("Harold Perry");
		bigData.add("Stephen Patterson");
		bigData.add("Charles Carter");
		bigData.add("Paul Martinez");
		bigData.add("Steve Russell");
		bigData.add("Earl Hill");
		bigData.add("Bruce Reed");
		bigData.add("Jack Rogers");
		bigData.add("Richard Coleman");
		bigData.add("Jesse Morgan");
		bigData.add("Willie Moore");
		bigData.add("John James");
		bigData.add("Billy Johnson");
		bigData.add("Ryan Campbell");
		bigData.add("Joseph Ramirez");
		bigData.add("Larry Alexander");
		bigData.add("Patrick Henderson");
		bigData.add("Jeffrey King");
		bigData.add("Eugene Evans");
		bigData.add("Harry Bell");
		bigData.add("Harold Perry");
		bigData.add("Stephen Patterson");
		bigData.add("Charles Carter");
		bigData.add("Paul Martinez");
		bigData.add("Steve Russell");
		bigData.add("Earl Hill");
		bigData.add("Bruce Reed");
		bigData.add("Jack Rogers");
		bigData.add("Richard Coleman");

		bigData.add("Jesse Morgan");
		bigData.add("Willie Moore");
		bigData.add("John James");
		bigData.add("Billy Johnson");
		bigData.add("Ryan Campbell");
		bigData.add("Joseph Ramirez");
		bigData.add("Larry Alexander");
		bigData.add("Patrick Henderson");
		bigData.add("Jeffrey King");
		bigData.add("Eugene Evans");
		bigData.add("Harry Bell");
		bigData.add("Harold Perry");
		bigData.add("Stephen Patterson");
		bigData.add("Charles Carter");
		bigData.add("Paul Martinez");
		bigData.add("Steve Russell");
		bigData.add("Earl Hill");
		bigData.add("Bruce Reed");
		bigData.add("Jack Rogers");
		bigData.add("Richard Coleman");
	}

	private ScrollBar getListViewScrollBar(ListView<?> listView) {
		ScrollBar scrollbar = null;
		for (Node node : listView.lookupAll(".scroll-bar")) {
			if (node instanceof ScrollBar) {
				ScrollBar bar = (ScrollBar) node;
				if (bar.getOrientation().equals(Orientation.VERTICAL)) {
					scrollbar = bar;
				}
			}
		}
		return scrollbar;
	}
}
