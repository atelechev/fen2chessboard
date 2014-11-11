package fr.atelechev.chess.fen2cb;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import fr.atelechev.chess.fen2cb.style.Dimension;
import fr.atelechev.chess.fen2cb.style.Piece;
import fr.atelechev.chess.fen2cb.style.Point;
import fr.atelechev.chess.fen2cb.style.RasterStyle;
import fr.atelechev.util.Objects;

public class RasterRenderer {
	
	private final RasterStyle style;

	public RasterRenderer(RasterStyle style) {
		Objects.requireNonNull(style, "style");
		this.style = style;
	}
	
	public BufferedImage renderDiagram(Fen fen, int size) {
		final BufferedImage board = this.style.getBoard();
		final char[][] fenData = fen.getRows();
		final Point origin = this.style.getCellsOrigin();
		final Dimension cellSize = this.style.getCellSize();
		int y = origin.getY();
		final Graphics2D g2 = (Graphics2D) board.getGraphics();
		for (int rowIndex = 0; rowIndex < fenData.length; rowIndex++) {
			int x = origin.getX();
			for (int fenItemIndex = 0; fenItemIndex < fenData[rowIndex].length; fenItemIndex++) {
				final char fenValue = fenData[rowIndex][fenItemIndex];
				if (Character.isDigit(fenValue)) {
					final int nbSkippedItems = Integer.parseInt(String.valueOf(fenValue));
					x += (nbSkippedItems * cellSize.getWidth());
				}
				else {
					final Piece piece = Piece.fromFen(fenValue);
					final BufferedImage pieceImage = this.style.getPiece(piece);
					g2.drawImage(pieceImage, x, y, null);
					x += cellSize.getWidth();
				}
			}
			y += cellSize.getHeight();
		}
		g2.dispose();
		return board;
	}
	
}
