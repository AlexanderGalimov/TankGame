package ru.vsu.cs.galimov.tasks.logic;

import ru.vsu.cs.galimov.tasks.model.BattleFieldObject;
import ru.vsu.cs.galimov.tasks.model.movable.Bullet;
import ru.vsu.cs.galimov.tasks.player.Player;

import java.util.List;

public interface Destroying {
    void destroySeparatedObjects(Player player, List<BattleFieldObject> objects);

    void destroyObjectsByBullet(List<Player> players, List<BattleFieldObject> walls, List<BattleFieldObject> indestructibleWalls, List<BattleFieldObject> eagles, int velocity);

    void destroyBulletsByCollision(Player player1, Player player2, int velocity);

    boolean checkTankIntersectsBullet(List<Bullet> bullets, int indexPlayer, Player player);

}
