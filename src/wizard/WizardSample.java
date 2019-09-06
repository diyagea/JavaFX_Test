package wizard;

import java.util.Optional;

import org.controlsfx.dialog.Wizard;
import org.controlsfx.dialog.Wizard.LinearFlow;
import org.controlsfx.dialog.WizardPane;
import org.controlsfx.validation.ValidationSupport;
import org.controlsfx.validation.Validator;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class WizardSample extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		//				showLinearWizard();
		showBranchingWizard();
		//showValidatedLinearWizard();
		//showTestWizard();
	}

	public static void main(String[] args) {
		launch(args);
	}

	private void showTestWizard() {
		Wizard wizard = new Wizard();
		wizard.setTitle("Test Linear Wizard");

		// Page 1
		WizardPane page1 = new WizardPane() {
			{
				int row = 0;

				GridPane page1Grid = new GridPane();
				page1Grid.setVgap(10);
				page1Grid.setHgap(10);

				page1Grid.add(new Label("Username:"), 0, row);
				TextField txUsername = createTextField("username");
				page1Grid.add(txUsername, 1, row++);

				page1Grid.add(new Label("Full Name:"), 0, row);
				TextField txFullName = createTextField("fullname");
				page1Grid.add(txFullName, 1, row);

				setContent(page1Grid);
			}
		};

		// Page 2
		WizardPane page2 = new WizardPane() {
			{

				int row = 0;

				GridPane page1Grid = new GridPane();
				page1Grid.setVgap(10);
				page1Grid.setHgap(10);

				page1Grid.add(new Label("Username:"), 0, row);
				TextField txUsername = createTextField("username2");
				page1Grid.add(txUsername, 1, row++);

				setContent(page1Grid);
			}
		};

		// Page 2
		WizardPane page3 = new WizardPane() {
			{
				TextField txUsername = createTextField("username3");
				super.onEnteringPage(wizard);
				int row = 0;

				GridPane page1Grid = new GridPane();
				page1Grid.setVgap(10);
				page1Grid.setHgap(10);

				page1Grid.add(new Label("Username:"), 0, row);
				page1Grid.add(txUsername, 1, row++);

				setContent(page1Grid);
			}

		};

		// Page 2
		WizardPane page4 = new WizardPane() {
			@Override
			public void onEnteringPage(Wizard wizard) {
				super.onEnteringPage(wizard);

				setContentText("完成！");
			}
		};
		// create wizard
		wizard.setFlow(new LinearFlow(page1, page2, page3, page4));

		// show wizard and wait for response
		wizard.showAndWait().ifPresent(result -> {
			if (result == ButtonType.FINISH) {
				System.out.println("Wizard finished, settings: " + wizard.getSettings());
			}
		});
	}

	private void showLinearWizard() {
		// define pages to show
		Wizard wizard = new Wizard();
		wizard.setTitle("Linear Wizard");

		// --- page 1
		int row = 0;

		GridPane page1Grid = new GridPane();
		page1Grid.setVgap(10);
		page1Grid.setHgap(10);

		page1Grid.add(new Label("First Name:"), 0, row);
		TextField txFirstName = createTextField("firstName");
		//        wizard.getValidationSupport().registerValidator(txFirstName, Validator.createEmptyValidator("First Name is mandatory")); 
		page1Grid.add(txFirstName, 1, row++);

		page1Grid.add(new Label("Last Name:"), 0, row);
		TextField txLastName = createTextField("lastName");
		//        wizard.getValidationSupport().registerValidator(txLastName, Validator.createEmptyValidator("Last Name is mandatory"));
		page1Grid.add(txLastName, 1, row);

		WizardPane page1 = new WizardPane();
		page1.setHeaderText("Please Enter Your Details");
		page1.setContent(page1Grid);

		// --- page 2
		final WizardPane page2 = new WizardPane() {
			{
				TextField test = createTextField("test");
				setContent(test);
			}

			@Override
			public void onEnteringPage(Wizard wizard) {
				String firstName = (String) wizard.getSettings().get("firstName");
				String lastName = (String) wizard.getSettings().get("lastName");
				setContentText("Welcome, " + firstName + " " + lastName + "! Let's add some newlines!\n\n\n\n\n\n\nHello World!");
			}
		};
		page2.setHeaderText("Thanks For Your Details!");

		// --- page 3
		WizardPane page3 = new WizardPane();
		page3.setHeaderText("Goodbye!");
		page3.setContentText("Page 3, with extra 'help' button!");

		ButtonType helpDialogButton = new ButtonType("Help", ButtonData.HELP_2);
		page3.getButtonTypes().add(helpDialogButton);
		Button helpButton = (Button) page3.lookupButton(helpDialogButton);
		helpButton.addEventFilter(ActionEvent.ACTION, actionEvent -> {
			actionEvent.consume(); // stop hello.dialog from closing
			System.out.println("Help clicked!");
		});

		// create wizard
		wizard.setFlow(new LinearFlow(page1, page2, page3));

		System.out.println("page1: " + page1);
		System.out.println("page2: " + page2);
		System.out.println("page3: " + page3);

		// show wizard and wait for response
		wizard.showAndWait().ifPresent(result -> {
			if (result == ButtonType.FINISH) {
				System.out.println("Wizard finished, settings: " + wizard.getSettings());
			}
		});
	}

	private void showBranchingWizard() {
		// define pages to show.
		// Because page1 references page2, we need to declare page2 first.
		final WizardPane page2 = new WizardPane();
		page2.setContentText("Page 2");

		final CheckBox checkBox = new CheckBox("Skip the second page");
		checkBox.setId("skip-page-2");
		VBox vbox = new VBox(10, new Label("Page 1"), checkBox);
		final WizardPane page1 = new WizardPane() {
			// when we exit page 1, we will check the state of the 'skip page 2'
			// checkbox, and if it is true, we will remove page 2 from the pages list
			@Override
			public void onExitingPage(Wizard wizard) {
				//                List<WizardPage> pages = wizard.getPages();
				//                if (checkBox.isSelected()) {
				//                    pages.remove(page2);
				//                } else {
				//                    if (! pages.contains(page2)) {
				//                        pages.add(1, page2);
				//                    }
				//                }
			}
		};
		page1.setContent(vbox);

		final WizardPane page3 = new WizardPane();
		page3.setContentText("Page 3");

		// create wizard
		Wizard wizard = new Wizard();
		wizard.setTitle("Branching Wizard");
		Wizard.Flow branchingFlow = new Wizard.Flow() {

			@Override
			public Optional<WizardPane> advance(WizardPane currentPage) {
				return Optional.of(getNext(currentPage));
			}

			@Override
			public boolean canAdvance(WizardPane currentPage) {
				return currentPage != page3;
			}

			private WizardPane getNext(WizardPane currentPage) {
				if (currentPage == null) {
					return page1;
				} else if (currentPage == page1) {
					return checkBox.isSelected() ? page3 : page2;
				} else {
					return page3;
				}
			}

		};

		//wizard.setFlow( new LinearWizardFlow( page1, page2, page3));
		wizard.setFlow(branchingFlow);

		// show wizard
		wizard.showAndWait().ifPresent(result -> {
			if (result == ButtonType.FINISH) {
				System.out.println("Wizard finished, settings: " + wizard.getSettings());
			}
		});
	}

	private void showValidatedLinearWizard() {
		Wizard wizard = new Wizard();
		wizard.setTitle("Validated Linear Wizard");

		// Page 1
		WizardPane page1 = new WizardPane() {
			ValidationSupport vs = new ValidationSupport();
			{
				vs.initInitialDecoration();

				int row = 0;

				GridPane page1Grid = new GridPane();
				page1Grid.setVgap(10);
				page1Grid.setHgap(10);

				page1Grid.add(new Label("Username:"), 0, row);
				TextField txUsername = createTextField("username");
				vs.registerValidator(txUsername, Validator.createEmptyValidator("EMPTY!"));
				page1Grid.add(txUsername, 1, row++);

				page1Grid.add(new Label("Full Name:"), 0, row);
				TextField txFullName = createTextField("fullName");
				page1Grid.add(txFullName, 1, row);

				setContent(page1Grid);
			}

			@Override
			public void onEnteringPage(Wizard wizard) {
				wizard.invalidProperty().unbind();
				wizard.invalidProperty().bind(vs.invalidProperty());
			}
		};

		// Page 2

		WizardPane page2 = new WizardPane() {
			ValidationSupport vs = new ValidationSupport();
			{
				vs.initInitialDecoration();

				int row = 0;

				GridPane page2Grid = new GridPane();
				page2Grid.setVgap(10);
				page2Grid.setHgap(10);

				page2Grid.add(new Label("ControlsFX is:"), 0, row);
				ComboBox<String> cbControlsFX = createComboBox("controlsfx");
				cbControlsFX.setItems(FXCollections.observableArrayList("Cool", "Great"));
				vs.registerValidator(cbControlsFX, Validator.createEmptyValidator("EMPTY!"));
				page2Grid.add(cbControlsFX, 1, row++);

				page2Grid.add(new Label("Where have you heard of it?:"), 0, row);
				TextField txWhere = createTextField("where");
				vs.registerValidator(txWhere, Validator.createEmptyValidator("EMPTY!"));
				page2Grid.add(txWhere, 1, row++);

				page2Grid.add(new Label("Free text:"), 0, row);
				TextField txFreeText = createTextField("freetext");
				page2Grid.add(txFreeText, 1, row);

				setContent(page2Grid);
			}

			@Override
			public void onEnteringPage(Wizard wizard) {
				wizard.invalidProperty().unbind();
				wizard.invalidProperty().bind(vs.invalidProperty());
			}
		};

		// create wizard
		wizard.setFlow(new LinearFlow(page1, page2));

		// show wizard and wait for response
		wizard.showAndWait().ifPresent(result -> {
			if (result == ButtonType.FINISH) {
				System.out.println("Wizard finished, settings: " + wizard.getSettings());
			}
		});
	}

	private TextField createTextField(String id) {
		TextField textField = new TextField();
		textField.setId(id);
		GridPane.setHgrow(textField, Priority.ALWAYS);
		return textField;
	}

	private ComboBox<String> createComboBox(String id) {
		ComboBox<String> comboBox = new ComboBox<>();
		comboBox.setId(id);
		GridPane.setHgrow(comboBox, Priority.ALWAYS);
		return comboBox;
	}

}
