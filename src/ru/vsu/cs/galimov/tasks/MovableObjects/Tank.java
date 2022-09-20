package ru.vsu.cs.galimov.tasks.MovableObjects;

import ru.vsu.cs.galimov.tasks.interfaces.DestroyableObjectOnBF;

public class Tank extends MovableObject implements DestroyableObjectOnBF {

    public Tank(Position position, MoveParameters mp) {
        super(position, mp);
    }

    public void move(){
        if(this.getMp().getDirection() == MoveDirections.UP){
            this.setPosition(new Position(this.getPosition().getX(), this.getPosition().getY() + this.getMp().getVelocity()));
        }
        else if(this.getMp().getDirection() == MoveDirections.DOWN){
            this.setPosition(new Position(this.getPosition().getX(), this.getPosition().getY() - this.getMp().getVelocity()));
        }
        else if(this.getMp().getDirection() == MoveDirections.LEFT){
            this.setPosition(new Position(this.getPosition().getX() - this.getMp().getVelocity(), this.getPosition().getY()));
        }
        else if(this.getMp().getDirection() == MoveDirections.RIGHT){
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
