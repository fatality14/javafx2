package application;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public class GameController{
	private Pane parent;
	
	private Label ball = new Label();
	private Label sliderL = new Label();
	private Label sliderR = new Label();
	
	private boolean moveSliderLUp = false;
	private boolean moveSliderRUp = false;
	private boolean moveSliderL = false;
	private boolean moveSliderR = false;

	public void setMoveSliderR(boolean moveSliderR) {
		this.moveSliderR = moveSliderR;
	}
	private GameData data;
	
	SliderLKeyPressedEvent sliderLPressedEvent;
	SliderRKeyPressedEvent sliderRPressedEvent;
	SliderLKeyReleasedEvent sliderLReleasedEvent;
	SliderRKeyReleasedEvent sliderRReleasedEvent;
	
	GameController(Pane parent, GameData data){
		super();
		this.parent = parent;
		this.data = data;
		
		ball.getStyleClass().add("ball");
		sliderL.getStyleClass().add("sliderL");
		sliderR.getStyleClass().add("sliderR");
		
		update();
		sliderLPressedEvent = new SliderLKeyPressedEvent(this);
		sliderRPressedEvent = new SliderRKeyPressedEvent(this);
		sliderLReleasedEvent = new SliderLKeyReleasedEvent(this);
		sliderRReleasedEvent = new SliderRKeyReleasedEvent(this);
	}
	
	public void embed() {		
		parent.getChildren().add(ball);
		parent.getChildren().add(sliderL);
		parent.getChildren().add(sliderR);
		
		startBallThread();
		startSliderLThread();
		startSliderRThread();
	}
	public void update() {
		updateBall();
		updateSliderL();
		updateSliderR();
	}
	public void updateBall() {
		ball.setMinWidth(data.getBall().getSize().getX());
		ball.setMinHeight(data.getBall().getSize().getY());
		ball.setLayoutY(data.getBall().getPosition().getLoc().getY());
		ball.setLayoutX(data.getBall().getPosition().getLoc().getX());
	}
	public void updateSliderL() {
		sliderL.setMinWidth(data.getSliderL().getThickness());
		sliderL.setMinHeight(data.getSliderL().getWidth());
		sliderL.setLayoutY(data.getSliderL().getPosition().getLoc().getY());
		sliderL.setLayoutX(data.getSliderL().getPosition().getLoc().getX());
	}
	public void updateSliderR() {
		sliderR.setMinWidth(data.getSliderR().getThickness());
		sliderR.setMinHeight(data.getSliderR().getWidth());
		sliderR.setLayoutY(data.getSliderR().getPosition().getLoc().getY());
		sliderR.setLayoutX(data.getSliderR().getPosition().getLoc().getX() - data.getSliderL().getThickness());
	}
	public void startBallThread() {
		Runnable task = new Runnable() {
            public void run() {
                while(true) {
                	if(data.getBall().getPosition().getLoc().getY() > data.getWindowHeight() - data.getBall().getSize().getX()) {
                		data.getBall().getDir().setY(-data.getBall().getDir().getY());
            		}
            		if(data.getBall().getPosition().getLoc().getY() < 0) {
            			data.getBall().getDir().setY(-data.getBall().getDir().getY());
            		}
            		
            		if(data.getBall().getPosition().getLoc().getX() > data.getWindowWidth() - data.getBall().getSize().getY()) {
            			data.getBall().getDir().setX(-data.getBall().getDir().getX());
            		}
            		if(data.getBall().getPosition().getLoc().getX() < 0) {
            			data.getBall().getDir().setX(-data.getBall().getDir().getX());
            		}
            		
            		data.getBall().getPosition().getLoc().add(data.getBall().getDir());
            		updateBall();
                	
                	try {
						Thread.sleep(5);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
                }
            }
        };
        Thread thread = new Thread(task);
        thread.start();
	}
	public void startSliderLThread() {
		Runnable task = new Runnable() {
            public void run() {
                while(true) {
                	if(moveSliderL && moveSliderLUp) {
                		float move = data.getSliderL().getPosition().getLoc().getY() - data.getSliderL().getMoveSpeed();
            			data.getSliderL().getPosition().getLoc().setY(move);
                	}
                	if (moveSliderL && !moveSliderLUp) {
                		float move = data.getSliderL().getPosition().getLoc().getY() + data.getSliderL().getMoveSpeed();
            			data.getSliderL().getPosition().getLoc().setY(move);
                	}
                	
                	updateSliderL();
                	
                	try {
						Thread.sleep(5);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
                }
            }
        };
        Thread thread = new Thread(task);
        thread.start();
	}
	public void startSliderRThread() {
		Runnable task = new Runnable() {
            public void run() {
                while(true) {
                	if(moveSliderR && moveSliderRUp) {
                		float move = data.getSliderR().getPosition().getLoc().getY() - data.getSliderR().getMoveSpeed();
            			data.getSliderR().getPosition().getLoc().setY(move);
                	}
                	if (moveSliderR && !moveSliderRUp) {
                		float move = data.getSliderR().getPosition().getLoc().getY() + data.getSliderR().getMoveSpeed();
            			data.getSliderR().getPosition().getLoc().setY(move);
                	}
                	
                	updateSliderR();
                	
                	try {
						Thread.sleep(5);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
                }
            }
        };
        Thread thread = new Thread(task);
        thread.start();
	}
	
	public Label getBall() {
		return ball;
	}
	public void setBall(Label ball) {
		this.ball = ball;
	}
	public Label getSliderL() {
		return sliderL;
	}
	public void setSliderL(Label sliderL) {
		this.sliderL = sliderL;
	}
	public Label getSliderR() {
		return sliderR;
	}
	public void setSliderR(Label sliderR) {
		this.sliderR = sliderR;
	}
	public GameData getData() {
		return data;
	}
	public void setData(GameData data) {
		this.data = data;
	}
	public boolean isMoveSliderLUp() {
		return moveSliderLUp;
	}
	public void setMoveSliderLUp(boolean moveSliderLUp) {
		this.moveSliderLUp = moveSliderLUp;
	}
	public boolean isMoveSliderRUp() {
		return moveSliderRUp;
	}
	public void setMoveSliderRUp(boolean moveSliderRUp) {
		this.moveSliderRUp = moveSliderRUp;
	}
	public boolean isMoveSliderL() {
		return moveSliderL;
	}
	public void setMoveSliderL(boolean moveSliderL) {
		this.moveSliderL = moveSliderL;
	}
	public boolean isMoveSliderR() {
		return moveSliderR;
	}
}
