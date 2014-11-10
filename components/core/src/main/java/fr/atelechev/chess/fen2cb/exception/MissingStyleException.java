package fr.atelechev.chess.fen2cb.exception;

public class MissingStyleException extends Fen2ChessboardException {

	private static final long serialVersionUID = 1L;

	public MissingStyleException(String styleName) {
		super(String.format("Failed to find the style '%1$s'. Please check its name.", styleName));
	}
	
}
