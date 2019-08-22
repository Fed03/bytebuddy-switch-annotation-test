package com.fed03.app;

public class AppMain {
    public static void main(String[] args) {
        SomeClass obj = new SomeClass();

        DummyAnnotation annotation = obj.getClass().getAnnotation(DummyAnnotation.class);
        if (annotation != null) {

            System.out.println(annotation.value());
        }
        else {
            System.out.println("no annotation");
        }

    }
}

