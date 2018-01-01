package Chess;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
	Scene scene;
	@Override
	public void start(Stage primaryStage) throws Exception {
		loadMainStage(primaryStage);
		primaryStage.show();
	}

	private void loadMainStage(Stage primaryStage) throws IOException {
		scene = new Scene(FXMLLoader.load(getClass().getResource("/ChessRecources/ScreenRoot.fxml")));
		primaryStage.setScene(scene);
		primaryStage.sizeToScene();
		primaryStage.setTitle("Chess");
		primaryStage.sizeToScene();
		primaryStage.setResizable(false);
		System.out.println("aa");
	}

	public static void main(String... args) {
		launch(args);
	}

	Scene getScene(){
		return scene;
	}

}
