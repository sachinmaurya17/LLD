package org.example.classes;

public class SyncronizedSingletionClass {

    private static SyncronizedSingletionClass instance;

    private SyncronizedSingletionClass() {}

    public static synchronized SyncronizedSingletionClass getInstance() {
        if(instance == null) {
            instance = new SyncronizedSingletionClass();
        }
        return instance;
    }
}
