package application;

import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public class GameController{
	private Pane parent;
	
	private Label ball = new Label();
	private Label sliderL = new Label();
	private Label sliderR = new Label();
	private Label scoreLText = new Label();
	private Label scoreRText = new Label();
	int scoreL = 0;
	int scoreR = 0;
	
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
		scoreLText.getStyleClass().add("score");
		scoreRText.getStyleClass().add("score");
		
		scoreLText.setLayoutX(data.getWindowWidth()/2-50);
		scoreLText.setLayoutY(data.getWindowHeight()/2);
		scoreRText.setLayoutX(data.getWindowWidth()/2+50);
		scoreRText.setLayoutY(data.getWindowHeight()/2);
		
		
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
		parent.getChildren().add(scoreLText);
		parent.getChildren().add(scoreRText);
		
		startBallThread();
		startSliderLThread();
		startSliderRThread();
	}
	public void update() {
		updateBall();
		updateSliderL();
		updateSliderR();
		updateScore();
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
	public void updateScore() {
		scoreLText.setText(scoreL+"");
		scoreRText.setText(scoreR+"");
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
            			scoreL++;
            			Platform.runLater(new Runnable(){
            				public void run() {
            					updateScore();
            				}
        				});
            			
            			
            			data.getBall().getPosition().getLoc().setX(data.getWindowWidth()/2);
            			data.getBall().getPosition().getLoc().setY(data.getWindowHeight()/2);
            		}
            		if(data.getBall().getPosition().getLoc().getX() < 0) {
            			scoreR++;
            			Platform.runLater(new Runnable(){
            				public void run() {
            					updateScore();
            				}
        				});
            			
            			data.getBall().getPosition().getLoc().setX(data.getWindowWidth()/2);
            			data.getBall().getPosition().getLoc().setY(data.getWindowHeight()/2);
            		}
            		
            		float distToLSlider = Float.MAX_VALUE;
            		float distToRSlider = Float.MAX_VALUE;
            		
            		float sliderTop;
            		float sliderBottom;
            		float ballOffset;
            		
            		sliderTop = data.getSliderL().getPosition().getLoc().getY();
            		sliderBottom = sliderTop + data.getSliderL().getWidth();
            		ballOffset = data.getBall().getPosition().getLoc().getY();
            		if(ballOffset > sliderTop && ballOffset < sliderBottom) {
            			distToLSlider = data.getBall().getPosition().getLoc().getX() - data.getSliderL().getPosition().getLoc().getX();
            		}
            		
            		sliderTop = data.getSliderR().getPosition().getLoc().getY();
            		sliderBottom = sliderTop + data.getSliderR().getWidth();
            		ballOffset = data.getBall().getPosition().getLoc().getY();
            		if(ballOffset > sliderTop && ballOffset < sliderBottom) {
            			distToRSlider = data.getSliderR().getPosition().getLoc().getX() - data.getBall().getPosition().getLoc().getX();
            		}
            		
            		if(distToLSlider < 20 || distToRSlider < 40) {
            			data.getBall().getDir().setX(-data.getBall().getDir().getX());
            		}
            		
            		data.getBall().getPosition().getLoc().add(data.getBall().getDir());
            		Platform.runLater(new Runnable(){
        				public void run() {
        					updateBall();
        				}
    				});
                	
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
                		if(move >= 0) {
                			data.getSliderL().getPosition().getLoc().setY(move);
                		}
                		else {
                			data.getSliderL().getPosition().getLoc().setY(0);
                		}
                	}
                	if (moveSliderL && !moveSliderLUp) {
                		float move = data.getSliderL().getPosition().getLoc().getY() + data.getSliderL().getMoveSpeed();
                		if(move <= data.getWindowHeight() - data.getSliderL().getWidth()) {
                			data.getSliderL().getPosition().getLoc().setY(move);
                		}
                		else {
                			data.getSliderL().getPosition().getLoc().setY(data.getWindowHeight() - data.getSliderL().getWidth());
                		}
                	}
                	
                	Platform.runLater(new Runnable(){
        				public void run() {
        					updateSliderL();
        				}
    				});
                	
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
                		if(move >= 0) {
                			data.getSliderR().getPosition().getLoc().setY(move);
                		}
                		else {
                			data.getSliderR().getPosition().getLoc().setY(0);
                		}
                	}
                	if (moveSliderR && !moveSliderRUp) {
                		float move = data.getSliderR().getPosition().getLoc().getY() + data.getSliderR().getMoveSpeed();
                		if(move <= data.getWindowHeight() - data.getSliderR().getWidth()) {
                			data.getSliderR().getPosition().getLoc().setY(move);
                		}
                		else {
                			data.getSliderR().getPosition().getLoc().setY(data.getWindowHeight() - data.getSliderR().getWidth());
                		}
                	}
                	
                	Platform.runLater(new Runnable(){
        				public void run() {
        					updateSliderR();
        				}
    				});
                	
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
