package ru.vsu.cs.galimov.tasks.logic;

import ru.vsu.cs.galimov.tasks.model.BattleFieldObject;
import ru.vsu.cs.galimov.tasks.model.movable.*;
import ru.vsu.cs.galimov.tasks.model.staticObject.Eagle;
import ru.vsu.cs.galimov.tasks.model.staticObject.IndestructibleWall;
import ru.vsu.cs.galimov.tasks.player.Player;

import java.util.List;

public class LogicRealization implements Destroying, Layering, Moving {

    @Override
    public void checkDestroy(Player player, List<BattleFieldObject> list) {
        for (int i = 0; i < player.getTank().getBullets().size(); i++) {
            for (BattleFieldObject object : list) {
                if (object.intersects(player.getTank().getBullets().get(i).getPosition())) {
                    if(!(object instanceof IndestructibleWall)){
                        if (object instanceof Eagle) {
                            ((Eagle) object).setAlive(false);
                        }
                        else {
                            list.remove(object);
                        }
                    }
                    player.getTank().getBullets().remove(i);
                    break;
                }
            }
        }
    }

    @Override
    public void checkBulletReachedObject(List<Player> players, List<BattleFieldObject> walls, List<BattleFieldObject> indestructibleWalls, List<BattleFieldObject> eagles, int velocity) {
        for (Player player : players) {
            if (player.isCondition()) {
                for (int i = 0; i < player.getTank().getBullets().size(); i++) {
                    for (Player value : players) {
                        if (checkTankIntersection(player.getTank().getBullets(), i, value)) break;
                    }
                }
                checkDestroy(player, walls);
                checkDestroy(player, indestructibleWalls);
                checkDestroy(player, eagles);
            }
        }

        for (int i = 0; i < players.size(); i++) {
            for (int j = 0; j < players.size(); j++) {
                if (i != j) {
                    checkDestroyForBullets(players.get(i), players.get(j), velocity);
                }
            }
        }
    }

    @Override
    public void checkDestroyForBullets(Player player1, Player player2, int velocity) {
        for (int i = 0; i < player1.getTank().getBullets().size(); i++) {
            for (int j = 0; j < player2.getTank().getBullets().size(); j++) {
                if (player1.isCondition()) {
                    if (player1.getTank().getBullets().get(i).destroy(player2.getTank().getBullets().get(j))
                            || (player1.getTank().getBullets().get(i).destroy(player1.getTank().getBullets().get(i).getPosition(), new Bullet(new Position(player2.getTank().getBullets().get(j).getPosition().x() + player2.getTank().getBullets().get(j).getMp().getVelocity(), player2.getTank().getBullets().get(j).getPosition().y()), new MoveParameters(velocity)))
                            || player1.getTank().getBullets().get(i).destroy(player1.getTank().getBullets().get(i).getPosition(), new Bullet(new Position(player2.getTank().getBullets().get(j).getPosition().x() - player2.getTank().getBullets().get(j).getMp().getVelocity(), player2.getTank().getBullets().get(j).getPosition().y()), new MoveParameters(velocity)))
                            && player1.getTank().getBullets().get(i).getPosition().y() == player2.getTank().getBullets().get(j).getPosition().y())
                            || (player1.getTank().getBullets().get(i).destroy(player1.getTank().getBullets().get(i).getPosition(), new Bullet(new Position(player2.getTank().getBullets().get(j).getPosition().x(), player2.getTank().getBullets().get(j).getPosition().y() + player2.getTank().getBullets().get(j).getMp().getVelocity()), new MoveParameters(velocity)))
                            || player1.getTank().getBullets().get(i).destroy(player1.getTank().getBullets().get(i).getPosition(), new Bullet(new Position(player2.getTank().getBullets().get(j).getPosition().x(), player2.getTank().getBullets().get(j).getPosition().y() - player2.getTank().getBullets().get(j).getMp().getVelocity()), new MoveParameters(velocity)))
                            && player1.getTank().getBullets().get(i).getPosition().x() == player2.getTank().getBullets().get(j).getPosition().x())) {
                        player1.getTank().getBullets().remove(i);
                        player2.getTank().getBullets().remove(j);
                        break;
                    }
                }
            }
        }
    }

    public boolean layering(Tank tank, List<BattleFieldObject> list, int changeX, int changeY) {
        for (BattleFieldObject object : list) {
            if (object.intersects(new Position(tank.getPosition().x() + changeX, tank.getPosition().y() + changeY))) {
                return true;
            }
        }
        return false;
    }


    @Override
    public void checkLayering(List<Player> players, List<BattleFieldObject> walls, List<BattleFieldObject> indestructibleWalls, List<BattleFieldObject> lakes, List<BattleFieldObject> eagles, MoveDirections direction, int changeX, int changeY, int numberOfPlayer) {
        for (int j = 0; j < players.size(); j++) {
            if (!layering(players.get(numberOfPlayer).getTank(), walls, changeX, changeY)
                    && !layering(players.get(numberOfPlayer).getTank(), indestructibleWalls, changeX, changeY)
                    && !layering(players.get(numberOfPlayer).getTank(), lakes, changeX, changeY)
                    && !layering(players.get(numberOfPlayer).getTank(), eagles, changeX, changeY)
                    && !pairTankLayering(players.get(numberOfPlayer), players.get(j), changeX, changeY) && numberOfPlayer != j) {
                moveTank(players.get(numberOfPlayer), direction);
            }
        }
    }

    @Override
    public boolean pairTankLayering(Player player1, Player player2, int changeX, int changeY) {
        if (player1.isCondition() && player2.isCondition()) {
            return player2.getTank().intersects(new Position(player1.getTank().getPosition().x() + changeX, player1.getTank().getPosition().y() + changeY));
        }
        return false;
    }

    @Override
    public boolean checkTankIntersection(List<Bullet> bullets, int indexPlayer, Player player) {
        if (player.isCondition()) {
            if (player.getTank().destroy(player.getTank().getPosition(), bullets.get(indexPlayer))) {
                player.setCondition(false);
                bullets.remove(indexPlayer);
                return true;
            }
        }
        return false;
    }

    @Override
    public void moveTank(Player player, MoveDirections direction) {
        player.getTank().move();
    }

    @Override
    public void turnTank(Player player, MoveDirections direction) {
        player.getTank().getMp().setDirection(direction);
    }

}
