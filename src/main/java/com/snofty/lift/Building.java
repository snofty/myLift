package com.snofty.lift;

import java.util.List;

public class Building {
    private List<Floor> floors;
    private Lift lift;

    public List<Floor> getFloors() {
        return floors;
    }

    public void setFloors(List<Floor> floors) {
        this.floors = floors;
    }

    public Lift getLift() {
        return lift;
    }

    public void setLift(Lift lift) {
        this.lift = lift;
    }
}
