package org.example.classes;

import org.example.interfaces.MakeMyTripInterface;

public class MakeMyTripFactory {

    public MakeMyTripInterface makeMyTrip(String name){
        if("BUS".equals(name)){
            return new TripWayBus();
        }else if ("TRIAN".equals(name)){
            return new TripWayTrains();
        }else if ("FLIGHT".equals(name)){
            return new TripWaysFlights();
        }else{
            return new TripWaysCabs();
        }
    }
}
