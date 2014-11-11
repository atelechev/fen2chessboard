package fr.atelechev.chess.fen2cb.style;

public enum Piece {

	BLACK_PAWN('p'),
	
	BLACK_ROOK('r'),
	
	BLACK_KNIGHT('n'),
	
	BLACK_BISHOP('b'),
	
	BLACK_QUEEN('q'),
	
	BLACK_KING('k'),
	
	WHITE_PAWN('P'),
	
	WHITE_ROOK('R'),
	
	WHITE_KNIGHT('N'),
	
	WHITE_BISHOP('B'),
	
	WHITE_QUEEN('Q'),
	
	WHITE_KING('K');
	
	private final String imageFileName;
	
	private final char fen;
	
	private Piece(char fen) {
		this.imageFileName = name().toLowerCase() + ".png";
		this.fen = fen;
	}
	
	public String getImageFileName() {
		return this.imageFileName;
	}
	
	public static Piece fromFen(char fenChar) {
		for (Piece piece : values()) {
			if (piece.fen == fenChar) {
				return piece;
			}
		}
		throw new IllegalArgumentException(String.format("Invalid value for piece FEN: '%1$s'", fenChar));
	}
	
}
