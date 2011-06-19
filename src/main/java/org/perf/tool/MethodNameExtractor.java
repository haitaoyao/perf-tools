package org.perf.tool;

import java.util.HashSet;
import java.util.Set;

import org.objectweb.asm.MethodVisitor;



public class MethodNameExtractor extends ClassVisitorAdaptor{
	
	private static final Set<String> EXCLUSIONS;
	
	static{
		EXCLUSIONS = new HashSet<String>();
		EXCLUSIONS.add("<init>");
	}
	
	private final Set<String> methodNames = new HashSet<String>();

	@Override
	public MethodVisitor visitMethod(int access, String name, String desc,
			String signature, String[] exceptions) {
		if(access % 2 == 0){
			return null;
		}
		if(EXCLUSIONS.contains(name)){
			return null;
		}
		methodNames.add(name);
		return null;
	}

	public Set<String> getMethodNames() {
		return methodNames;
	}

}
