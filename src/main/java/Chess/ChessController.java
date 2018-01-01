package Chess;

import Chess.Sockets.Client;
import Chess.Sockets.Host;
import Chess.Sockets.generalSocket;
import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.SocketException;
import java.nio.ByteBuffer;

import static Chess.ChessBoard.boxes;

public class ChessController {

	@FXML
	private Pane piecesPlaceholder;
	@FXML
	private FlowPane grid;
	@FXML
	private StackPane root;
	static boolean isSinglePlayer, isHost, canPlay;
	private Scene chessScene;
	private generalSocket socket;
	private ChessBoard board;
	private InputStream inputStream;
	private OutputStream outputStream;


	@FXML
	void initialize() {
		board = new ChessBoard(this);
		grid.getChildren().addAll(boxes);
		piecesPlaceholder.getChildren().addAll(board.getStartingGrid());
	}

	Pane getPiecesPlaceholder() {
		return piecesPlaceholder;
	}

	StackPane getRoot() {
		return root;
	}

	public void init(boolean isSinglePlayer, boolean isHost, generalSocket socket) {
		ChessController.isSinglePlayer = isSinglePlayer;
		ChessController.isHost = isHost;
		this.socket = socket;
		if (socket != null) {
			try {
				inputStream = socket.getSocket().getInputStream();
				outputStream = socket.getSocket().getOutputStream();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
		if (isHost) {
			canPlay = true;
		} else {
			canPlay = false;
			ChessBoard.isWhitePlaying = false;
			startReceivingNext();
		}
	}

	public Client getClient() {
		return (Client) socket;
	}

	public Host getHost() {
		return (Host) socket;
	}

	void send(int a) {
		try {
			outputStream.write(ByteBuffer.allocate(4).putInt(a).array());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private byte[] bytes;

	public void startReceivingNext() {
		System.out.println("resieveing");
		bytes = new byte[12];
		new AnimationTimer() {
			int n;
			@Override
			public void handle(long now) {
				try {
					if (inputStream.available() > 0) {
						inputStream.read(bytes);
						int a = ByteBuffer.wrap(bytes).getInt();
						bytes = new byte[12];
						System.err.println("You received a message :: " + a);
						board.move(a / 100, a % 100);
						canPlay = true;
						this.stop();
					}

				} catch (IOException e) {
					if (e instanceof SocketException)
						System.exit(1);
				}
			}


		}.start();
	}
}
