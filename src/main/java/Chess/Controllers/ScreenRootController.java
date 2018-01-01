package Chess.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

import java.io.IOException;

public class ScreenRootController {
	@FXML
	private Parent root;
	@FXML
	private Label footer;

	private GridPane gridPanes[] = new GridPane[4];
	private int current = 0;

	@FXML
	void initialize() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/ChessRecources/MainScreen.fxml"));
		gridPanes[0] = loader.load();
		System.out.println("done 0");
		((Controller)loader.getController()).init(this);
		loader = new FXMLLoader(getClass().getResource("/ChessRecources/MultiPlayerScreen.fxml"));
		gridPanes[1] = loader.load();
		System.out.println("done 1");
		((Controller)loader.getController()).init(this);
		loader = new FXMLLoader(getClass().getResource("/ChessRecources/PlayAndHostScreen.fxml"));
		gridPanes[2] = loader.load();
		((Controller)loader.getController()).init(this);
		loader = new FXMLLoader(getClass().getResource("/ChessRecources/PlayViaIPScreen.fxml"));
		gridPanes[3]= loader.load();
		((Controller)loader.getController()).init(this);
		((BorderPane)root).setCenter(gridPanes[0]);
	}

	void back(){
		setCenter(current - 1);
	}

	void setCenter(int index){
		if (index == 0) ((BorderPane)root).setBottom(footer);
		else ((BorderPane)root).setBottom(null);
		((BorderPane)root).setCenter(gridPanes[index]);
		current = index;
	}

	//getters and setters start :: *******************************************
	public BorderPane getRoot() {
		return ((BorderPane)root);
	}

	public GridPane[] getGridPanes() {
		return gridPanes;
	}

	//getters and setters end :: *******************************************
}
