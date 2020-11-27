package application;
	
import java.util.ArrayList;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class Main extends Application {
	public static boolean closeWindow = false;
	@Override
	public void start(Stage primaryStage) throws Exception {
		float width = 800;
		float height = 600;
		
		primaryStage.setTitle("JavaFX");

		Pane pane = FXMLLoader.load(getClass().getResource("index.fxml"));
		Font.loadFont(getClass().getResourceAsStream("SansitaSwashed-Regular.ttf"), 30);
		
		Vec2 ballDir = new Vec2(1,1);
		
		Ball ball = new Ball(new Position(new Vec2(width/2,height/2)), ballDir, new Vec2(25,25));
		Slider sliderL = new Slider(new Position(new Vec2(0,height/2-50)), 5, 100, 20);
		Slider sliderR = new Slider(new Position(new Vec2(width,height/2-50)), 5, 100, 20);
		
		GameData data = new GameData(ball, sliderL, sliderR, width, height);
		GameController game = new GameController(pane, data);
		
		game.embed();
		
		Scene scene = new Scene(pane, width, height);
		primaryStage.setScene(scene);
		
		scene.getStylesheets().add(this.getClass().getResource("application.css").toExternalForm());
		
		scene.addEventHandler(KeyEvent.KEY_PRESSED, game.sliderLPressedEvent);
		scene.addEventHandler(KeyEvent.KEY_PRESSED, game.sliderRPressedEvent);
		scene.addEventHandler(KeyEvent.KEY_RELEASED, game.sliderLReleasedEvent);
		scene.addEventHandler(KeyEvent.KEY_RELEASED, game.sliderRReleasedEvent);
		
		primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			@Override
			public void handle(WindowEvent event) {
				Main.closeWindow = true;
				Tools.print("Window closed...");
			}
		});

		primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
