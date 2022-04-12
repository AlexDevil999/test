package com.company.entity;

import com.company.config.Moving;

import java.util.Random;

import static com.company.config.Constants.MIN_LEVEL;

public class Passenger{
    private Moving direction;
    private int level;
    private int currentLevel;
    private int maxLevel;
    private String name;

    public Passenger(Level currentLevel, int maxLevel, String name) {
        this.maxLevel = maxLevel;
        this.name = name;
        this.currentLevel = currentLevel.getNumber();
        setLevel();
    }

    public Moving getDirection() {
        return direction;
    }

    public String getName() {
        return name;
    }

    public int getLevel() {
        return level;
    }

    public void setNewCurrentLevelNumber(int number) {
        currentLevel = number;
        setLevel();
    }

    public void enterToElevator(Elevator elevator, Level currentLevel){
        elevator.addPassenger(this);
        currentLevel.getPassengers().remove(this);
    }

    public void exitFromElevator(Elevator elevator, Level currentLevel){
        setNewCurrentLevelNumber(currentLevel.getNumber());
        currentLevel.getPassengers().add(this);
        elevator.removePassenger(this);
    }

    private void setDirection(){
        if (currentLevel > level){
            direction = Moving.DOWN;
        } else {
            direction = Moving.UP;
        }
    }

    private void setLevel() {
        do{
            level = new Random().nextInt((maxLevel - MIN_LEVEL) + 1) + MIN_LEVEL;
        }
        while (level == currentLevel);
        setDirection();
    }
}
