package com.company.service;

import com.company.entity.Elevator;
import com.company.entity.Level;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.company.config.Constants.*;

public class BuildingService {
    private List<Level> levels = new ArrayList<>();
    private Elevator elevator;


    public BuildingService() {
        int levelCount = new Random().nextInt((MAX_FLOOR - MIN_FLOOR) + 1) + MIN_FLOOR;
        for (int i = 0; i < levelCount; i++){
            levels.add(new Level(i + 1, levelCount));
        }
        elevator = new Elevator(levels.get(ELEVATOR_STARTED));
    }

    public Elevator getElevator() {
        return elevator;
    }

    public Level getLevel(int index){
        return levels.get(index);
    }

    public int getLevelsCount(){
        return levels.size();
    }
}
