package fr.atelechev.chess.fen2cb.service;

import javax.ws.rs.ext.Provider;

import fr.atelechev.chess.fen2cb.exception.Fen2ChessboardException;
import fr.atelechev.util.web.GenericExceptionMapper;

@Provider
public class Fen2ChExceptionMapper extends GenericExceptionMapper {

	@Override
	protected void traceException(Exception exception) {
		if (!(exception instanceof Fen2ChessboardException)) {
			LOGGER.error(EXCEPTION_TRACE, exception);
		}
		else {
			LOGGER.debug(EXCEPTION_TRACE, exception);
		}
	}
	
	@Override
	protected int getCustomExceptionCode(Exception ex) {
		if (ex instanceof Fen2ChessboardException) {
			return 400;
		}
		return -1;
	}
	
}
