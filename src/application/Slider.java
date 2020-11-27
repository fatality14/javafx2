package application;

public class Slider {
	private	Position position;
	private float moveSpeed;
	private float width;
	private float thickness;

	public Slider(Position position, float moveSpeed, float width, float thickness) {
		super();
		this.position = position;
		this.moveSpeed = moveSpeed;
		this.width = width;
		this.thickness = thickness;
		
		this.position.getLoc().setX(this.position.getLoc().getX()); 
	}

	public Position getPosition() {
		return position;
	}
	public void setPosition(Position position) {
		this.position = position;
	}
	public float getMoveSpeed() {
		return moveSpeed;
	}
	public void setMoveSpeed(float moveSpeed) {
		this.moveSpeed = moveSpeed;
	}
	public float getWidth() {
		return width;
	}
	public void setWidth(float width) {
		this.width = width;
	}
	public float getThickness() {
		return thickness;
	}
	public void setThickness(float thickness) {
		this.thickness = thickness;
	}
}
