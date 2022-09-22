package ru.vsu.cs.galimov.tasks.model.movable;

import ru.vsu.cs.galimov.tasks.model.Destroyable;

import java.util.Timer;

public class Bullet extends MovableObject implements Destroyable {
    private Timer timer = new Timer();

    public Bullet(Position position, MoveParameters mp) {
        super(position, mp);
    }

    public void move() {
        if (this.getMp().getDirection() == MoveDirections.UP) {
           /* this.setPosition(new Position(this.getPosition().x(), this.getPosition().y() + this.getMp().getVelocity()));*/
        } else if (this.getMp().getDirection() == MoveDirections.DOWN) {
            /*this.setPosition(new Position(this.getPosition().x(), this.getPosition().y() - this.getMp().getVelocity()));*/
        } else if (this.getMp().getDirection() == MoveDirections.LEFT) {
           /* this.setPosition(new Position(this.getPosition().x() - this.getMp().getVelocity(), this.getPosition().y()));*/
        } else if (this.getMp().getDirection() == MoveDirections.RIGHT) {
           /* this.setPosition(new Position(this.getPosition().x() + this.getMp().getVelocity(), this.getPosition().y() + this.getMp().getVelocity()));*/
        }
    }

    @Override
    public boolean destroy(Bullet bullet) {
        if(this.getPosition().x() == bullet.getPosition().x() || this.getPosition().y() == bullet.getPosition().y()){
            System.out.println("bullet destroyed");
            return true;
        }
        return false;
    }
}
