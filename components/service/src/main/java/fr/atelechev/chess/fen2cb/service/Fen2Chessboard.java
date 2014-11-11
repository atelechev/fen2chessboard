package fr.atelechev.chess.fen2cb.service;

import java.awt.image.BufferedImage;

import javax.ws.rs.GET;
import javax.ws.rs.MatrixParam;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import fr.atelechev.chess.fen2cb.Fen;
import fr.atelechev.chess.fen2cb.RasterRenderer;
import fr.atelechev.chess.fen2cb.exception.Fen2ChessboardException;
import fr.atelechev.chess.fen2cb.style.DiagramStyle;
import fr.atelechev.chess.fen2cb.style.RasterStyle;

@Path("fen2cb")
public class Fen2Chessboard {

	private final StyleProvider styleProvider;
	
	public Fen2Chessboard() {
		this.styleProvider = StyleProvider.getInstance();
	}
	
	@GET
	@Path("{fen: ([1-8prnbqkPRNBQK]{1,8}(/[1-8prnbqkPRNBQK]{1,8}){7})(/[wWbB])?}")
	@Produces({MediaType.TEXT_PLAIN})
	public Response getDiagram(@PathParam("fen") String strFen,
							   @MatrixParam("size") int size,
							   @MatrixParam("style") String styleName) throws Fen2ChessboardException {
		final Fen fen = Fen.fromPath(strFen);
		if ("text".equalsIgnoreCase(styleName)) {
			return Response.ok(fen.toString(), MediaType.TEXT_PLAIN).build();
		}
		final DiagramStyle diagramStyle = this.styleProvider.getStyleByName(styleName);
		if (diagramStyle instanceof RasterStyle) {
			return renderRaster(fen, (RasterStyle) diagramStyle, size);
		}
		throw new Fen2ChessboardException(String.format("Unsupported rendering type for style '%1$s'", diagramStyle.getName()));
	}

	private Response renderRaster(Fen fen, RasterStyle style, int size) {
		assert fen != null;
		assert style != null;
		final RasterRenderer renderer = new RasterRenderer(style);
		final BufferedImage diagram = renderer.renderDiagram(fen, size);
		return Response.ok(new RasterStreamingOutput(diagram), "image/png")
					   .header("Content-Disposition", "filename=diagram.png")
					   .build();
	}
	
}
