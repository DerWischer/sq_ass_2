/**
 * Splits up comma-seperated parameters into single parameters.
 * 
 * @param parameterLine
 * @return
 * @throws TestfileException
 */
private Object[] getParameters(String parameterLine) throws TestfileException {
	if (parameterLine.length() == 0) {
		return new Object[0];
	}
	String[] parStrArray = parameterLine.split(",");
	Object[] res = new Object[parStrArray.length];
	for (int i = 0; i < parStrArray.length; i++) {
		String strPar = parStrArray[i].toString().trim();

		Object value = null;
		try {
			value = interpretValue(strPar);
		} catch (KeywordException | AssertionError e) {
			throw TestfileExceptionHandler.InvalidParameter(strPar);
		}
		res[i] = value;
	}
	return res;
}