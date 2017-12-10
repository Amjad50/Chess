package Chess;

import Chess.Controllers.ScreenRootController;
import Chess.Sockets.mySocket;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ChessStarter {
	private boolean isSinglePlayer, isHost;
	private Scene chessScene;
	private mySocket socket;
	private ChessController controller;
	private Parent root;

	public void load(){
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/Chess/ChessGame.fxml"));
		try {
			root = loader.load();
			controller = loader.getController();
		}catch(IOException e){
			e.printStackTrace();
		}
	}

	public ChessController getController() {
		return controller;
	}

	public void start(Scene scene) {
		scene.setRoot(root);
	}

}
