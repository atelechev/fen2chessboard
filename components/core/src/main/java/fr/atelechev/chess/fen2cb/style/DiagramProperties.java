package fr.atelechev.chess.fen2cb.style;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;

import fr.atelechev.util.Objects;

public class DiagramProperties extends Properties {
	
	private static final long serialVersionUID = 1L;

	public DiagramProperties() {
		super();
		put(DiagramProperty.RENDER_TYPE, "raster");
		put(DiagramProperty.PADDING_LEFT, "0");
		put(DiagramProperty.PADDING_TOP, "0");
		put(DiagramProperty.OVERLAY_X, "");
		put(DiagramProperty.OVERLAY_Y, "");
	}
	
	public DiagramProperties(Path propertiesFile) {
		this();
		Objects.requireNonNull(propertiesFile, "propertiesFile");
		try (InputStream is = Files.newInputStream(propertiesFile)) {
			load(is);
		} catch (IOException ex) {
			throw new IllegalStateException(String.format("Failed to read %1$s: %2$s", 
														  propertiesFile, 
														  ex.getMessage()));
		}
	}
	
	public Object put(DiagramProperty property, Object value) {
		Objects.requireNonNull(property, "property");
		return super.put(property.toString(), value);
	}
	
	public Object get(DiagramProperty property) {
		if (property == null) {
			return null;
		}
		return super.get(property.toString());
	}
	
	public Integer getAsInteger(DiagramProperty property) {
		if (property == null) {
			return null;
		}
		final Object objValue = super.get(property.toString());
		if (objValue == null 
				|| (objValue instanceof String && ((String) objValue).trim().isEmpty())) {
			return null;
		}
		try {
			return Integer.parseInt(String.valueOf(objValue));
		} catch (NumberFormatException ex) {
			throw new IllegalArgumentException(String.format("Invalid property value for %1$s: '%2$s'", property, objValue));
		}
	}

}
