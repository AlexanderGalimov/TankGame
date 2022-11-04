package ru.vsu.cs.galimov.tasks.logic;

import ru.vsu.cs.galimov.tasks.model.BattleFieldObject;
import ru.vsu.cs.galimov.tasks.model.movable.Bullet;
import ru.vsu.cs.galimov.tasks.model.movable.Position;
import ru.vsu.cs.galimov.tasks.model.staticObject.Eagle;
import ru.vsu.cs.galimov.tasks.model.staticObject.IndestructibleWall;
import ru.vsu.cs.galimov.tasks.player.Player;

import java.util.List;

public class DestroyingLogicRealization implements Destroying {

    @Override
    public void destroySeparatedObjects(Player player, List<BattleFieldObject> objects) {
        for (int i = 0; i < player.getTank().getBullets().size(); i++) {
            for (BattleFieldObject object : objects) {
                if (object.intersects(player.getTank().getBullets().get(i).getPosition())) {
                    if (!(object instanceof IndestructibleWall)) {
                        if (object instanceof Eagle) {
                            ((Eagle) object).setAlive(false);
                        } else {
                            objects.remove(object);
                        }
                    }
                    player.getTank().getBullets().remove(i);
                    break;
                }
            }
        }
    }

    @Override
    public void destroyObjectsByBullet(List<Player> players, List<BattleFieldObject> walls, List<BattleFieldObject> indestructibleWalls, List<BattleFieldObject> eagles, int velocity) {
        for (Player player : players) {
            if (player.isCondition()) {
                for (int i = 0; i < player.getTank().getBullets().size(); i++) {
                    for (Player value : players) {
                        if (!player.equals(value) && checkTankIntersectsBullet(player.getTank().getBullets(), i, value)){
                            break;
                        }
                    }
                }
                destroySeparatedObjects(player, walls);
                destroySeparatedObjects(player, indestructibleWalls);
                destroySeparatedObjects(player, eagles);
            }
        }

        for (int i = 0; i < players.size(); i++) {
            for (int j = 0; j < players.size(); j++) {
                if (i != j) {
                    destroyBulletsByCollision(players.get(i), players.get(j), velocity);
                }
            }
        }
    }

    @Override
    public void destroyBulletsByCollision(Player player1, Player player2, int velocity) {
        for (int i = 0; i < player1.getTank().getBullets().size(); i++) {
            for (int j = 0; j < player2.getTank().getBullets().size(); j++) {
                if (player1.isCondition()) {
                    if (player1.getTank().getBullets().get(i).intersects(player2.getTank().getBullets().get(j).getPosition())
                            || ((((player1.getTank().getBullets().get(i).intersects(new Position(player2.getTank().getBullets().get(j).getPosition().x() + player2.getTank().getBullets().get(j).getMp().getVelocity(), player2.getTank().getBullets().get(j).getPosition().y()))
                            && player1.getTank().getBullets().get(i).intersects(new Position(player2.getTank().getBullets().get(j).getPosition().x() - player2.getTank().getBullets().get(j).getMp().getVelocity(), player2.getTank().getBullets().get(j).getPosition().y())))
                            && player1.getTank().getBullets().get(i).getPosition().y() == player2.getTank().getBullets().get(j).getPosition().y())
                            || ((player1.getTank().getBullets().get(i).intersects(new Position(player2.getTank().getBullets().get(j).getPosition().x(), player2.getTank().getBullets().get(j).getPosition().y() + player2.getTank().getBullets().get(j).getMp().getVelocity()))
                            && player1.getTank().getBullets().get(i).intersects(new Position(player2.getTank().getBullets().get(j).getPosition().x(), player2.getTank().getBullets().get(j).getPosition().y() - player2.getTank().getBullets().get(j).getMp().getVelocity())))
                            && player1.getTank().getBullets().get(i).getPosition().x() == player2.getTank().getBullets().get(j).getPosition().x())))) {
                        player1.getTank().getBullets().remove(i);
                        player2.getTank().getBullets().remove(j);
                        break;
                    }
                }
            }
        }
    }

    @Override
    public boolean checkTankIntersectsBullet(List<Bullet> bullets, int indexPlayer, Player player) {
        if (player.isCondition()) {
            if (player.getTank().intersects(bullets.get(indexPlayer).getPosition())) {
                player.setCondition(false);
                bullets.remove(indexPlayer);
                return true;
            }
        }
        return false;
    }
}
