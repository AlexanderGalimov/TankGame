package ru.vsu.cs.galimov.tasks.model.staticObject;

import ru.vsu.cs.galimov.tasks.draw.DrawUtils;
import ru.vsu.cs.galimov.tasks.model.BattleFieldObject;
import ru.vsu.cs.galimov.tasks.model.movable.Position;

import java.awt.*;

public class Thickets extends BattleFieldObject {
    public Thickets(Position position) {
        super(position);
    }

    @Override
    public void draw(Graphics2D graphics2D) {
        DrawUtils.drawThickets(graphics2D, this);
    }
}
