package application;

public class Vec2 {
	private float x;
	private float y;
	
	public Vec2(float x, float y) {
		super();
		this.x = x;
		this.y = y;
	}

	public float getX() {
		return x;
	}
	public void setX(float x) {
		this.x = x;
	}
	public float getY() {
		return y;
	}
	public void setY(float y) {
		this.y = y;
	}
	public void add(Vec2 v) {
		x += v.getX();
		y += v.getY();
	}
}
