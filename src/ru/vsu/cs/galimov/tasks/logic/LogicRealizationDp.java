package ru.vsu.cs.galimov.tasks.logic;

import ru.vsu.cs.galimov.tasks.model.movable.*;
import ru.vsu.cs.galimov.tasks.model.staticObject.IndestructibleWall;
import ru.vsu.cs.galimov.tasks.model.staticObject.Wall;
import ru.vsu.cs.galimov.tasks.model.staticObject.Water;
import ru.vsu.cs.galimov.tasks.player.Player;

import java.util.List;

public class LogicRealizationDp implements Moving, Layering, Destroying {

    @Override
    public void checkLayering(List<Player> players, List<Wall> walls, List<IndestructibleWall> indestructibleWalls, List<Water> lakes, MoveDirections direction, int changeX, int changeY, int numberOfPlayer) {
        for (int j = 0; j < players.size(); j++) {
            if (!layering(players.get(numberOfPlayer).getTank(), walls, indestructibleWalls, lakes, changeX, changeY) && !pairTankLayering(players.get(numberOfPlayer), players.get(j), changeX, changeY) && numberOfPlayer != j) {
                moveTank(players.get(numberOfPlayer), direction);
            }
        }
    }

    @Override
    public void checkBulletReachedObject(List<Player> players, List<Wall> walls, List<IndestructibleWall> indestructibleWalls) {
        for (Player player : players) {
            if (player.isCondition()) {
                for (int i = 0; i < player.getTank().getBullets().size(); i++) {
                    for (int j = 0; j < players.size() && player != players.get(j); j++) {
                        if (checkTankIntersection(player.getTank().getBullets(), i, players.get(j))) break;
                    }
                    checkDestroy(player, walls, indestructibleWalls);
                }
            }
        }

        for (int i = 0; i < players.size(); i++) {
            for (int j = 0; j < players.size(); j++) {
                if (i != j) {
                    checkDestroyForBullets(players.get(i), players.get(j));
                }
            }
        }
    }

    @Override
    public boolean checkTankIntersection(List<Bullet> bullets, int i, Player player) {
        if (player.isCondition() && bullets.size() != 0) {
            if (player.getTank().destroy(player.getTank().getPosition(), bullets.get(i))) {
                player.setCondition(false);
                bullets.remove(i);
                return true;
            }
        }
        return false;
    }


    @Override
    public boolean layering(Tank tank, List<Wall> walls, List<IndestructibleWall> indestructibleWalls, List<Water> lakes, int changeX, int changeY) {
        for (Wall wall : walls) {
            if (wall.intersects(new Position(tank.getPosition().x() + changeX, tank.getPosition().y() + changeY))) {
                return true;
            }
        }

        for (IndestructibleWall indestructibleWall : indestructibleWalls) {
            if (indestructibleWall.intersects(new Position(tank.getPosition().x() + changeX, tank.getPosition().y() + changeY))) {
                return true;
            }
        }

        for (Water lake : lakes) {
            if (lake.intersects(new Position(tank.getPosition().x() + changeX, tank.getPosition().y() + changeY))) {
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean pairTankLayering(Player player1, Player player2, int changeX, int changeY) {
        if (player1.isCondition() && player2.isCondition()) {
            return player2.getTank().intersects(new Position(player1.getTank().getPosition().x() + changeX, player1.getTank().getPosition().y() + changeY));
        }
        return false;
    }

    @Override
    public void checkDestroyForBullets(Player player1, Player player2) {
        for (int i = 0; i < player1.getTank().getBullets().size(); i++) {
            for (int j = 0; j < player2.getTank().getBullets().size(); j++) {
                if (player1.isCondition()) {
                    if (player1.getTank().getBullets().get(i).destroy(player2.getTank().getBullets().get(j))
                            || player1.getTank().getBullets().get(i).destroy(player1.getTank().getBullets().get(i).getPosition(), new Bullet(new Position(player2.getTank().getBullets().get(j).getPosition().x() + 50, player2.getTank().getBullets().get(j).getPosition().y()), new MoveParameters(50)))
                            || player1.getTank().getBullets().get(i).destroy(player1.getTank().getBullets().get(i).getPosition(), new Bullet(new Position(player2.getTank().getBullets().get(j).getPosition().x() - 50, player2.getTank().getBullets().get(j).getPosition().y()), new MoveParameters(50)))
                            || player1.getTank().getBullets().get(i).destroy(player1.getTank().getBullets().get(i).getPosition(), new Bullet(new Position(player2.getTank().getBullets().get(j).getPosition().x(), player2.getTank().getBullets().get(j).getPosition().y() + 50), new MoveParameters(50)))
                            || player1.getTank().getBullets().get(i).destroy(player1.getTank().getBullets().get(i).getPosition(), new Bullet(new Position(player2.getTank().getBullets().get(j).getPosition().x(), player2.getTank().getBullets().get(j).getPosition().y() - 50), new MoveParameters(50)))) {
                        player1.getTank().getBullets().remove(i);
                        player2.getTank().getBullets().remove(j);
                        break;
                    }
                }
            }
        }
    }
}
