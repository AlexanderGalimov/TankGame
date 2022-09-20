package ru.vsu.cs.galimov.tasks.MovableObjects;

public abstract class MovableObject{
    private Position position;
    private MoveParameters mp;

    public MovableObject(Position position, MoveParameters mp) {
        this.position = position;
        this.mp = mp;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position){
        this.position = position;
    }

    public MoveParameters getMp() {
        return mp;
    }

    public void setMp(MoveParameters mp) {
        this.mp = mp;
    }


}
