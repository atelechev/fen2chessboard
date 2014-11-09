package fr.atelechev.chess.fen2cb.service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import fr.atelechev.chess.fen2cb.Fen;
import fr.atelechev.chess.fen2cb.exception.Fen2ChessboardException;

@Path("fen2cb")
public class Fen2Chessboard {

	@GET
	@Path("{fen: ([1-8prnbqkPRNBQK]{1,8}(/[1-8prnbqkPRNBQK]{1,8}){7})(/[wWbB])?}")
	@Produces({MediaType.TEXT_PLAIN})
	public Object getDiagram(@PathParam("fen") String strFen) throws Fen2ChessboardException {
		final Fen fen = Fen.fromPath(strFen);
		
		return "OK";
	}
	
}
