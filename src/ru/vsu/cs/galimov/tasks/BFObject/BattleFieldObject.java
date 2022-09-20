package ru.vsu.cs.galimov.tasks.BFObject;

import ru.vsu.cs.galimov.tasks.MovableObjects.Position;

abstract class BattleFieldObject{
    private Position position;

    public BattleFieldObject(Position position) {
        this.position = position;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }
}
