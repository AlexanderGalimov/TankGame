package ru.vsu.cs.galimov.tasks.logic;

import ru.vsu.cs.galimov.tasks.model.BattleFieldObject;
import ru.vsu.cs.galimov.tasks.player.Player;

import java.util.List;

public interface Destroying {
    void checkDestroy(Player player, List<BattleFieldObject> objects);

    void checkBulletReachedObject(List<Player> players, List<BattleFieldObject> walls, List<BattleFieldObject> indestructibleWalls, List<BattleFieldObject> eagles, int velocity);

    void checkDestroyForBullets(Player player1, Player player2, int velocity);
}
