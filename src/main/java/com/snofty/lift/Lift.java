package com.snofty.lift;

import com.snofty.model.People;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class Lift {
    private static final int MAX_LOAD = 50;
    Logger logger = Logger.getLogger(Lift.class.getSimpleName());
    private List<Floor> floors;
    private List<People> peoples;
    private int currentFloor;
    private boolean up;

    public Lift() {
        peoples = new ArrayList<>();
        currentFloor = 0;
    }

    public void nextFloor() {
        if (up) {
            info("Going to next floor from {0}", currentFloor);
            ++currentFloor;
        } else {
            info("Going to previous floor {0}", currentFloor);
            --currentFloor;
        }
    }

    public void start(boolean up) {
        this.up = up;
        info("Lift started running up: {0}", up);
        pickUpPeople();
        runMe();
        info("Lift completed its job");
    }

    private void runMe() {
        while (!peoples.isEmpty()) {
            nextFloor();
            int targetFloor = peoples.get(0).getTargetFloor();
            if (currentFloor == targetFloor) {
                doorOpen();
                peoples.removeIf(people -> people.getTargetFloor() == targetFloor);
                pickUpPeople();
            }

        }
    }

    private void doorOpen() {
        try {
            info("Opening door of lift with {0} people at {1} floor", peoples.size(), currentFloor);
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            logger.log(Level.SEVERE, e.getMessage());
            Thread.currentThread().interrupt();
        }
    }

    public void setFloors(List<Floor> floors) {
        this.floors = floors;
    }

    private void pickUpPeople() {
        Floor floor = floors.get(currentFloor);
        if (peoples.size() < MAX_LOAD) {
            List<People> allowedPeoples = floor.getPeoples().stream().limit((long)MAX_LOAD - peoples.size()).collect(Collectors.toList());
            peoples.addAll(allowedPeoples);
            peoples.sort(Comparator.comparingInt(People::getTargetFloor));
        }
        info("Lift contains: {0} people(s)", peoples.size());
    }

    private void info(String msg, Object... vars) {
        logger.log(Level.INFO, msg, vars);
    }
}
