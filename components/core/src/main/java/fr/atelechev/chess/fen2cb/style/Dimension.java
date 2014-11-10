package fr.atelechev.chess.fen2cb.style;

/**
 * Provides dimension description with integer units.
 * Unlike java.awt.Dimension, this one is immutable.
 */
public class Dimension {

	private final int width;
	
	private final int height;
	
	public Dimension() {
		this(0, 0);
	}
	
	public Dimension(int width, int height) {
		if (width < 0 || height < 0) {
			throw new IllegalArgumentException(String.format("Dimension cannot be negative: %1$s,%2$s", width, height));
		}
		this.width = width;
		this.height = height;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + height;
		result = prime * result + width;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null || !(obj instanceof Dimension)) {
			return false;
		}
		Dimension other = (Dimension) obj;
		return this.height == other.height
				&& this.width == other.width;
	}
	
	@Override
	public String toString() {
		return String.format("Dimension[%1$s,%2$s]", this.width, this.height);
	}
	
}
