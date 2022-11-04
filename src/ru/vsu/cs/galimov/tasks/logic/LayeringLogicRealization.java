package ru.vsu.cs.galimov.tasks.logic;

import ru.vsu.cs.galimov.tasks.model.BattleFieldObject;
import ru.vsu.cs.galimov.tasks.model.movable.MoveDirections;
import ru.vsu.cs.galimov.tasks.model.movable.Position;
import ru.vsu.cs.galimov.tasks.model.movable.Tank;
import ru.vsu.cs.galimov.tasks.player.Player;

import java.util.List;

public class LayeringLogicRealization implements Layering {

    @Override
    public boolean isLayering(Tank tank, List<BattleFieldObject> list, int changeX, int changeY) {
        for (BattleFieldObject object : list) {
            if (object.intersects(new Position(tank.getPosition().x() + changeX, tank.getPosition().y() + changeY))) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void moveInViewOfLayering(List<Player> players, List<BattleFieldObject> walls, List<BattleFieldObject> indestructibleWalls, List<BattleFieldObject> lakes, List<BattleFieldObject> eagles, MoveDirections direction, int changeX, int changeY, int numberOfPlayer) {
        for (int j = 0; j < players.size(); j++) {
            if (isLayering(players.get(numberOfPlayer).getTank(), walls, changeX, changeY)
                    && isLayering(players.get(numberOfPlayer).getTank(), indestructibleWalls, changeX, changeY)
                    && isLayering(players.get(numberOfPlayer).getTank(), lakes, changeX, changeY)
                    && isLayering(players.get(numberOfPlayer).getTank(), eagles, changeX, changeY)
                    && !pairTankLayering(players.get(numberOfPlayer), players.get(j), changeX, changeY) && numberOfPlayer != j) {
                players.get(numberOfPlayer).getTank().move();
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
}
