package ru.vsu.cs.galimov.tasks.model;

import ru.vsu.cs.galimov.tasks.model.movable.Bullet;

public interface Destroyable {
    boolean destroy(Bullet bullet);
}
