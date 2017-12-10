package Chess.Sockets;

import Chess.Controllers.PlayViaIPScreenController;
import javafx.animation.AnimationTimer;
import javafx.scene.control.Label;

import java.net.Socket;

public class Client extends mySocket{
	private String ip;
	private int port;
	private PlayViaIPScreenController controller;
	private boolean connected;
	private AnimationTimer timer;

	public Client(String ip, int port, PlayViaIPScreenController controller) {
		this.ip = ip.replaceAll(" ", "");
		this.port = port;
		this.controller = controller;
		connected = false;
		connectStart();
	}

	private void connectStart() {
		connect();
	}

	private void connect(){
		new Thread(() -> {
			while (socket == null) {
				try {
					Thread.sleep(20);
					socket = new Socket(ip, port);
					connected = true;
				} catch (Exception e) {
					socket = null;
				}
			}

		}).start();

		editLabel("connecting");
		final double[] number = {1, -0.01};
		timer = new AnimationTimer(){
			@Override
			public void handle(long now) {
				if (socket == null){
					controller.getConnectLabel().setOpacity(number[0]);
					number[0] += number[1];
					if (number[0] < 0 || number[0] >= 1.1){
						number[1] = -number[1];
					}
				}else{
					controller.getConnectLabel().setText("Connected");
					controller.getConnectLabel().setOpacity(1);
					connected = true;
					controller.hasConnected();
					timer.stop();
				}
			}
		};
		timer.start();
	}


	private void editLabel(String s) {
		controller.getConnectLabel().setText(s);
	}

	public boolean isConnected() {
		return connected;
	}
}
