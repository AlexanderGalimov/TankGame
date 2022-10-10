package ru.vsu.cs.galimov.tasks.model.staticObject;

import ru.vsu.cs.galimov.tasks.draw.DrawUtils;
import ru.vsu.cs.galimov.tasks.draw.Drawable;
import ru.vsu.cs.galimov.tasks.model.BattleFieldObject;
import ru.vsu.cs.galimov.tasks.model.movable.Position;
import ru.vsu.cs.galimov.tasks.model.Destroyable;

import java.awt.*;

public class Wall extends BattleFieldObject implements Destroyable, Drawable {

    public Wall(Position position) {
        super(position);
    }

    @Override
    public void draw(Graphics2D graphics2D) {
        DrawUtils.drawWall(graphics2D, getPosition());
    }
}
