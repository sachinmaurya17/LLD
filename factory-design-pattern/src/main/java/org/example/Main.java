package org.example;

import org.example.classes.MakeMyTripFactory;

public class Main {
    public static void main(String[] args) {
        System.out.println("-------------------- MAKE MY TRIP 😂😂-----------------------------");
        MakeMyTripFactory makeMyTripFactory = new MakeMyTripFactory();
        System.out.println(makeMyTripFactory.makeMyTrip("BUS").searchRoute());

    }
}