package Chess.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

import java.io.IOException;

public class MultiPlayerScreenController extends Controller{

	@FXML
	void initialize(){

	}

	public void playAndHost(ActionEvent actionEvent) throws IOException {
		setCenter(2);
	}

	public void joinViaIP(ActionEvent actionEvent) {
		setCenter(3);
	}
}
