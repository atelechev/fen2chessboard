package fr.atelechev.chess.fen2cb.style;

public enum DiagramProperty {

	RENDER_TYPE,
	
	PADDING_TOP,

	PADDING_LEFT,
	
	OVERLAY_X,
	
	OVERLAY_Y;
	
	@Override
	public String toString() {
		return this.name().toLowerCase().replaceAll("_+", ".");
	}
	
}
