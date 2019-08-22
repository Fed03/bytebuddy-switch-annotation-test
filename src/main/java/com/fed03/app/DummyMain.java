package com.fed03.app;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.agent.ByteBuddyAgent;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

public class DummyMain {
    public static void main(String[] args) {
        DummyAnnotated obj = new DummyAnnotated();

        /*if (obj.getClass().isAnnotationPresent(ToBeSwitched.class)) {
            System.out.println("replaced");
        } else if (obj.getClass().isAnnotationPresent(AnnTest.class)){
            System.out.println("not replaced");
        } else {
            System.out.println("no annotations");
        }*/

        AnnTest annotation = obj.getClass().getAnnotation(AnnTest.class);

        if (annotation != null) {

            System.out.println(annotation.developer());
            System.out.println("aaa");
        }
        else {
            System.out.println("no ann");
        }

    }
}

@AnnTest
class DummyAnnotated{
    @Override
    public String toString() {
        return "not trans";
    }
}

