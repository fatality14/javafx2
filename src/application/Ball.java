package application;

public class Ball {
	private	Position position;
	private Vec2 dir;
	private Vec2 size;
	
	public Ball(Position position, Vec2 dir, Vec2 size) {
		super();
		this.position = position;
		this.dir = dir;
		this.size = size;
		
		this.position.getLoc().setX(this.position.getLoc().getX() - size.getX());
	}
	
	public Position getPosition() {
		return position;
	}
	public void setPosition(Position position) {
		this.position = position;
	}
	public Vec2 getDir() {
		return dir;
	}
	public void setDir(Vec2 dir) {
		this.dir = dir;
	}
	public Vec2 getSize() {
		return size;
	}
	public void setSize(Vec2 size) {
		this.size = size;
	}
	public float getRadius() {
		return position.getLoc().getX()/2;
	}
	
}
