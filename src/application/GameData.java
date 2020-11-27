package application;

public class GameData {
	private Ball ball;
	private Slider sliderL;
	private Slider sliderR;
	private float windowWidth;
	private float windowHeight;

	public GameData(Ball ball, Slider sliderL, Slider sliderR, float windowWidth, float windowHeight) {
		super();
		this.ball = ball;
		this.sliderL = sliderL;
		this.sliderR = sliderR;
		this.windowWidth = windowWidth;
		this.windowHeight = windowHeight;
	}

	public Ball getBall() {
		return ball;
	}
	public void setBall(Ball ball) {
		this.ball = ball;
	}
	public Slider getSliderL() {
		return sliderL;
	}
	public void setSliderL(Slider sliderL) {
		this.sliderL = sliderL;
	}
	public Slider getSliderR() {
		return sliderR;
	}
	public void setSliderR(Slider sliderR) {
		this.sliderR = sliderR;
	}
	public float getWindowWidth() {
		return windowWidth;
	}
	public void setWindowWidth(float windowWidth) {
		this.windowWidth = windowWidth;
	}
	public float getWindowHeight() {
		return windowHeight;
	}
	public void setWindowHeight(float windowHeight) {
		this.windowHeight = windowHeight;
	}
}
