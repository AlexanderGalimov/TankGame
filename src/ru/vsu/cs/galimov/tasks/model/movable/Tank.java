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
        DrawUtils.drawTank(graphics2D, getPosition(), getMp().getDirection());
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

    public void shoot(){
        isFire = true;
        if (this.getMp().getDirection() == MoveDirections.LEFT) {
            this.getBullets().add(new Bullet(new Position(this.getPosition().x() - this.getMp().getVelocity(), this.getPosition().y()), new MoveParameters(this.getMp().getVelocity())));
        } else if (this.getMp().getDirection() == MoveDirections.RIGHT) {
            this.getBullets().add(new Bullet(new Position(this.getPosition().x() + this.getMp().getVelocity(), this.getPosition().y()), new MoveParameters(this.getMp().getVelocity())));
        } else if (this.getMp().getDirection() == MoveDirections.UP) {
            this.getBullets().add(new Bullet(new Position(this.getPosition().x(), this.getPosition().y() - this.getMp().getVelocity()), new MoveParameters(this.getMp().getVelocity())));
        } else if (this.getMp().getDirection() == MoveDirections.DOWN) {
            this.getBullets().add(new Bullet(new Position(this.getPosition().x(), this.getPosition().y() + this.getMp().getVelocity()), new MoveParameters(this.getMp().getVelocity())));
        }
        this.getBullets().get(this.getBullets().size() - 1).getMp().setDirection(this.getMp().getDirection());
    }
}
