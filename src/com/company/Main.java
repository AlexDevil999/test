package com.company;

import com.company.config.Moving;
import com.company.service.BuildingService;

public class Main {

    public static final int CURRENT_LEVEL = 0;

    public static void main(String[] args) {
        BuildingService building = new BuildingService();
        int levelPassengersCounter = building.getLevel(CURRENT_LEVEL).getPassengers().size();
        for (int i = 0; i < building.getLevel(CURRENT_LEVEL).getPassengers().size() && building.getElevator().getVacancies() > 0;){
            building.getLevel(CURRENT_LEVEL).getPassengers().get(i).enterToElevator(building.getElevator(), building.getLevel(CURRENT_LEVEL));
            if (levelPassengersCounter == building.getLevel(CURRENT_LEVEL).getPassengers().size()){
                i++;
            } else {
                levelPassengersCounter = building.getLevel(CURRENT_LEVEL).getPassengers().size();
            }
        }
        execute(building);
        int i = CURRENT_LEVEL;
        while (true){
            if (building.getElevator().getDirection() == Moving.UP){
                i++;
            } else {
                i--;
            }
            building.getElevator().moveTo(building.getLevel(i));
            execute(building);
        }
    }

    private static void execute(BuildingService building){
        System.out.println(String.format("Лифт на %d этаже", building.getElevator().getCurrentLevelNumber()));
        System.out.println(String.format("Пассажир на %d", building.getElevator().getPassengersCount()));
        for (int i = 0; i < building.getElevator().getPassengersCount(); i++){
            System.out.print(String.format("%s, ", building.getElevator().getPassengers().get(i).getName()));
        }
        System.out.println();
        for (int i = 0; i < building.getLevelsCount(); i++){
            System.out.println(String.format("Этаж %d ", i + 1));
            System.out.println(String.format("Пассажир %d: ", building.getLevel(i).getPassengers().size()));
            for (int j = 0; j < building.getLevel(i).getPassengers().size(); j++){
                System.out.print(String.format("%s, ", building.getLevel(i).getPassengers().get(j).getName()));
            }
            System.out.println();
        }

    }


}
