package ru.vsu.cs.galimov.tasks.logic;

import ru.vsu.cs.galimov.tasks.model.movable.*;
import ru.vsu.cs.galimov.tasks.model.staticObject.IndestructibleWall;
import ru.vsu.cs.galimov.tasks.model.staticObject.Wall;
import ru.vsu.cs.galimov.tasks.model.staticObject.Water;
import ru.vsu.cs.galimov.tasks.player.Player;

import java.util.List;

public class LogicRealizationMainBF implements Moving, Layering, Destroying {

    @Override
    public void checkLayering(List<Player> players, List<Wall> walls, List<IndestructibleWall> indestructibleWalls, List<Water> lakes, MoveDirections direction, int changeX, int changeY, int numberOfPlayer) {
        if (layering(players.get(numberOfPlayer).getTank(), walls, indestructibleWalls, lakes, changeX, changeY)) {
            moveTank(players.get(numberOfPlayer), direction);
        }
    }

    @Override
    public void checkBulletReachedObject(List<Player> players, List<Wall> walls, List<IndestructibleWall> indestructibleWalls) {
        checkDestroy(players.get(0), walls, indestructibleWalls);
    }

    @Override
    public boolean layering(Tank tank, List<Wall> walls, List<IndestructibleWall> indestructibleWalls, List<Water> lakes, int changeX, int changeY) {
        for (IndestructibleWall indestructibleWall : indestructibleWalls) {
            if (indestructibleWall.intersects(new Position(tank.getPosition().x() + changeX, tank.getPosition().y() + changeY))) {
                return false;
            }
        }

        for (Wall wall : walls) {
            if (wall.intersects(new Position(tank.getPosition().x() + changeX, tank.getPosition().y() + changeY))) {
                return false;
            }
        }

        for (Water lake : lakes) {
            if (lake.intersects(new Position(tank.getPosition().x() + changeX, tank.getPosition().y() + changeY))) {
                return false;
            }
        }

        return true;
    }

    @Override
    public boolean pairTankLayering(Player player1, Player player2, int changeX, int changeY) {
        System.out.println("NOT SUPPORTED YET");
        return false;
    }

    @Override
    public boolean checkTankIntersection(List<Bullet> bullets, int indexPlayer, Player player) {
        System.out.println("NOT SUPPORTED YET");
        return false;
    }

    @Override
    public void checkDestroyForBullets(Player player1, Player player2) {
        System.out.println("NOT SUPPORTED YET");
    }
}
