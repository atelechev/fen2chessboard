package fr.atelechev.chess.fen2cb.service;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.atelechev.chess.fen2cb.exception.MissingStyleException;
import fr.atelechev.chess.fen2cb.style.DiagramStyle;
import fr.atelechev.chess.fen2cb.style.DiagramStyleFactory;

public class StyleProvider {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(StyleProvider.class);

	private static final StyleProvider INSTANCE = new StyleProvider();
	
	private static final String STYLE_NAME_DEFAULT = "default";
	
	private final Map<String, DiagramStyle> styleCache;
	
	private StyleProvider() {
		this.styleCache = new HashMap<>();
		final DiagramStyle defaultStyle = readStyle(STYLE_NAME_DEFAULT);
		if (defaultStyle != null) {
			this.styleCache.put(STYLE_NAME_DEFAULT, defaultStyle);
		}
		else {
			throw new IllegalStateException("Default diagram style is missing.");
		}
	}
	
	public static StyleProvider getInstance() {
		return INSTANCE;
	}
	
	public DiagramStyle getStyleByName(String styleName) throws MissingStyleException {
		if (styleName == null 
				|| styleName.trim().isEmpty()
				|| STYLE_NAME_DEFAULT.equalsIgnoreCase(styleName)) {
			return this.styleCache.get(STYLE_NAME_DEFAULT);
		}
		styleName = styleName.trim();
		if (this.styleCache.containsKey(styleName)) {
			return this.styleCache.get(styleName);
		}
		final DiagramStyle style = readStyle(styleName);
		if (style != null) {
			this.styleCache.put(styleName, style);
			return style;
		}
		throw new MissingStyleException(styleName);
	}
	
	private DiagramStyle readStyle(String styleName) {
		assert styleName != null;
		final Path stylesRoot = Paths.get(this.getClass().getClassLoader().getResource("fen2chessboard/styles").getFile());
		try {
			return DiagramStyleFactory.buildStyle(styleName, stylesRoot);
		} catch (IllegalArgumentException | IllegalStateException ex) {
			LOGGER.error("Failed to build a DiagramStyle: {}", ex.getMessage());
			return null;
		}
	}
	
}
