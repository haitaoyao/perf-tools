package org.perf.tool;

import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.Attribute;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.MethodVisitor;

public class ClassVisitorAdaptor implements ClassVisitor {

	
	public AnnotationVisitor visitAnnotation(String paramString,
			boolean paramBoolean) {

		return null;
	}

	
	public void visitAttribute(Attribute paramAttribute) {

	}

	
	public void visitEnd() {

	}

	
	public FieldVisitor visitField(int paramInt, String paramString1,
			String paramString2, String paramString3, Object paramObject) {

		return null;
	}

	
	public void visitInnerClass(String paramString1, String paramString2,
			String paramString3, int paramInt) {

	}

	
	public MethodVisitor visitMethod(int access, String name, String desc,
			String signature, String[] exceptions) {

		return null;
	}

	
	public void visitOuterClass(String paramString1, String paramString2,
			String paramString3) {

	}

	
	public void visitSource(String paramString1, String paramString2) {

	}

	
	public void visit(int paramInt1, int paramInt2, String paramString1,
			String paramString2, String paramString3,
			String[] paramArrayOfString) {
	}

}
