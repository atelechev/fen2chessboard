package fr.atelechev.chess.fen2cb.style;

import java.awt.image.BufferedImage;
import java.util.Map;

import fr.atelechev.util.Objects;

public class RasterStyle extends DiagramStyle {

	private final BufferedImage board;
	
	private final Map<Piece, BufferedImage> pieces;
	
	protected RasterStyle(String name, BufferedImage board, Map<Piece, BufferedImage> pieces) {
		super(name);
		assert board != null;
		assert pieces != null && pieces.size() == Piece.values().length;
		this.board = board;
		this.pieces = pieces;
	}
	
	public BufferedImage getBoard() {
		return this.board;
	}
	
	public BufferedImage getPiece(Piece piece) {
		Objects.requireNonNull(piece, "piece");
		return this.pieces.get(piece);
	}
	
}
