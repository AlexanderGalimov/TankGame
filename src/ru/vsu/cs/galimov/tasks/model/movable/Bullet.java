package ru.vsu.cs.galimov.tasks.model.movable;

import ru.vsu.cs.galimov.tasks.draw.DrawUtils;
import ru.vsu.cs.galimov.tasks.draw.Drawable;
import ru.vsu.cs.galimov.tasks.model.Destroyable;

import java.awt.*;

public class Bullet extends MovableObject implements Destroyable, Drawable {
    private int conditionIndex = 1;

    public Bullet(Position position, MoveParameters mp) {
        super(position, mp);
    }


    public boolean destroy(Bullet bullet) {
        return this.getPosition().x() == bullet.getPosition().x() && this.getPosition().y() == bullet.getPosition().y();
    }

    @Override
    public void draw(Graphics2D graphics2D) {
        DrawUtils.drawBullet(graphics2D,getPosition(),conditionIndex);
    }

    public int getConditionIndex() {
        return conditionIndex;
    }

    public void setConditionIndex(int conditionIndex) {
        this.conditionIndex = conditionIndex;
    }
}
