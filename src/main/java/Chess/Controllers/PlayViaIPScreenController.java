package Chess.Controllers;

import Chess.ChessStarter;
import Chess.Sockets.Client;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class PlayViaIPScreenController extends Controller{

	@FXML
	private TextField ipAdress;
	@FXML
	private TextField port;
	@FXML
	private Label connectLabel;
	@FXML
	private Label errorMessage;

	private Client client;

	@Override
	void back() {
		setCenter(1);
	}

	public void connect() {
		if (ipAdress.getText().isEmpty() || port.getText().isEmpty()) {
			errorMessage.setText("You have to put an IP address and a port.");
		}else {
			try {
				client = new Client(ipAdress.getText(), Integer.parseInt(port.getText()), this);
				errorMessage.setText("");
			}catch (Exception e){
				if (e instanceof NumberFormatException){
					errorMessage.setText("the port number you entered is not valid.");
				}
			}
		}
	}

	public void hasConnected(){
		ChessStarter starter = new ChessStarter();
		starter.load();
		starter.start(controller.getRoot().getScene());
		starter.getController().init(false, false, client);
	}

	public Label getConnectLabel() {
		return connectLabel;
	}
}
