public String toString() {
	StringBuilder builder = new StringBuilder();

	builder.append(TAG_AUTHOR + "\t" + getAuthor() + "\n");
	builder.append(TAG_TESTNAME + "\t" + getTestname() + "\n");
	builder.append(TAG_DESCRIPTION + "\t" + getDescription() + "\n");
	builder.append(TAG_REPEAT + "\t" + getRepetition() + "\n");

	if (hasLibraryFilePaths()) {
		for (String path : libPaths) {
			builder.append(TAG_LIBRARY_FILE + "\t" + path + "\n");
		}
	}

	if (hasSetupLines()) {
		builder.append(TAG_SETUP + "\n");
		for (Testline line : setupLines) {
			builder.append("\t" + line.text + "\n");
		}
	}

	if (hasTestLines()) {
		builder.append(TAG_TEST + "\n");
		for (Testline line : testLines) {
			builder.append("\t" + line.text + "\n");
		}
	}

	if (hasTeardownLines()) {
		builder.append(TAG_TEARDOWN + "\n");
		for (Testline line : teardownLines) {
			builder.append("\t" + line.text + "\n");
		}
	}
	return builder.toString();
}