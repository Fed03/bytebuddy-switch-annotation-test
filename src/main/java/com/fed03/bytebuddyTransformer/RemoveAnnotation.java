package com.fed03.bytebuddyTransformer;

import net.bytebuddy.description.field.FieldDescription;
import net.bytebuddy.description.field.FieldList;
import net.bytebuddy.description.method.MethodList;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.implementation.Implementation;
import net.bytebuddy.jar.asm.AnnotationVisitor;
import net.bytebuddy.jar.asm.ClassVisitor;
import net.bytebuddy.jar.asm.Type;
import net.bytebuddy.pool.TypePool;

import java.lang.annotation.Annotation;

import static net.bytebuddy.jar.asm.Opcodes.ASM4;


public class RemoveAnnotation implements net.bytebuddy.asm.AsmVisitorWrapper {
    private final String annotationDescriptor;

    RemoveAnnotation(Class<? extends Annotation> annotationType) {
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
            public AnnotationVisitor visitAnnotation(String descriptor, boolean visible) {
                if (descriptor.equals(annotationDescriptor)) {
                    return null;
                }

                return super.visitAnnotation(descriptor, visible);
            }
        };
    }
}
