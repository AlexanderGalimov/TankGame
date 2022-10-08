package ru.vsu.cs.galimov.tasks.model.movable;

import ru.vsu.cs.galimov.tasks.draw.DrawUtils;
import ru.vsu.cs.galimov.tasks.draw.Drawable;
import ru.vsu.cs.galimov.tasks.model.Destroyable;

import java.awt.*;

public class Tank extends MovableObject implements Destroyable, Drawable {

    public Tank(Position position, MoveParameters mp) {
        super(position, mp);
    }

    @Override
    public void draw(Graphics2D graphics2D) {
        DrawUtils.drawTank(graphics2D,getPosition(), getMp().getDirection());
    }
}
