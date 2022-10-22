package ru.vsu.cs.galimov.tasks.model.movable;

import ru.vsu.cs.galimov.tasks.draw.DrawUtils;

import java.awt.*;

public class Bullet extends MovableObject{

    public Bullet(Position position, MoveParameters mp) {
        super(position, mp);
    }

    @Override
    public void draw(Graphics2D graphics2D) {
        DrawUtils.drawBullet(graphics2D, this);
    }

}
