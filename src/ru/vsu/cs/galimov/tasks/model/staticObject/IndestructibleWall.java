package ru.vsu.cs.galimov.tasks.model.staticObject;

import ru.vsu.cs.galimov.tasks.draw.DrawUtils;
import ru.vsu.cs.galimov.tasks.model.BattleFieldObject;
import ru.vsu.cs.galimov.tasks.model.movable.Position;

import java.awt.*;

public class IndestructibleWall extends BattleFieldObject {

    public IndestructibleWall(Position position) {
        super(position);
    }

    @Override
    public void draw(Graphics2D graphics2D) {
        DrawUtils.drawIndestructibleWall(graphics2D, this);
    }
}
