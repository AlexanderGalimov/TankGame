package ru.vsu.cs.galimov.tasks.interfaces;

import ru.vsu.cs.galimov.tasks.MovableObjects.Bullet;

public interface DestroyableObjectOnBF {
    boolean destroy(Bullet bullet);
}
