package application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;
 
public class FXMLController {
	@FXML public Label form;
	
	@FXML private TextField actiontarget;
	
	@FXML protected void handle(KeyEvent event) {
		form.setText(actiontarget.getText());
	}
}
