package fr.atelechev.chess.fen2cb.style;

/**
 * Wraps 2-dimensional coordinates.
 * Unlike java.awt.Point, is immutable.
 */
public class Point {

	private final int x;
	
	private final int y;
	
	public Point() {
		this(0, 0);
	}
	
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null || !(obj instanceof Point)) {
			return false;
		}
		Point other = (Point) obj;
		return this.x == other.x
				&& this.y == other.y;
	}
	
	@Override
	public String toString() {
		return String.format("Point[%1$s,%2$s]", this.x, this.y);
	}
	
}
