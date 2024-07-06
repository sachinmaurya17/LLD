package org.example.classes;

public class LazySingletionClass {

    private static LazySingletionClass instance;

    private LazySingletionClass(){}

    private static LazySingletionClass getInstance(){
        if(instance == null){
            instance = new LazySingletionClass();
        }
        return instance;
    }

}
