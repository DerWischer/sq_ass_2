/**
 * Create a {@link Testfile}-Object from the specified lines
 * 
 * @param lines
 * @return
 * @throws TestfileException
 */
private Testfile readLinesToTestfile(String[] lines) throws TestfileException {
	Testfile testfile = new Testfile();
	for (int i = 0; i < lines.length; i++) {
		String line = lines[i].trim();
		if (line.length() == 0 || line.startsWith(Testfile.TAG_COMMENT)) { 
			// comment ignore
		} else if (line.startsWith(Testfile.TAG_AUTHOR)) { // author
			String author = getLineContent(Testfile.TAG_AUTHOR, line);
			testfile.setAuthor(author);
		} else if (line.startsWith(Testfile.TAG_TESTNAME)) { // testname
			String testname = getLineContent(Testfile.TAG_TESTNAME, line);
			testfile.setTestname(testname);
		} else if (line.startsWith(Testfile.TAG_DESCRIPTION)) { // description
			processDescription(testfile, lines, i);
		} else if (line.startsWith(Testfile.TAG_LIBRARY_FILE)) { // library
			String libPath = getLineContent(Testfile.TAG_LIBRARY_FILE, line);
			testfile.addLibraryFilePath(libPath);
		} else if (line.startsWith(Testfile.TAG_VARIABLE_FILE)) {
			String varPath = getLineContent(Testfile.TAG_VARIABLE_FILE, line);
			testfile.addVariableFilePath(varPath);
		} else if (line.startsWith(Testfile.TAG_REPEAT)) { // repeat
			String repeat = getLineContent(Testfile.TAG_REPEAT, line);
			testfile.setRepeat(repeat);
		} else if (line.startsWith(Testfile.TAG_SETUP)) { // setup
			processTestlines(testfile, Testfile.TAG_SETUP, lines, i);
		} else if (line.startsWith(Testfile.TAG_TEST)) { // test
			processTestlines(testfile, Testfile.TAG_TEST, lines, i);
		} else if (line.startsWith(Testfile.TAG_TEARDOWN)) { // teardown
			processTestlines(testfile, Testfile.TAG_TEARDOWN, lines, i);
		}
	}
	checkTestfileContent(testfile);
	return testfile;
}

private void processDescription(Testfile testfile, String[] lines, int currentIndex){
	String description = getLineContent(Testfile.TAG_DESCRIPTION, lines[currentIndex]);
	for (; i < lines.length - 1; i++) {
		line = lines[i + 1].trim();
		if (!line.startsWith(Testfile.TAG_FIRST_CHAR)) {
			if (line.length() > 0) {
				description += " " + line;
			}
		} else {
			break;
		}
	}
	testfile.setDescription(description);
}

private void processTestlines(Testfile testfile, String tag, String[] lines, int currentIndex){
	for (; currentIndex < lines.length - 1; currentIndex++) {
		line = lines[currentIndex + 1].trim();
		if (line.length() == 0 || line.startsWith(Testfile.TAG_COMMENT)) {
			continue;
		} else if (line.startsWith(Testfile.TAG_FIRST_CHAR)) {
			break;
		} else {
			if (tag.equals(Testfile.TAG_SETUP)){
				testfile.addSetupLine(line, currentIndex + 2);
			} else if (tag.equals(Testfile.TAG_TEST)){
				testfile.addTestLine(line, currentIndex + 2);
			} else if (tag.equals(Testfile.TAG_TEARDOWN)){
				testfile.addTeardownLine(line, currentIndex + 2);
			}	
		}
	}
}