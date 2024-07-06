package org.example.classes;

public class SingletionClassTesting {

    private static SingletionClassTesting instance = new SingletionClassTesting();
    private SingletionClassTesting(){
    }
    public static SingletionClassTesting getInstance() {
        return instance;
    }

    static {
        instance = new SingletionClassTesting();
    }
}
