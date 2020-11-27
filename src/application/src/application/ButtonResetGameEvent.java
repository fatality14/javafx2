package application;

import java.util.Timer;
import java.util.TimerTask;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;
import javafx.util.Duration;

public class ButtonResetGameEvent implements EventHandler<MouseEvent>{
	GameController controller;
	
	ButtonResetGameEvent(GameController controller){
		super();
		this.controller = controller;
	}
	
	@Override
	public void handle(MouseEvent event) {
		controller.getData().setScoreL(0);
		controller.getData().setScoreR(0);
		
		Platform.runLater(new Runnable(){
			public void run() {
				controller.getData().getBall().getPosition().getLoc().setX(controller.getData().getWindowWidth()/2);
				controller.getData().getBall().getPosition().getLoc().setY(controller.getData().getWindowHeight()/2);
				
				controller.updateScore();
			}
		});
	}
}
