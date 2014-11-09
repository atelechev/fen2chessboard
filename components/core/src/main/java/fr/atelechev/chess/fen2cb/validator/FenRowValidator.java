package fr.atelechev.chess.fen2cb.validator;

/**
 * Validator for values representing a single row in FEN notation.
 */
public class FenRowValidator {

	private static FenRowValidator INSTANCE = new FenRowValidator();
	
	private FenRowValidator() {
		// nothing
	}
	
	public static FenRowValidator getInstance() {
		return INSTANCE;
	}
	
	public boolean isFenRowValid(String fenRow) {
		if (fenRow == null || fenRow.trim().isEmpty()) {
			return false;
		}
		// for validation, the char case does not matter 
		final String row = fenRow.trim().toUpperCase();
		int cellsCount = 0;
		for (char c : row.toCharArray()) {
			final int nbCellsForChar = getCellsCountForFenRowCharacter(c);
			if (nbCellsForChar == 0) {
				return false;
			}
			cellsCount += nbCellsForChar;
		}
		return cellsCount == 8;
	}
	
	private static int getCellsCountForFenRowCharacter(char c) {
		switch (c) {
			case '1':
			case '2':
			case '3':
			case '4':
			case '5':
			case '6':
			case '7':
			case '8': {
				return Integer.parseInt(String.valueOf(c));
			}
			case 'P':
			case 'R':
			case 'N':
			case 'B':
			case 'Q':
			case 'K': {
				return 1;
			}
			default: {
				return 0;
			}
		}
	}
	
}
