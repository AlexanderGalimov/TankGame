package ru.vsu.cs.galimov.tasks.logic;

import ru.vsu.cs.galimov.tasks.model.staticObject.IndestructibleWall;
import ru.vsu.cs.galimov.tasks.model.staticObject.Wall;
import ru.vsu.cs.galimov.tasks.player.Player;

import java.util.List;

public interface Destroying {
    void checkDestroy(Player player, List<Wall> walls, List<IndestructibleWall> indestructibleWalls);

    void checkBulletReachedObject(List<Player> players, List<Wall> walls, List<IndestructibleWall> indestructibleWalls);

    void checkDestroyForBullets(Player player1, Player player2);
}
