package ru.vsu.cs.galimov.tasks.model.staticObject;

import ru.vsu.cs.galimov.tasks.draw.DrawUtils;
import ru.vsu.cs.galimov.tasks.model.BattleFieldObject;
import ru.vsu.cs.galimov.tasks.model.Destroyable;
import ru.vsu.cs.galimov.tasks.model.movable.Position;

import java.awt.*;

public class Eagle extends BattleFieldObject implements Destroyable {
    private boolean isAlive = true;

    public Eagle(Position position) {
        super(position);
    }

    @Override
    public void draw(Graphics2D graphics2D) {
        DrawUtils.drawEagle(graphics2D, this);
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }

}
