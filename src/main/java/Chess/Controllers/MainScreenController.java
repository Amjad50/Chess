package Chess.Controllers;

import Chess.ChessStarter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;

import java.io.IOException;

public class MainScreenController extends Controller{

	@FXML
	private GridPane mainscreen;

	@FXML
	void initialize(){

	}

	public void Multiplayer() throws IOException{
		setCenter(1);   //sets the Screen to Screen id 1 => multiplayer screen
	}

	public void singlePlayer() {
		ChessStarter starter = new ChessStarter();
		starter.load();
		starter.start(controller.getRoot().getScene());
		starter.getController().init(true, true, null);
	}
}
