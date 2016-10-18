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

		if (line.length() == 0 || line.startsWith(Testfile.TAG_COMMENT)) { // comment
			// ignore
		} else if (line.startsWith(Testfile.TAG_AUTHOR)) { // author
			String author = getLineContent(Testfile.TAG_AUTHOR, line);
			testfile.setAuthor(author);
		} else if (line.startsWith(Testfile.TAG_TESTNAME)) { // testname
			String testname = getLineContent(Testfile.TAG_TESTNAME, line);
			testfile.setTestname(testname);
		} else if (line.startsWith(Testfile.TAG_DESCRIPTION)) { // description
			String description = getLineContent(Testfile.TAG_DESCRIPTION, line);
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
			for (; i < lines.length - 1; i++) {
				line = lines[i + 1].trim();
				if (line.length() == 0 || line.startsWith(Testfile.TAG_COMMENT)) {
					continue;
				} else if (line.startsWith(Testfile.TAG_FIRST_CHAR)) {
					break;
				} else {
					testfile.addSetupLine(line, i + 2);
				}
			}
		} else if (line.startsWith(Testfile.TAG_TEST)) { // test
			for (; i < lines.length - 1; i++) {
				line = lines[i + 1].trim();
				if (line.length() == 0 || line.startsWith(Testfile.TAG_COMMENT)) {
					continue;
				} else if (line.startsWith(Testfile.TAG_FIRST_CHAR)) {
					break;
				} else {
					testfile.addTestLine(line, i + 2);
				}
			}
		} else if (line.startsWith(Testfile.TAG_TEARDOWN)) { // teardown
			for (; i < lines.length - 1; i++) {
				line = lines[i + 1].trim();
				if (line.length() == 0 || line.startsWith(Testfile.TAG_COMMENT)) {
					continue;
				} else if (line.startsWith(Testfile.TAG_FIRST_CHAR)) {
					break;
				} else {
					testfile.addTeardownLine(line, i + 2);
				}
			}
		}
	}

	checkTestfileContent(testfile);
	return testfile;
}