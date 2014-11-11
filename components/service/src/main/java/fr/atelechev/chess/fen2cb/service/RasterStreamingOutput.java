package fr.atelechev.chess.fen2cb.service;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.imageio.ImageIO;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.StreamingOutput;

import fr.atelechev.util.Objects;

public class RasterStreamingOutput implements StreamingOutput {

	
	private final BufferedImage image;
	
	public RasterStreamingOutput(BufferedImage image) {
		Objects.requireNonNull(image, "image");
		this.image = image;
	}
	
	@Override
	public void write(OutputStream output) throws IOException,
												  WebApplicationException {
		try (ByteArrayOutputStream byteOutput = new ByteArrayOutputStream()) {
			ImageIO.write(this.image, "png", byteOutput);
			byteOutput.flush();
			final byte[] imageBytes = byteOutput.toByteArray();
			output.write(imageBytes);
		}
	}

}
