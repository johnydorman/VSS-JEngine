package uk.johndorman.lib;

public class Vector2 {
	
	public float x,y;
	
	public Vector2(float x, float y) {
		this.x = x;
		this.y = y;
	}
	
	public float length() {
		return (float) Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
	}
	
	public Vector2 normalize() {
		float len = length();
		return new Vector2(x / len, y / len);
	}
	
	public int getRoundedX() {
		return Math.round(x);
	}
	
	public int getRoundedY() {
		return Math.round(y);
	}

	public Vector2 add(Vector2 other) {
		return new Vector2(x+other.x, y+other.y);
	}
}
