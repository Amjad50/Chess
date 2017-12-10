package Chess.Controllers;

import javafx.fxml.FXML;

public class Controller {
	protected ScreenRootController controller;

	void init(ScreenRootController controller){
		this.controller = controller;
	}

	@FXML
	void back(){
		controller.back();
	}

	void setCenter(int index){
		controller.setCenter(index);
	}
}
