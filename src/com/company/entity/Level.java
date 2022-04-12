package com.company.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.company.config.Constants.MAX_PASSENGERS_COUNT;

public class Level {
    private List<Passenger> passengers = new ArrayList<>();
    private int number;

    public Level(int number, int levelCount) {
        this.number = number;
        int startPassengersCount = new Random().nextInt(MAX_PASSENGERS_COUNT);
        for (int i = 0; i < startPassengersCount; i++){
            String passengerName = this.number + " " + i;
            passengers.add(new Passenger(this, levelCount, passengerName));
        }
    }

    public List<Passenger> getPassengers() {
        return passengers;
    }

    public int getNumber() {
        return number;
    }
}
