package ru.vsu.cs.galimov.tasks.logic;

import ru.vsu.cs.galimov.tasks.model.movable.MoveDirections;

public class Turn {
    private boolean isTurned;
    private MoveDirections direction;

    public Turn(boolean isTurned, MoveDirections direction) {
        this.isTurned = isTurned;
        this.direction = direction;
    }

    public boolean isTurned() {
        return isTurned;
    }

    public void setTurned(boolean turned) {
        isTurned = turned;
    }

    public MoveDirections getDirection() {
        return direction;
    }

    public void setDirection(MoveDirections direction) {
        this.direction = direction;
    }
}
