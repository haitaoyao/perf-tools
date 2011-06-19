package org.perf.tool;

import java.util.HashSet;
import java.util.Set;

public class CodeMeta {
	
	private static final String SCRIPT_SUFFIX = "_Script";

	private final String className;
	
	private final String simpleClassName;
	
	private Set<String> methodNames;

	public CodeMeta(String className){
		this.className = className;
		this.simpleClassName = getSimpleClassName(className);
	}
	
	private String getSimpleClassName(String className) {
		return className == null ? null : className.substring(className
				.lastIndexOf(".") + 1);
	}
	
	public void validate(){
		if(methodNames == null || methodNames.isEmpty()){
			throw new RuntimeException("No method found!!");
		}
		if(this.simpleClassName == null || this.simpleClassName.trim().length() == 0){
			throw new RuntimeException("simple class name null, className: " + this.className);
		}
	}
	

	public String getClassName() {
		return className;
	}

	public String getSimpleClassName() {
		return simpleClassName;
	}

	public Set<String> getMethodNames() {
		return new HashSet<String>(methodNames);
	}

	public void setMethodNames(Set<String> methodNames) {
		this.methodNames = methodNames;
	}
	
	public String getScriptFileName(){
		return this.getScriptClassName() + ".java";
	}
	
	public String getScriptClassName(){
		return this.simpleClassName + SCRIPT_SUFFIX;
	}
}
