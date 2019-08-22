package com.fed03.bb;

import com.fed03.app.AnnTest;
import com.fed03.app.ToBeSwitched;
import net.bytebuddy.build.Plugin;
import net.bytebuddy.description.annotation.AnnotationDescription;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.dynamic.ClassFileLocator;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.implementation.FixedValue;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.HashSet;
import java.util.Set;

import static net.bytebuddy.matcher.ElementMatchers.named;

public class BbPlugin implements Plugin {
    private Set<String> typesAlreadyVisited = new HashSet<>();

    @Override
    public DynamicType.Builder<?> apply(DynamicType.Builder<?> builder, TypeDescription typeDescription, ClassFileLocator classFileLocator) {
        return builder.visit(new Someting(AnnTest.class))/*.annotateType(new AnnTest(){

            @Override
            public String developer() {
                return "Incredibl2e";
            }

            @Override
            public Class<? extends Annotation> annotationType() {
                return AnnTest.class;
            }
        })*/;
    }

    @Override
    public void close() throws IOException {

    }

    @Override
    public boolean matches(TypeDescription typeDefinitions) {
        return true;
        /*boolean annotationPresent = isAnnotationPresent(typeDefinitions);
        String typeName = typeDefinitions.getCanonicalName();

        if (annotationPresent && !typesAlreadyVisited.contains(typeName)) {
            typesAlreadyVisited.add(typeName);
            return true;
        }

        return false;*/
    }

    private boolean isAnnotationPresent(TypeDescription typeDefinitions) {
        return typeDefinitions.getInheritedAnnotations().isAnnotationPresent(AnnTest.class);
    }
}
