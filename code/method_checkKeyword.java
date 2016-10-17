private boolean checkKeyword(String keyword) {
	String libName = null;
	if (keyword.contains(".")) {
		String[] split = keyword.split("\\.");
		if (split.length != 2) {
			return false;
		}
		libName = split[0];
		keyword = split[1];
	}

	if (libName != null && !checkLibraryName(libName)) {
		return false;
	}

	String REGEX_KEYWORD = "[\\w \\d \\s \\. ß äöü ÄÖÜ]{1,}";
	if (!Pattern.matches(REGEX_KEYWORD, keyword)) {
		return false;
	}
	return true;
}