package fr.atelechev.chess.fen2cb.style;

import java.nio.file.Files;
import java.nio.file.Path;

import fr.atelechev.chess.fen2cb.validator.StyleNameValidator;
import fr.atelechev.util.Objects;

public class DiagramStyleFactory {

	private static final String PROPERTIES_FILE_NAME = "diagram.properties";
	
	private DiagramProperties properties;
	
	private final Path stylesRoot;
	
	private final String styleName;
	
	private DiagramStyleFactory(String name, Path stylesRoot) {
		this.properties = null;
		this.stylesRoot = stylesRoot;
		this.styleName = name;
	}
	
	private DiagramStyle build() {
		validateStyleName();
		final Path styleFolder = validateStyleFolder(this.styleName, stylesRoot);
		final Path propsFile = styleFolder.resolve(PROPERTIES_FILE_NAME);
		this.properties = new DiagramProperties(propsFile);
		final Point padding = readPadding();
		final Point overlay = readOverlay();
		final String renderType = (String) this.properties.get(DiagramProperty.RENDER_TYPE);
		if (renderType == null) {
			throw new IllegalArgumentException(String.format("Undefined render.type in %1$s.", propsFile));
		}
		switch (renderType.toLowerCase()) {
			case "raster": return RasterStyleFactory.buildStyle(styleFolder, this.styleName, padding, overlay);
			default: {
				throw new UnsupportedOperationException(String.format("Invalid or unsupported render type: %1$s in '%2$s'", 
																	  renderType, 
																	  propsFile));
			}
		}
	}
	
	private Point readOverlay() {
		assert this.properties != null;
		final Integer overlayX = this.properties.getAsInteger(DiagramProperty.OVERLAY_X);
		final Integer overlayY = this.properties.getAsInteger(DiagramProperty.OVERLAY_Y);
		if (overlayX == null || overlayY == null) {
			return null;
		}
		return new Point(overlayX, overlayY);
	}

	public static DiagramStyle buildStyle(String name, Path stylesRoot) {
		return new DiagramStyleFactory(name, stylesRoot).build();
	}
	
	private Point readPadding() {
		assert this.properties != null;
		final Integer paddingLeft = this.properties.getAsInteger(DiagramProperty.PADDING_LEFT);
		final Integer paddingTop = this.properties.getAsInteger(DiagramProperty.PADDING_TOP);
		return new Point(paddingLeft != null ? paddingLeft : 0, paddingTop != null ? paddingTop : 0);
	}

	private void validateStyleName() {
		if (!StyleNameValidator.isStyleNameValid(this.styleName)) {
			throw new IllegalArgumentException(String.format("Invalid style name: %1$s", this.styleName));
		}
	}
	
	private Path validateStyleFolder(String name, Path stylesRoot) {
		Objects.requireNonNull(stylesRoot, "stylesRoot");
		final Path styleFolder = stylesRoot.resolve(name);
		if (!Files.exists(styleFolder)
				|| !Files.isDirectory(styleFolder)
				|| !Files.isReadable(styleFolder)) {
			throw new IllegalArgumentException(String.format("Inaccessible style folder: %1$s", styleFolder.toString()));
		}
		return styleFolder;
	}
	
}
