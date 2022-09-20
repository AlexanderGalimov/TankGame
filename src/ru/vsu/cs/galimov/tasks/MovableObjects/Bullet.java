package ru.vsu.cs.galimov.tasks.MovableObjects;

import ru.vsu.cs.galimov.tasks.interfaces.DestroyableObjectOnBF;

public class Bullet extends MovableObject implements DestroyableObjectOnBF {

    public Bullet(Position position, MoveParameters mp) {
        super(position, mp);
    }

    public void move(Tank tank) {
        if (tank.getMp().getDirection() == MoveDirections.UP) {
            this.setPosition(new Position(this.getPosition().getX(), this.getPosition().getY() + this.getMp().getVelocity()));
        } else if (tank.getMp().getDirection() == MoveDirections.DOWN) {
            this.setPosition(new Position(this.getPosition().getX(), this.getPosition().getY() - this.getMp().getVelocity()));
        } else if (tank.getMp().getDirection() == MoveDirections.LEFT) {
            this.setPosition(new Position(this.getPosition().getX() - this.getMp().getVelocity(), this.getPosition().getY()));
        } else if (tank.getMp().getDirection() == MoveDirections.RIGHT) {
            this.setPosition(new Position(this.getPosition().getX() + this.getMp().getVelocity(), this.getPosition().getY() + this.getMp().getVelocity()));
        }
    }

    @Override
    public boolean destroy(Bullet bullet) {
        if(this.getPosition().getX() == bullet.getPosition().getX() || this.getPosition().getY() == bullet.getPosition().getY()){
            // убрать объект и пулю или поменять цвет
            return true;
        }
        return false;
    }
}
