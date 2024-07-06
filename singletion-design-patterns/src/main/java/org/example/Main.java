package org.example;

import org.example.classes.SingletionClassTesting;

import java.lang.reflect.Constructor;

public class Main {
    public static void main(String[] args) {
        SingletionClassTesting instance1 = SingletionClassTesting.getInstance();
        SingletionClassTesting instance2 = null;

        try{
            Constructor cons[] = SingletionClassTesting.class.getConstructors();
            for(Constructor con : cons) {
                con.setAccessible(true);
                instance2 = (SingletionClassTesting) con.newInstance();
            }
        }catch (Exception e) {

        }


        if(instance1 instanceof  SingletionClassTesting)
            System.out.println("it is type of SingletionClassTesting");
    }
}