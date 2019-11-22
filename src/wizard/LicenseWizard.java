package wizard;

import java.io.File;
import java.util.Optional;

import org.controlsfx.dialog.Wizard;
import org.controlsfx.dialog.WizardPane;

import javafx.application.Application;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

public class LicenseWizard extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		showWizardDialog(primaryStage);
	}

	private void showWizardDialog(Stage primaryStage) {
		//Wizard Dialog
		Wizard wizard = new Wizard();
		wizard.setTitle("License Installer");

		//raido
		RadioButton installRadio = new RadioButton("安装/更新License");
		RadioButton displayRadio = new RadioButton("查看当前License信息");
		RadioButton uninstallRadio = new RadioButton("卸载当前License");

		// Choose Type Page
		WizardPane chooseTypePage = new WizardPane() {
			{
				this.setHeaderText("\n\r   请选择操作类型！\n\r");

				//position
				int row = 2;
				int col = 2;
				GridPane gridPane = new GridPane();
				gridPane.setMinHeight(300);
				gridPane.setMinWidth(500);
				gridPane.setVgap(20);
				gridPane.setHgap(20);

				//choose type taggle group
				ToggleGroup toggle = new ToggleGroup();
				installRadio.setToggleGroup(toggle);
				displayRadio.setToggleGroup(toggle);
				uninstallRadio.setToggleGroup(toggle);
				installRadio.setSelected(true);

				//cursor setting
				installRadio.setCursor(Cursor.HAND);
				displayRadio.setCursor(Cursor.HAND);
				uninstallRadio.setCursor(Cursor.HAND);

				//add to grid pane
				gridPane.add(installRadio, col, row++);
				gridPane.add(displayRadio, col, row++);
				gridPane.add(uninstallRadio, col, row++);

				//put into dialog
				setContent(gridPane);
			}

			@Override
			public void onEnteringPage(Wizard wizard) {
				wizard.invalidProperty().unbind();
				wizard.invalidProperty().set(false);
			}
		};

		//Page Finish
		WizardPane installPage = new WizardPane() {
			BooleanProperty installFlagProperty = new SimpleBooleanProperty(false);

			{
				//position 
				int row = 2;
				int col = 2;
				GridPane gridPane = new GridPane();
				gridPane.setMinHeight(300);
				gridPane.setMinWidth(500);
				gridPane.setVgap(20);
				gridPane.setHgap(10);

				//row1
				Label titleLabel = new Label("请选择License文件：");
				TextField fileText = new TextField();
				fileText.setPrefWidth(200);
				fileText.setEditable(false);
				Button chooseBtn = new Button("...");
				FileChooser fileChooser = new FileChooser();
				ExtensionFilter extFilter = new ExtensionFilter("License", "*.lic");
				fileChooser.getExtensionFilters().add(extFilter);
				chooseBtn.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						File licFile = fileChooser.showOpenDialog(primaryStage);
						if (licFile != null) {
							fileText.setText(licFile.getName());
							fileText.setUserData(licFile);
						}
					}
				});

				//add into grid pane
				gridPane.add(titleLabel, col, row);
				gridPane.add(fileText, col + 1, row);
				gridPane.add(chooseBtn, col + 2, row);

				//row2 
				Label resultLabel = new Label("");
				resultLabel.setStyle("-fx-text-fill:red;-fx-font-weight:bold;");
				Button installBtn = new Button("安装License");
				installBtn.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						//TODO install license
						if (fileText.getUserData() == null) {
							resultLabel.setText("请选择License文件！");
							return;
						}
						installFlagProperty.set(true);
						resultLabel.setText("License安装成功！");

					}
				});

				//add to gridPane
				gridPane.add(installBtn, col, row + 1);
				gridPane.add(resultLabel, col, row + 2);
				GridPane.setRowSpan(resultLabel, 2);

				//set dialog content 
				this.setContent(gridPane);
			}

			@Override
			public void onEnteringPage(Wizard wizard) {
				this.setHeaderText("\n\r   安装/更新License文件\n\r");

				//bind install action
				wizard.invalidProperty().unbind();
				wizard.invalidProperty().bind(installFlagProperty.not());
			}

		};
		//Page Finish
		WizardPane displayPage = new WizardPane() {
			{
				this.setHeaderText("\n\r   查看当前License信息\n\r");

				int row = 1;//grid row
				int col = 1;
				GridPane gridPane = new GridPane();
				gridPane.setMinHeight(300);
				gridPane.setMinWidth(500);
				gridPane.setVgap(10);
				gridPane.setHgap(10);

				int maxWidth = 300;

				//row1
				Label subjectLabel = new Label("subject:");
				TextField subjectText = new TextField("subject");
				subjectText.setEditable(false);
				subjectText.setMaxWidth(maxWidth);
				gridPane.add(subjectLabel, col, row);
				gridPane.add(subjectText, col + 1, row++);

				/*//row2
				Label holderLabel = new Label("holder:");
				TextField holderText = new TextField("holder");
				holderText.setEditable(false);
				holderText.setMaxWidth(maxWidth);
				gridPane.add(holderLabel, col, row);
				gridPane.add(holderText, col + 1, row++);

				//row3
				Label issuerLabel = new Label("issuer:");
				TextField issuerText = new TextField("issuer");
				issuerText.setEditable(false);
				issuerText.setMaxWidth(maxWidth);
				gridPane.add(issuerLabel, col, row);
				gridPane.add(issuerText, col + 1, row++);*/

				//row4
				Label issuedLabel = new Label("issued:");
				TextField issuedText = new TextField("issued");
				issuedText.setEditable(false);
				issuedText.setMaxWidth(maxWidth);
				gridPane.add(issuedLabel, col, row);
				gridPane.add(issuedText, col + 1, row++);

				//row5
				Label notBeforeLabel = new Label("notBefore:");
				TextField notBeforeText = new TextField("notBefore");
				notBeforeText.setEditable(false);
				notBeforeText.setMaxWidth(maxWidth);
				gridPane.add(notBeforeLabel, col, row);
				gridPane.add(notBeforeText, col + 1, row++);

				//row6
				Label notAfterLabel = new Label("notAfter:");
				TextField notAfterText = new TextField("notAfter");
				notBeforeText.setEditable(false);
				notBeforeText.setMaxWidth(maxWidth);
				gridPane.add(notAfterLabel, col, row);
				gridPane.add(notAfterText, col + 1, row++);

				//row7
				Label consumerTypeLabel = new Label("consumer:");
				TextField consumerTypeText = new TextField("consumer");
				consumerTypeText.setEditable(false);
				consumerTypeText.setMaxWidth(maxWidth);
				gridPane.add(consumerTypeLabel, col, row);
				gridPane.add(consumerTypeText, col + 1, row++);

				//row8
				Label infoLabel = new Label("info:");
				TextArea infoText = new TextArea("info");
				infoText.setEditable(false);
				infoText.setMaxWidth(maxWidth);
				infoText.setPrefRowCount(2);
				gridPane.add(infoLabel, col, row);
				gridPane.add(infoText, col + 1, row++);

				//set dialog content
				this.setContent(gridPane);

			}

		};
		//Page Finish
		WizardPane uninstallPage = new WizardPane() {
			BooleanProperty uninstallFlagProperty = new SimpleBooleanProperty(false);
			{
				//position index
				int row = 2;
				int col = 2;
				GridPane gridPane = new GridPane();
				gridPane.setMinHeight(300);
				gridPane.setMinWidth(500);
				gridPane.setVgap(20);
				gridPane.setHgap(20);

				//init UI
				Label titleLabel = new Label("确认要卸载License？");
				Label resultLabel = new Label("");
				resultLabel.setStyle("-fx-text-fill:red;-fx-font-weight:bold;");
				Button uninstallBtn = new Button("卸载License");
				uninstallBtn.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						resultLabel.setText("成功卸载License！");
						uninstallFlagProperty.set(true);
					}
				});

				//add to gridPane
				gridPane.add(titleLabel, col, row++);
				gridPane.add(uninstallBtn, col, row++);
				gridPane.add(resultLabel, col, row);

				//set dialog content
				this.setContent(gridPane);
			}

			@Override
			public void onEnteringPage(Wizard wizard) {
				this.setHeaderText("\n\r   卸载License\n\r");

				//bind install action
				wizard.invalidProperty().unbind();
				wizard.invalidProperty().bind(uninstallFlagProperty.not());
			}
		};

		//Page Finish
		WizardPane finishPage = new WizardPane() {
			@Override
			public void onEnteringPage(Wizard wizard) {
				super.onEnteringPage(wizard);
				setHeader(null);
				setContentText("完成！");
			}
		};

		//define wizard flow
		wizard.setFlow(new Wizard.Flow() {
			WizardPane curPage;

			@Override
			public Optional<WizardPane> advance(WizardPane currentPage) {
				curPage = getNext(currentPage);
				return Optional.of(curPage);
			}

			@Override
			public boolean canAdvance(WizardPane currentPage) {
				if (currentPage == chooseTypePage)
					return true;

				return currentPage != curPage;
			}

			private WizardPane getNext(WizardPane currentPage) {
				if (currentPage == null) {
					return chooseTypePage;
				} else if (currentPage == chooseTypePage) {

					if (installRadio.isSelected()) {
						return installPage;
					}

					if (displayRadio.isSelected()) {
						return displayPage;
					}

					if (uninstallRadio.isSelected()) {
						return uninstallPage;
					}
				}
				return finishPage;
			}

		});
		// show wizard and wait for response
		wizard.showAndWait().ifPresent(result -> {
			if (result == ButtonType.FINISH) {
				System.out.println("Wizard finished, settings: " + wizard.getSettings());
			}
		});
	}

	public static void main(String[] args) {
		launch(args);
	}

}
