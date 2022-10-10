package ru.vsu.cs.galimov.tasks.logic;

import ru.vsu.cs.galimov.tasks.model.staticObject.IndestructibleWall;
import ru.vsu.cs.galimov.tasks.model.staticObject.Wall;
import ru.vsu.cs.galimov.tasks.player.Player;

import java.util.List;

public interface Destroying {
    default void checkDestroy(Player player, List<Wall> walls, List<IndestructibleWall> indestructibleWalls) {
        for (int i = 0; i < player.getTank().getBullets().size(); i++) {
            for (int j = 0; j < walls.size(); j++) {
                if (walls.get(j).destroy(walls.get(j).getPosition(), player.getTank().getBullets().get(i))) {
                    walls.remove(j);
                    player.getTank().getBullets().remove(i);
                    break;
                }
            }
        }

        for (int i = 0; i < player.getTank().getBullets().size(); i++) {
            for (IndestructibleWall indestructibleWall : indestructibleWalls) {
                if (player.getTank().getBullets().get(i).destroy(indestructibleWall.getPosition(), player.getTank().getBullets().get(i))) {
                    player.getTank().getBullets().remove(i);
                    break;
                }
            }
        }
    }

    void checkBulletReachedObject(List<Player> players, List<Wall> walls, List<IndestructibleWall> indestructibleWalls);

    void checkDestroyForBullets(Player player1, Player player2);
}
