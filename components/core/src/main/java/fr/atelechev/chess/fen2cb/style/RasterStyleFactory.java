package fr.atelechev.chess.fen2cb.style;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;
import java.util.EnumMap;
import java.util.Map;

import javax.imageio.ImageIO;

class RasterStyleFactory {
	
	private final Path styleFolder;
	
	private final String styleName;
	
	private final Point padding;
	
	private final Point overlay;
	
	private RasterStyleFactory(Path styleFolder,
							  String styleName,
							  Point padding,
							  Point overlay) {
		this.styleFolder = styleFolder;
		this.styleName = styleName;
		this.padding = padding;
		this.overlay = overlay;
	}
	
	private RasterStyle build() {
		final BufferedImage board = readBoard();
		final Map<Piece, BufferedImage> pieces = readPieces();
		final BufferedImage overlayImage = readOverlayImage();
		final RasterStyle style = new RasterStyle(this.styleName, 
												  board, 
												  Collections.unmodifiableMap(pieces));
		style.setCellsOrigin(this.padding);
		style.setOverlayOrigin(this.overlay);
		style.setOverlayImage(overlayImage);
		return style;
	}

	private BufferedImage readOverlayImage() {
		try {
			return readImage("overlay.png");
		} catch (IOException ex) {
			// the overlay image is optional
			return null;
		}
	}

	private Map<Piece, BufferedImage> readPieces() {
		final Map<Piece, BufferedImage> pieces = new EnumMap<>(Piece.class);
		Dimension commonImageSize = null;
		for (Piece piece : Piece.values()) {
			try {
				final BufferedImage pieceImage = readImage(piece.getImageFileName());
				if (commonImageSize == null) {
					commonImageSize = new Dimension(pieceImage.getWidth(), pieceImage.getHeight());
				}
				validateImageSize(piece, commonImageSize, pieceImage);
				pieces.put(piece, pieceImage);
			} catch (IOException ex) {
				throw new IllegalStateException(String.format("Failed to read piece data from '%1$s' for style '%2$s': %3$s",
															  piece.getImageFileName(),
															  this.styleName, 
															  ex.getMessage()));
			}
		}
		return pieces;
	}
	
	private void validateImageSize(Piece piece, Dimension expectedSize, BufferedImage image) {
		assert piece != null;
		assert expectedSize != null;
		assert image != null;
		final Dimension thisPieceSize = new Dimension(image.getWidth(), image.getHeight());
		if (!expectedSize.equals(thisPieceSize)) {
			throw new IllegalStateException(String.format("Image size %1$s:%2$s is different from expected %3$s:%4$s for piece '%5$s' in style '%6$s'.",
														  thisPieceSize.getWidth(), thisPieceSize.getHeight(), 
														  expectedSize.getWidth(), expectedSize.getHeight(), 
														  piece.getImageFileName(), 
														  this.styleName));
		}
	}

	static RasterStyle buildStyle(Path styleFolder,
								  String styleName,
								  Point padding,
								  Point overlay) {
		assert styleFolder != null;
		assert styleName != null;
		assert padding != null;
		return new RasterStyleFactory(styleFolder, styleName, padding, overlay).build();
	}

	private BufferedImage readBoard() {
		try {
			return readImage("board.png");
		} catch (IOException ex) {
			throw new IllegalStateException(String.format("Failed to read board data for style '%1$s': %2$s", 
														  this.styleName, 
														  ex.getMessage()));
		}
	}
	
	private BufferedImage readImage(String fileName) throws IOException {
		assert fileName != null;
		final Path imageFile = this.styleFolder.resolve(fileName);
		try (InputStream is = Files.newInputStream(imageFile)) {
			return ImageIO.read(is);
		}
	}
	
}
