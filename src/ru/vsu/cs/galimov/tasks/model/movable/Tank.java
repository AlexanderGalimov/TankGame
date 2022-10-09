package ru.vsu.cs.galimov.tasks.model.movable;

import ru.vsu.cs.galimov.tasks.draw.DrawUtils;
import ru.vsu.cs.galimov.tasks.draw.Drawable;
import ru.vsu.cs.galimov.tasks.model.Destroyable;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Tank extends MovableObject implements Destroyable, Drawable {
    private boolean isFire = false;
    private List<Bullet> bullets = new ArrayList<>();

    public Tank(Position position, MoveParameters mp) {
        super(position, mp);
    }

    @Override
    public void draw(Graphics2D graphics2D) {
        DrawUtils.drawTank(graphics2D,getPosition(), getMp().getDirection());
    }

    public boolean isFire() {
        return isFire;
    }

    public void setFire(boolean fire) {
        isFire = fire;
    }

    public List<Bullet> getBullets() {
        return bullets;
    }

    public void setBullets(List<Bullet> bullets) {
        this.bullets = bullets;
    }
}
