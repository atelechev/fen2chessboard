package fr.atelechev.chess.fen2cb.service;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import fr.atelechev.chess.fen2cb.exception.Fen2ChessboardException;

@Provider
public class Fen2ChessboardExceptionMapper implements ExceptionMapper<Fen2ChessboardException> {

	@Override
	public Response toResponse(Fen2ChessboardException ex) {
		return Response.status(400)
				.entity(ex.getMessage())
				.type(MediaType.TEXT_PLAIN)
				.build();
	}

}
