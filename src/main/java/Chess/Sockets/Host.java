package Chess.Sockets;

import Chess.Controllers.PlayAndHostScreenController;
import javafx.animation.AnimationTimer;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Host extends generalSocket {
	private ServerSocket serverSocket;
	private PlayAndHostScreenController controller;

	public Host(int port, PlayAndHostScreenController controller) throws IOException {
		serverSocket = new ServerSocket(port);
		this.controller = controller;
		connect();
	}


	private void connect() {
		controller.getLabel().setText("connecting");
		final double[] number = {1, -0.01};
		new AnimationTimer(){
			@Override
			public void handle(long now) {
				if (socket == null){
					controller.getLabel().setOpacity(number[0]);
					number[0] += number[1];
					if (number[0] < 0 || number[0] >= 1.1){
						number[1] = -number[1];
					}
				}else{
					controller.getLabel().setText("Connected");
					controller.getStartButton().setDisable(false);
					controller.getLabel().setOpacity(1);
					this.stop();
				}
			}
		}.start();

		new Thread(()->{
			try {
				socket = serverSocket.accept();
			} catch (IOException e) {
		e.printStackTrace();
		}
		}).start();
	}

	public Socket getSocket() {
		return socket;
	}

	public ServerSocket getServerSocket() {
		return serverSocket;
	}
}
