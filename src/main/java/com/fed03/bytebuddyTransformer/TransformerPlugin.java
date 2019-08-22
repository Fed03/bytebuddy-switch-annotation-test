package com.fed03.bytebuddyTransformer;

import com.fed03.app.DummyAnnotation;
import net.bytebuddy.build.Plugin;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.dynamic.ClassFileLocator;
import net.bytebuddy.dynamic.DynamicType;

import java.io.IOException;
import java.lang.annotation.Annotation;

public class TransformerPlugin implements Plugin {

    @Override
    public DynamicType.Builder<?> apply(DynamicType.Builder<?> builder, TypeDescription typeDescription, ClassFileLocator classFileLocator) {
        return builder.visit(new RemoveAnnotation(DummyAnnotation.class)).annotateType(new DummyAnnotation(){

            @Override
            public String value() {
                return "bar";
            }

            @Override
            public Class<? extends Annotation> annotationType() {
                return DummyAnnotation.class;
            }
        });
    }

    @Override
    public void close() throws IOException {

    }

    @Override
    public boolean matches(TypeDescription typeDefinitions) {
        return typeDefinitions.getInheritedAnnotations().isAnnotationPresent(DummyAnnotation.class);
    }

}
