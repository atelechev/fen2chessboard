package fr.atelechev.chess.fen2cb.validator;

import java.util.regex.Pattern;

public class StyleNameValidator {

	private static final Pattern PATTERN_NAME = Pattern.compile("^[0-9a-zA-Z_]+$");
	
	public static final boolean isStyleNameValid(String name) {
		if (name == null || name.isEmpty()) {
			return false;
		}
		return PATTERN_NAME.matcher(name).matches();
	}
	
}
