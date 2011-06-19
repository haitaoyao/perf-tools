package org.perf.tool;


public class BtraceCodeGenerator {

	private static final String CODE_BODY_NAME = "code_body.txt";

	private static final String CODE_HEADER_NAME = "code_header.txt";

	private static final String HEADER;

	private static final String BODY;

	static {
		String header = "";
		String body = "";
		header = FileUtils.toString(BtraceCodeGenerator.class
				.getResourceAsStream(CODE_HEADER_NAME));
		body = FileUtils.toString(BtraceCodeGenerator.class
				.getResourceAsStream(CODE_BODY_NAME));
		HEADER = header;
		BODY = body;
	}

	public String generateCode(CodeMeta codeMeta) {
		if (codeMeta == null) {
			return "";
		}
		StringBuilder result = new StringBuilder(HEADER.replaceAll("FILE_NAME",
				codeMeta.getScriptClassName()));
		for (String mName : codeMeta.getMethodNames()) {
			result.append(BODY
					.replaceAll("CLASS_NAME", codeMeta.getClassName())
					.replaceAll("METHOD_NAME", mName));
		}
		return result.append("}").toString();
	}
}
