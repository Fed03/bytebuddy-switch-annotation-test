package com.fed03.bb;

import net.bytebuddy.jar.asm.Type;
import net.bytebuddy.description.field.FieldDescription;
import net.bytebuddy.description.field.FieldList;
import net.bytebuddy.description.method.MethodList;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.implementation.Implementation;
import net.bytebuddy.jar.asm.AnnotationVisitor;
import net.bytebuddy.jar.asm.ClassVisitor;
import net.bytebuddy.pool.TypePool;

import java.lang.annotation.Annotation;

import static net.bytebuddy.jar.asm.Opcodes.ASM4;
import static net.bytebuddy.jar.asm.Opcodes.ASM6;


public class Someting implements net.bytebuddy.asm.AsmVisitorWrapper {
    private final String annotationDescriptor;

    public Someting(Class<? extends Annotation> annotationType) {
        annotationDescriptor = Type.getDescriptor(annotationType);
    }

    @Override
    public int mergeWriter(int flags) {
        return 0;
    }

    @Override
    public int mergeReader(int flags) {
        return 0;
    }

    @Override
    public ClassVisitor wrap(TypeDescription instrumentedType, ClassVisitor classVisitor, Implementation.Context implementationContext, TypePool typePool, FieldList<FieldDescription.InDefinedShape> fields, MethodList<?> methods, int writerFlags, int readerFlags) {
         return new ClassVisitor(ASM4, classVisitor) {
             @Override
             public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
                 super.visit(version, access, name, signature, superName, interfaces);
             }

             @Override
            public AnnotationVisitor visitAnnotation(String descriptor, boolean visible) {
                if (descriptor.equals(annotationDescriptor)) {
                    return null;
                }
                return cv.visitAnnotation(descriptor, visible);
                //return super.visitAnnotation(descriptor, visible);
            }
        };
    }
}
