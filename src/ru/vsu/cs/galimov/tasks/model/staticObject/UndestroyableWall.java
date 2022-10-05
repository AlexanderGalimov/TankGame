package ru.vsu.cs.galimov.tasks.model.staticObject;

import ru.vsu.cs.galimov.tasks.draw.DrawUtils;
import ru.vsu.cs.galimov.tasks.draw.Drawable;
import ru.vsu.cs.galimov.tasks.model.BattleFieldObject;
import ru.vsu.cs.galimov.tasks.model.movable.Position;

import java.awt.*;

public class UndestroyableWall extends BattleFieldObject implements Drawable {

    public UndestroyableWall(Position position) {
        super(position);
    }

    @Override
    public void draw(Graphics2D graphics2D) {
        DrawUtils.drawUndestroyableWall(graphics2D,getPosition());
    }
}
