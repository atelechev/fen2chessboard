package fr.atelechev.chess.fen2cb;

import fr.atelechev.chess.fen2cb.exception.IllegalFenException;
import fr.atelechev.chess.fen2cb.validator.FenRowValidator;
import fr.atelechev.util.Objects;


public class Fen {
	
	private static final String LINE = "---------------------------------\n";

	private final char[][] rows;
	
	private final Side side;
	
	private Fen(char[][] rows, Side side) {
		assert rows != null && rows.length == 8;
		assert side != null;
		this.rows = rows;
		this.side = side;
	}
	
	public char[][] getRows() {
		switch (this.side) {
			case WHITE: return this.rows;
			case BLACK: return getReversedRows();
			default: {
				throw new IllegalStateException("Must not have entered here.");
			}
		}
	}
	
	public Side getSide() {
		return this.side;
	}
	
	public static Fen fromPath(String fenPath) throws IllegalFenException {
		Objects.requireNonNullNonEmptyString(fenPath, "fenPath");
		final String normalized = normalizeFenPath(fenPath);
		final String[] elements = normalized.split("/+", 9);
		if (elements.length < 9) {
			throw new IllegalFenException(String.format("Invalid FEN string: '%1$s'", fenPath));
		}
		final Side side = toSide(elements[8]);
		final FenRowValidator rowValidator = FenRowValidator.getInstance();
		final char[][] rows = new char[8][];
		for (int i = 0; i < 8; i++) {
			final String row = elements[i];
			if (!rowValidator.isFenRowValid(row)) {
				throw new IllegalFenException(String.format("Invalid FEN string: '%1$s'", fenPath));
			}
			rows[i] = row.toCharArray();
		}
		return new Fen(rows, side);
	}

	private static Side toSide(String strSide) throws IllegalFenException {
		assert strSide != null;
		switch (strSide.charAt(0)) {
			case 'w':
			case 'W': {
				return Side.WHITE;
			}
			case 'b':
			case 'B': {
				return Side.BLACK;
			}
			default: {
				throw new IllegalFenException(String.format("Illegal move side definition: '%1$s'", strSide));
			}
		}
	}

	private static String normalizeFenPath(String fenPath) {
		assert fenPath != null;
		fenPath = fenPath.trim();
		final char lastChar = Character.toLowerCase(fenPath.charAt(fenPath.length() - 1));
		if (lastChar == 'w' || lastChar == 'b') {
			return fenPath;
		}
		final StringBuilder bld = new StringBuilder(fenPath);
		if (fenPath.charAt(fenPath.length() - 1) != '/') {
			bld.append("/");
		}
		bld.append("w");
		return bld.toString();
	}
	
	@Override
	public String toString() {
		final StringBuilder bld = new StringBuilder(LINE.length() * 17).append(LINE);
		final char[][] rowsForView = getRows();
		for (char[] row : rowsForView) {
			bld.append(rowToString(row)).append(LINE);
		}
		return bld.toString();
	}
	
	private char[][] getReversedRows() {
		final char[][] reversedRows = new char[8][];
		for (int i = 0; i < 8; i++) {
			reversedRows[i] = reverseArray(this.rows[7 - i]);
		}
		return reversedRows;
	}

	private char[] reverseArray(char[] row) {
		assert row != null;
		final char[] reversed = new char[row.length];
		for (int i = 0; i < row.length; i++) {
			reversed[i] = row[(row.length - 1) - i];
		}
		return reversed;
	}

	private String rowToString(char[] row) {
		assert row != null;
		final StringBuilder bld = new StringBuilder(40).append("|");
		for (char c : row) {
			if (Character.isDigit(c)) {
				appendEmptyCells(Integer.parseInt(String.valueOf(c)), bld);
			}
			else {
				bld.append(cellToString(c));
			}
		}
		return bld.append("\n").toString();
	}
	
	private void appendEmptyCells(int nbCells, StringBuilder bld) {
		assert bld != null;
		for (int i = 0; i < nbCells; i++) {
			bld.append(cellToString(' '));
		}
	}
	
	private String cellToString(char piece) {
		return String.format(" %1$s |", piece);
	}
	
}
