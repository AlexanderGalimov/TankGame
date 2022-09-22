package ru.vsu.cs.galimov.tasks.model.movable;

import ru.vsu.cs.galimov.tasks.model.Destroyable;

public class Tank extends MovableObject implements Destroyable {

    public Tank(Position position, MoveParameters mp) {
        super(position, mp);
    }

    public void move(){
        if(this.getMp().getDirection() == MoveDirections.UP){
            this.setPosition(new Position(this.getPosition().x(), this.getPosition().y() + this.getMp().getVelocity()));
        }
        else if(this.getMp().getDirection() == MoveDirections.DOWN){
            this.setPosition(new Position(this.getPosition().x(), this.getPosition().x() - this.getMp().getVelocity()));
        }
        else if(this.getMp().getDirection() == MoveDirections.LEFT){
            this.setPosition(new Position(this.getPosition().x() - this.getMp().getVelocity(), this.getPosition().y()));
        }
        else if(this.getMp().getDirection() == MoveDirections.RIGHT){
            this.setPosition(new Position(this.getPosition().x() + this.getMp().getVelocity(), this.getPosition().y() + this.getMp().getVelocity()));
        }
    }

    @Override
    public boolean destroy(Bullet bullet) {
        if(this.getPosition().x() == bullet.getPosition().x() || this.getPosition().y() == bullet.getPosition().y()){
            System.out.println("Tank has been destroyed");
            return true;
        }
        return false;
    }
}
