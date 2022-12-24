package ru.vsu.cs.galimov.tasks.field;

import ru.vsu.cs.galimov.tasks.clientServer.BattleMapPanel;
import ru.vsu.cs.galimov.tasks.logic.Game;
import ru.vsu.cs.galimov.tasks.logic.Turn;
import ru.vsu.cs.galimov.tasks.model.BattleFieldObject;
import ru.vsu.cs.galimov.tasks.model.movable.MoveDirections;
import ru.vsu.cs.galimov.tasks.model.movable.MoveParameters;
import ru.vsu.cs.galimov.tasks.model.movable.Position;
import ru.vsu.cs.galimov.tasks.model.movable.Tank;
import ru.vsu.cs.galimov.tasks.model.staticObject.IndestructibleWall;
import ru.vsu.cs.galimov.tasks.model.staticObject.Wall;
import ru.vsu.cs.galimov.tasks.player.Player;

import java.util.ArrayList;

public class BattleMapConsole {
    public static final char[][] field = new char[11][11];

    public static void initializeGame(Game game) {
        IndestructibleWall indestructibleWall;
        for (int i = 0; i < field[0].length; i++) {
            indestructibleWall = new IndestructibleWall(new Position(0, i));
            game.getIndestructibleWalls().add(indestructibleWall);
        }

        for (int i = 0; i < field[0].length; i++) {
            indestructibleWall = new IndestructibleWall(new Position(field.length - 1, i));
            game.getIndestructibleWalls().add(indestructibleWall);
        }

        for (int i = 0; i < field.length; i++) {
            indestructibleWall = new IndestructibleWall(new Position(i, 0));
            game.getIndestructibleWalls().add(indestructibleWall);
        }

        for (int i = 0; i < field.length; i++) {
            indestructibleWall = new IndestructibleWall(new Position(i, field[0].length - 1));
            game.getIndestructibleWalls().add(indestructibleWall);
        }

        Wall wall;
        for (int i = 4; i < 8; i++) {
            wall = new Wall(new Position(4, i));
            game.getWalls().add(wall);
        }

        Tank tank1 = new Tank(new Position(5, 5), new MoveParameters(1));
        tank1.getMp().setDirection(MoveDirections.UP);
        game.getTanks().add(tank1);

        Tank tank2 = new Tank(new Position(8, 5), new MoveParameters(1));
        tank2.getMp().setDirection(MoveDirections.UP);
        BattleMapPanel.setTanks(game, tank2);

        Turn turn;
        for (Player value : game.getPlayers()) {
            turn = new Turn(false, value.getTank().getMp().getDirection());
            game.getTurns().add(turn);
        }
    }
}
