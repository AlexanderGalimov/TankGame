package ru.vsu.cs.galimov.tasks.logic;

import ru.vsu.cs.galimov.tasks.model.BattleFieldObject;
import ru.vsu.cs.galimov.tasks.model.movable.Bullet;
import ru.vsu.cs.galimov.tasks.model.movable.MoveDirections;
import ru.vsu.cs.galimov.tasks.model.movable.Tank;
import ru.vsu.cs.galimov.tasks.player.Player;

import java.util.List;

public interface Layering {
    boolean layering(Tank tank, List<BattleFieldObject> objects, int changeX, int changeY);

    void checkLayering(List<Player> players, List<BattleFieldObject> walls, List<BattleFieldObject> indestructibleWalls, List<BattleFieldObject> lakes, List<BattleFieldObject> eagles, MoveDirections direction, int changeX, int changeY, int numberOfPlayer);

    boolean pairTankLayering(Player player1, Player player2, int changeX, int changeY);

}
