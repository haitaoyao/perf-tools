package org.perf.tool;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;

import org.objectweb.asm.ClassReader;

public class CodeAnalyzer {

	public CodeMeta analyze(String path, String targetClassName)
			throws IOException {
		File file = new File(path);
		CodeMeta meta = new CodeMeta(targetClassName);
		Set<String> methods = null;
		if (!file.exists()) {
			throw new IllegalArgumentException(path + " not exits!");
		}
		if (file.isDirectory()) {
			methods = analyzeDirectory(file, meta);
		} else if (path.endsWith(".class")) {
			methods = analyzeClassFile(new FileInputStream(file), file.getName(), meta);
		} else if (path.endsWith(".jar")) {
			methods = analyzeJarFile(file, meta);
		}
		meta.setMethodNames(methods);
		meta.validate();
		return meta;
	}

	private Set<String> analyzeClassFile(InputStream is, String fileName, CodeMeta meta)
			throws IOException {
		return (meta.getSimpleClassName() + ".class").equals(fileName) ? analyzeClassFile(is, meta) : null;
	}

	private Set<String> analyzeClassFile(InputStream is, CodeMeta meta)
			throws IOException {
		ClassReader cr = new ClassReader(is);
		MethodNameExtractor extractor = new MethodNameExtractor();
		cr.accept(extractor, 0);
		return extractor.getMethodNames();
	}

	private Set<String> analyzeDirectory(File path, CodeMeta meta)
			throws IOException {
		File[] files = path.listFiles();
		if (files == null || files.length == 0) {
			return Collections.emptySet();
		}
		Set<String> result = null;
		for (File f : files) {
			if (f.isFile()) {
				if (f.getName().endsWith(".jar")) {
					result = analyzeJarFile(f, meta);
				} else if (f.getName().endsWith(".class")) {
					result = analyzeClassFile(new FileInputStream(f),f.getName(),
							meta);
				}
			} else {
				result = analyzeDirectory(f, meta);
			}
			if (result != null && !result.isEmpty()) {
				break;
			}
		}
		return result;
	}

	private Set<String> analyzeJarFile(File f, CodeMeta meta)
			throws IOException {
		String targetClassName = meta.getClassName();
		JarInputStream in = new JarInputStream(new FileInputStream(f));
		JarEntry entry = null;
		Set<String> result = null;
		while ((entry = in.getNextJarEntry()) != null) {
			String name = entry.getName();

			if (name.endsWith(".class")) {
				String className = name.substring(0, name.length() - 6);
				className = className.replace('/', '.');
				if (targetClassName.equals(className)) {
					result = analyzeClassFile(in, meta);
					if (result != null && !result.isEmpty()) {
						break;
					}
				}
			}

		}
		in.close();
		return result;
	}
}
