package com.company.entity;

import com.company.config.Moving;

import java.util.ArrayList;
import java.util.List;

import static com.company.config.Constants.ELEVATOR_MAX;

public class Elevator{
    private Level currentLevel;
    private int targetLevelNumber;
    private Moving direction;
    private List<Passenger> passengers = new ArrayList<>();
    private int passengersCount;

    public Elevator(Level currentLevel){
        this.currentLevel = currentLevel;
        passengersCount = 0;
        targetLevelNumber = 2;
        direction = Moving.UP;
    }

    public int getTargetLevelNumber(){
        return this.targetLevelNumber;
    }

    public int getCurrentLevelNumber(){
        return this.currentLevel.getNumber();
    }

    public List<Passenger> getPassengers() {
        return passengers;
    }

    public int getPassengersCount() {
        return passengersCount;
    }

    public int getVacancies(){
        return ELEVATOR_MAX - getPassengersCount();
    }

    public Moving getDirection() {
        return direction;
    }

    public void addPassenger(Passenger passenger){
        passengers.add(passenger);
        passengersCount++;
        setTargetLevel();
    }

    public void removePassenger(Passenger passenger){
        passengers.remove(passenger);
        passengersCount--;
        setTargetLevel();
    }

    public void moveTo(Level nextLevel){
        currentLevel = nextLevel;
        changePassengers(currentLevel);
    }

    private void setTargetLevel(){
        for (int i = 0; i < passengers.size(); i++){
            if (targetLevelNumber < passengers.get(i).getLevel()){
                targetLevelNumber = passengers.get(i).getLevel();
            }
        }
        if (targetLevelNumber > currentLevel.getNumber()){
            direction = Moving.UP;
        } else {
            direction = Moving.DOWN;
        }
    }

    private void changePassengers(Level currentLevel){
        if (getTargetLevelNumber() == getCurrentLevelNumber()){
            setNewDirection();
        }
        if (!passengers.isEmpty()) {
            exitPassengersFromElevator();
        }
        if (!currentLevel.getPassengers().isEmpty()) {
            enterNewPassengersToElevator();
        }
    }

    private void enterNewPassengersToElevator(){
        int passengersCounter = currentLevel.getPassengers().size();
        for (int i = 0; i < currentLevel.getPassengers().size() && getVacancies() > 0;) {
            if (getDirection() == currentLevel.getPassengers().get(i).getDirection()) {
                currentLevel.getPassengers().get(i).enterToElevator(this, currentLevel);
            }
            if (passengersCounter == currentLevel.getPassengers().size()){
                i++;
            } else {
                passengersCounter = currentLevel.getPassengers().size();
            }
        }
    }

    private void exitPassengersFromElevator(){
        int passengersCounter = passengers.size();
        for (int i = 0; i < passengers.size();) {
            if (passengers.get(i).getLevel() == currentLevel.getNumber()) {
                passengers.get(i).exitFromElevator(this, currentLevel);
            }
            if (passengersCounter == passengers.size()){
                i++;
            } else {
                passengersCounter = passengers.size();
            }
        }
    }

    private void setNewDirection(){
        int up = 0;
        int down = 0;
        for (int i = 0; i < currentLevel.getPassengers().size(); i++){
            if (currentLevel.getPassengers().get(i).getDirection() == Moving.UP){
                up++;
            } else {
                down++;
            }
        }
        if (down >= up){
            direction = Moving.DOWN;
        } else {
            direction = Moving.UP;
        }
    }
}
