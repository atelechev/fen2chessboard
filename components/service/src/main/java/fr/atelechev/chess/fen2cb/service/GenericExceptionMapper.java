package fr.atelechev.chess.fen2cb.service;

import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.ClientErrorException;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.atelechev.chess.fen2cb.exception.Fen2ChessboardException;

/**
 * Exception/error handler for all exceptions not covered by specific mappers.
 */
@Provider
public class GenericExceptionMapper implements ExceptionMapper<Throwable> {

	private static final Logger LOGGER = LoggerFactory.getLogger(GenericExceptionMapper.class);
	
	@Override
	public Response toResponse(Throwable ex) {
		traceException(ex);
		final int status = getExceptionCode(ex);
		JsonObject errorMessage = getErrorMessageAsJson(status, ex);
		return Response.status(status)
						.entity(errorMessage)
						.type(MediaType.APPLICATION_JSON)
						.build();
	}
	
	private static void traceException(Throwable ex) {
		if (!(ex instanceof Fen2ChessboardException)) {
			LOGGER.error("Exception trace:", ex);
		} else {
			LOGGER.debug("Exception trace:", ex); // these ones are client side exceptions, no need to trace them too much...
		}
	}

	private static int getExceptionCode(Throwable ex) {
		assert ex != null;
		if (ex instanceof NotFoundException) {
			return 404;
		}
		if (ex instanceof Fen2ChessboardException 
			|| ex instanceof BadRequestException
			|| ex instanceof ClientErrorException) {
			return 400;
		}
		if (ex instanceof UnsupportedOperationException) {
			return 501;
		}
		// any other case is considered as internal server error:
		return 500;
	}
	
	private static JsonObject getErrorMessageAsJson(int status, Throwable ex) {
		return Json.createObjectBuilder()
				.add("status", status)
				.add("message", ex.getMessage())
				.build();
	}

}
