package ru.vsu.cs.galimov.tasks.player;

import ru.vsu.cs.galimov.tasks.model.movable.Tank;

public class Player {
    private final Tank tank;
    private boolean condition;

    public Player(Tank tank, boolean condition) {
        this.tank = tank;
        this.condition = condition;
    }

    public Tank getTank() {
        return tank;
    }

    public boolean isCondition() {
        return condition;
    }

    public void setCondition(boolean condition) {
        this.condition = condition;
    }
}
