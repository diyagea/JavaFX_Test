package clipboard;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DataFormat;

public class ClipboardController {

	@FXML
	private Button getBtn;
	@FXML
	private Button putBtn;
	@FXML
	private TextArea text;
	
	@FXML
	private void put() {
		System.out.println("put");
		Clipboard clipboard = Clipboard.getSystemClipboard();
		ClipboardContent cc = new ClipboardContent();
		cc.putString(text.getText());
		clipboard.setContent(cc);
	}

	@FXML
	private void get() {
		System.out.println("get");
		Clipboard clipboard = Clipboard.getSystemClipboard();
		Object o = clipboard.getContent(DataFormat.PLAIN_TEXT);
		text.setText(o.toString());
	}
}
