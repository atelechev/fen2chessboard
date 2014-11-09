package fr.atelechev.chess.fen2cb.exception;


public class IllegalFenException extends Fen2ChessboardException {

	private static final long serialVersionUID = 584066302450244941L;

	public IllegalFenException(String message) {
		super(message);
	}
	
}
