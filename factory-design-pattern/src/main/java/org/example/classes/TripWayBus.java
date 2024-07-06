package org.example.classes;

import org.example.interfaces.TripWaysBusInterfaceInterface;

public class TripWayBus implements TripWaysBusInterfaceInterface {
    @Override
    public String searchRoute() {
        return "bus search is working fine";
    }
}
