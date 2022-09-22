package ru.vsu.cs.galimov.tasks.model.staticObject;

import ru.vsu.cs.galimov.tasks.model.BattleFieldObject;
import ru.vsu.cs.galimov.tasks.model.movable.Bullet;
import ru.vsu.cs.galimov.tasks.model.movable.Position;
import ru.vsu.cs.galimov.tasks.model.Destroyable;

public class Wall extends BattleFieldObject implements Destroyable {

    public Wall(Position position) {
        super(position);
    }

    @Override
    public boolean destroy(Bullet bullet) {
        if(this.getPosition().x() == bullet.getPosition().x() || this.getPosition().y() == bullet.getPosition().y()){
            System.out.println("wall has been destroyed");
            return true;
        }
        return false;
    }
}
