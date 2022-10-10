package ru.vsu.cs.galimov.tasks.logic;

import ru.vsu.cs.galimov.tasks.model.movable.Bullet;
import ru.vsu.cs.galimov.tasks.model.movable.MoveDirections;
import ru.vsu.cs.galimov.tasks.model.movable.Tank;
import ru.vsu.cs.galimov.tasks.model.staticObject.IndestructibleWall;
import ru.vsu.cs.galimov.tasks.model.staticObject.Wall;
import ru.vsu.cs.galimov.tasks.model.staticObject.Water;
import ru.vsu.cs.galimov.tasks.player.Player;

import java.util.List;

public interface Layering {
    boolean layering(Tank tank, List<Wall> walls, List<IndestructibleWall> indestructibleWalls, List<Water> lakes, int changeX, int changeY);

    void checkLayering(List<Player> players, List<Wall> walls, List<IndestructibleWall> indestructibleWalls, List<Water> lakes, MoveDirections direction, int changeX, int changeY, int numberOfPlayer);

    boolean pairTankLayering(Player player1, Player player2, int changeX, int changeY);

    boolean checkTankIntersection(List<Bullet> bullets, int indexPlayer, Player player);
}
