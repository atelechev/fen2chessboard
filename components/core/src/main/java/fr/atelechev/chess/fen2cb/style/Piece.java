package fr.atelechev.chess.fen2cb.style;

public enum Piece {

	BLACK_PAWN,
	
	BLACK_ROOK,
	
	BLACK_KNIGHT,
	
	BLACK_BISHOP,
	
	BLACK_QUEEN,
	
	BLACK_KING,
	
	WHITE_PAWN,
	
	WHITE_ROOK,
	
	WHITE_KNIGHT,
	
	WHITE_BISHOP,
	
	WHITE_QUEEN,
	
	WHITE_KING;
	
	private final String imageFileName;
	
	private Piece() {
		this.imageFileName = name().toLowerCase() + ".png";
	}
	
	public String getImageFileName() {
		return this.imageFileName;
	}
	
}
