package fr.atelechev.chess.fen2cb.style;

import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.util.Map;

import fr.atelechev.util.Objects;

public class RasterStyle extends DiagramStyle {

	private final BufferedImage board;
	
	private final Map<Piece, BufferedImage> pieces;
	
	private final Dimension cellSize;
	
	private BufferedImage overlayImage;
	
	protected RasterStyle(String name, BufferedImage board, Map<Piece, BufferedImage> pieces) {
		super(name);
		assert board != null;
		assert pieces != null && pieces.size() == Piece.values().length;
		this.board = board;
		this.pieces = pieces;
		this.cellSize = calculateCellSize();
		this.overlayImage = null;
	}
	
	private Dimension calculateCellSize() {
		final BufferedImage firstPiece = this.pieces.values().iterator().next();
		return new Dimension(firstPiece.getWidth(), firstPiece.getHeight());
	}

	/**
	 * Returns a copy of the board, which may be used to draw upon.
	 * @return BufferedImage a copy of the original board
	 */
	public BufferedImage getBoard() {
		final ColorModel colorModel = this.board.getColorModel();
		final boolean isAlphaPremultiplied = colorModel.isAlphaPremultiplied();
		final WritableRaster raster = this.board.copyData(null);
		return new BufferedImage(colorModel, raster, isAlphaPremultiplied, null);
	}
	
	public BufferedImage getPiece(Piece piece) {
		Objects.requireNonNull(piece, "piece");
		return this.pieces.get(piece);
	}

	public Dimension getCellSize() {
		return cellSize;
	}

	public BufferedImage getOverlayImage() {
		return overlayImage;
	}

	public void setOverlayImage(BufferedImage overlayImage) {
		this.overlayImage = overlayImage;
	}
	
	public boolean shouldDrawOverlay() {
		return getOverlayOrigin() != null && this.overlayImage != null;
	}
	
}
