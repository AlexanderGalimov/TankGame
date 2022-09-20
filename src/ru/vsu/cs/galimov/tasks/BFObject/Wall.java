package ru.vsu.cs.galimov.tasks.BFObject;

import ru.vsu.cs.galimov.tasks.MovableObjects.Bullet;
import ru.vsu.cs.galimov.tasks.MovableObjects.Position;
import ru.vsu.cs.galimov.tasks.interfaces.DestroyableObjectOnBF;

public class Wall extends BattleFieldObject implements DestroyableObjectOnBF {

    public Wall(Position position) {
        super(position);
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
