package org.example.classes;

public class EaggerSingletionClass {

    private static  EaggerSingletionClass instance = null;
    private EaggerSingletionClass(){ }

    static{
        instance = new EaggerSingletionClass();
    }

    public static EaggerSingletionClass getInstance(){
        return instance;
    }

}
