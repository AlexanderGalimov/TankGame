package ru.vsu.cs.galimov.tasks.model.movable;

public class MoveParameters {
    private MoveDirections direction = MoveDirections.NONE;
    private final int velocity;

    public MoveParameters(int velocity) {
        this.velocity = velocity;
    }

    public MoveDirections getDirection() {
        return direction;
    }

    public void setDirection(MoveDirections direction) {
        this.direction = direction;
    }

    public int getVelocity() {
        return velocity;
    }

}
