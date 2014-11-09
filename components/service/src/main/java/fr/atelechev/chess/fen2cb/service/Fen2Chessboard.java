package fr.atelechev.chess.fen2cb.service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import fr.atelechev.chess.fen2cb.exception.Fen2ChessboardException;
import fr.atelechev.chess.fen2cb.exception.IllegalFenException;

@Path("fen2cb")
public class Fen2Chessboard {

	@GET
	@Path("{fen: ([1-8prnbqkPRNBQK]{1,8}(/[1-8prnbqkPRNBQK]{1,8}){0,7})(/[wWbB])?}")
	@Produces({MediaType.TEXT_PLAIN})
	public Object getDiagram(@PathParam("fen") String fen) throws Fen2ChessboardException {
		if (fen.contains("6")) {
			throw new IllegalFenException("invalid FEN provided: " + fen);
		}
		return String.format("received: %1$s", fen);
	}
	
}
