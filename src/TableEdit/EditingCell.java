package TableEdit;


import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.control.TableCell;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class EditingCell extends TableCell<Person, String> {

	private TextField textField;

	public EditingCell() {
	}

	@Override
	public void startEdit() {
		if (!isEmpty()) {
			super.startEdit();
			createTextField();
			setText(null);
			setGraphic(textField);
			textField.requestFocus();
			textField.selectAll();
		}
	}

	@Override
	public void cancelEdit() {
		super.cancelEdit();

		setText((String) getItem());
		setGraphic(null);
	}

	@Override
	public void updateItem(String item, boolean empty) {
		super.updateItem(item, empty);

		if (empty) {
			setText(null);
			setGraphic(null);
		} else {
			if (isEditing()) {
				if (textField != null) {
					textField.setText(getString());
				}
				setText(null);
				setGraphic(textField);
			} else {
				setText(getString());
				setGraphic(null);
			}
		}
	}

	private void createTextField() {
		textField = new TextField(getString());
		textField.setMinWidth(this.getWidth() - this.getGraphicTextGap() * 2);
		//textfield miss focus to submit
		textField.focusedProperty().addListener((ObservableValue<? extends Boolean> observableVal, Boolean oldVal, Boolean newVal) -> {
			if (newVal == false) {
				commitEdit(textField.getText());
			}
		});
		//press KeyBoard -> ENTER
		textField.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				if(event.getCode() == KeyCode.ENTER){
					commitEdit(textField.getText());
				}
			}
		});
		//dataline miss focus to submit
		this.getParent().focusedProperty().addListener((ObservableValue<? extends Boolean> observableVal, Boolean oldVal, Boolean newVal) -> {
			if (newVal == false) {
				commitEdit(textField.getText());
			}
		});
	}

	private String getString() {
		return getItem() == null ? "" : getItem().toString();
	}
}