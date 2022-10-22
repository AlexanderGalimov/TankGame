package ru.vsu.cs.galimov.tasks.model;

import ru.vsu.cs.galimov.tasks.draw.Drawable;
import ru.vsu.cs.galimov.tasks.model.movable.Position;

import java.awt.*;

public abstract class BattleFieldObject implements Drawable {
    protected Position position;

    public BattleFieldObject(Position position) {
        this.position = position;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public boolean intersects(Position p) {
        return this.position.x() == p.x() && this.position.y() == p.y();
    }

    public abstract void draw(Graphics2D graphics2D);
}
