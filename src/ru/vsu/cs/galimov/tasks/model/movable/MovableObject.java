package ru.vsu.cs.galimov.tasks.model.movable;

import ru.vsu.cs.galimov.tasks.model.BattleFieldObject;

public abstract class MovableObject extends BattleFieldObject {
    private MoveParameters mp;

    public MovableObject(Position position, MoveParameters mp) {
        super(position);
        this.mp = mp;
    }

    public MoveParameters getMp() {
        return mp;
    }
    public abstract void move();

    public void setMp(MoveParameters mp) {
        this.mp = mp;
    }


}
