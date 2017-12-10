package Chess.Controllers;

import Chess.ChessStarter;
import Chess.Sockets.Host;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.net.BindException;

public class PlayAndHostScreenController extends Controller {
	@FXML
	private Button startButton;
	@FXML
	private Label errorScreen;
	@FXML
	private CheckBox customPort;
	@FXML
	private TextField port;
	@FXML
	private Label label;
	private Border defaultBorder;
	private Host host;

	@FXML
	void initialize() {
		customPort.selectedProperty().addListener((observable, oldValue, newValue) -> {
			if (newValue) {
				port.setVisible(true);
				port.setDisable(false);
			} else {
				port.setVisible(false);
				port.setDisable(true);
				resetPortStyle();
			}
		});
		defaultBorder = port.getBorder();
	}

	@FXML
	private void start() {
		ChessStarter starter = new ChessStarter();
		starter.load();
		starter.start(controller.getRoot().getScene());
		starter.getController().init(false, true, host);
	}

	@FXML
	private void connect() {
		try {
			if (customPort.isSelected()) {
				if (port.getText().isEmpty()) portError("You cant have an empty port");
				else {
					host = new Host(Integer.parseInt(port.getText()), this);
					resetPortStyle();
				}
			} else {
				host = new Host(454, this);
				resetPortStyle();
			}
		} catch (Exception e) {
			if (e instanceof NumberFormatException) portError("Enter a valid port number");
			if (e instanceof BindException)
				error("The port " + ((!port.getText().equals("454")) ? port.getText() : 454) + " is busy by other program.");
		}
	}

	private void resetPortStyle() {
		System.err.println("resetting");
		port.setBorder(defaultBorder);
		errorScreen.setText("");
	}

	private void error(String message) {
		errorScreen.setText(message);

	}

	private void portError(String message) {
		port.setBorder(new Border(new BorderStroke(Color.RED, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
		errorScreen.setText(message);
	}

	@Override
	void back() {
		super.back();
		try {
			if (host != null) {
				if (host.getSocket() != null) host.getSocket().close();
				host.getServerSocket().close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		resetPortStyle();
		startButton.setDisable(true);
		customPort.setSelected(false);
		label.setText("");
	}

	public Label getLabel() {
		return label;
	}

	public Button getStartButton() {
		return startButton;
	}
}
