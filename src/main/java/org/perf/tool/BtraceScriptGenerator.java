package org.perf.tool;

import java.io.File;
import java.io.IOException;

public class BtraceScriptGenerator {

	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		if (args.length != 3) {
			System.err
					.println("Usage: sh generate_btrace_script.sh fullTargetClassName jarFileLocation scriptLocation");
			System.exit(1);
		}
		Args arg = new Args(args);
		generateScript(arg);
	}

	public static void generateScript(Args arg) throws IOException {
		CodeAnalyzer codeAnalyzer = new CodeAnalyzer();
		CodeMeta codeMeta = codeAnalyzer.analyze(arg.jarFileLocation,
				arg.targetClassName);
		if (codeMeta == null) {
			throw new IllegalArgumentException("Failed to analyze code");
		}
		BtraceCodeGenerator generator = new BtraceCodeGenerator();
		String code = generator.generateCode(codeMeta);
		FileUtils.writeStringToFile(new File(arg.scriptLocation, codeMeta
				.getScriptFileName()), code);
	}

	public static class Args {
		private final String targetClassName;
		private final String jarFileLocation;
		private final String scriptLocation;

		private Args(String[] args) {
			super();
			this.targetClassName = args[0];
			this.jarFileLocation = args[1];
			this.scriptLocation = args[2];
			validate();
		}

		public void validate() {
			File file = new File(jarFileLocation);
			if (!file.exists()) {
				throw new RuntimeException(this.jarFileLocation
						+ " not exits!!");
			}
			file = new File(scriptLocation);
			if (!file.exists()) {
				throw new RuntimeException(this.scriptLocation + " not exits!!");
			}
		}
	}
}
