package ru.vsu.cs.galimov.tasks.model;

import ru.vsu.cs.galimov.tasks.model.movable.Bullet;
import ru.vsu.cs.galimov.tasks.model.movable.Position;

public interface Destroyable {
    default boolean destroy(Position position, Bullet bullet){
        return position.x() == bullet.getPosition().x() && position.y() == bullet.getPosition().y();
    }
}
