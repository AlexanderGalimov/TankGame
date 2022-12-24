package ru.vsu.cs.galimov.tasks.clientServer;

import ru.vsu.cs.galimov.tasks.logic.Game;
import ru.vsu.cs.galimov.tasks.logic.Turn;
import ru.vsu.cs.galimov.tasks.model.BattleFieldObject;
import ru.vsu.cs.galimov.tasks.model.movable.MoveDirections;
import ru.vsu.cs.galimov.tasks.model.movable.MoveParameters;
import ru.vsu.cs.galimov.tasks.model.movable.Position;
import ru.vsu.cs.galimov.tasks.model.movable.Tank;
import ru.vsu.cs.galimov.tasks.model.staticObject.*;
import ru.vsu.cs.galimov.tasks.player.Player;

import java.util.ArrayList;
import java.util.List;

public class BattleMapPanel {

    public static void initAllObjects(Game game) {

        Tank tank1 = new Tank(new Position(75, 125), new MoveParameters(game.getVelocity()));
        tank1.getMp().setDirection(MoveDirections.RIGHT);
        Tank tank2 = new Tank(new Position(1225, 625), new MoveParameters(game.getVelocity()));
        tank2.getMp().setDirection(MoveDirections.LEFT);
        game.getTanks().add(tank1);
        setTanks(game, tank2);

        Eagle eagle1 = new Eagle(new Position(75, 375));
        Eagle eagle2 = new Eagle(new Position(1225, 375));

        game.getEagles().add(eagle1);
        game.getEagles().add(eagle2);

        Wall wall;
        int x = 75;
        int y = 175;
        for (int i = 0; i < 4; i++) {
            wall = new Wall(new Position(x + 50 * i, y));
            game.getWalls().add(wall);
        }
        x = 1075;
        y = 575;
        for (int i = 0; i < 4; i++) {
            wall = new Wall(new Position(x + 50 * i, y));
            game.getWalls().add(wall);
        }

        x = 375;
        y = 275;
        for (int i = 0; i < 5; i++) {
            wall = new Wall(new Position(x, y + i * 50));
            game.getWalls().add(wall);
        }
        x = 925;
        for (int i = 0; i < 5; i++) {
            wall = new Wall(new Position(x, y + i * 50));
            game.getWalls().add(wall);
        }

        wall = new Wall(new Position(75, 325));
        game.getWalls().add(wall);
        wall = new Wall(new Position(75, 425));
        game.getWalls().add(wall);
        wall = new Wall(new Position(1225, 325));
        game.getWalls().add(wall);
        wall = new Wall(new Position(1225, 425));
        game.getWalls().add(wall);

        x = 375;
        y = 75;
        makeWallsBlock(game.getWalls(), x, y);

        y = 575;
        makeWallsBlock(game.getWalls(), x, y);

        IndestructibleWall indestructibleWall;
        x = 25;

        for (int i = 0; i < 26; i++) {
            indestructibleWall = new IndestructibleWall(new Position(x + 50 * i, 25));
            game.getIndestructibleWalls().add(indestructibleWall);
        }

        y = 75;
        for (int i = 0; i < 13; i++) {
            indestructibleWall = new IndestructibleWall(new Position(25, y + 50 * i));
            game.getIndestructibleWalls().add(indestructibleWall);
        }
        for (int i = 0; i < 13; i++) {
            indestructibleWall = new IndestructibleWall(new Position(1275, y + 50 * i));
            game.getIndestructibleWalls().add(indestructibleWall);
        }
        for (int i = 0; i < 26; i++) {
            indestructibleWall = new IndestructibleWall(new Position(x + 50 * i, 725));
            game.getIndestructibleWalls().add(indestructibleWall);
        }

        x = 125;
        y = 325;
        for (int i = 0; i < 3; i++) {
            indestructibleWall = new IndestructibleWall(new Position(x, y + i * 50));
            game.getIndestructibleWalls().add(indestructibleWall);
        }

        x = 1175;
        for (int i = 0; i < 3; i++) {
            indestructibleWall = new IndestructibleWall(new Position(x, y + i * 50));
            game.getIndestructibleWalls().add(indestructibleWall);
        }

        Water lake;
        x = 425;
        y = 275;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 10; j++) {
                lake = new Water(new Position(x + j * 50, y + i * 50));
                game.getLakes().add(lake);
            }
        }

        Thickets thicket;
        x = 125;
        y = 75;
        makeThicketsBlock(game.getThickets(), x, y);
        x = 1075;
        y = 625;
        makeThicketsBlock(game.getThickets(), x, y);

        x = 375;
        y = 225;

        for (int j = 0; j < 12; j++) {
            thicket = new Thickets(new Position(x + j * 50, y));
            game.getThickets().add(thicket);
        }
        y = 525;
        for (int j = 0; j < 12; j++) {
            thicket = new Thickets(new Position(x + j * 50, y));
            game.getThickets().add(thicket);
        }

        Turn turn;
        for (Player value : game.getPlayers()) {
            turn = new Turn(false, value.getTank().getMp().getDirection());
            game.getTurns().add(turn);
        }
    }

    public static void setTanks(Game game, Tank tank2) {
        game.getTanks().add(tank2);

        Player player;
        for (BattleFieldObject tank : game.getTanks()) {
            player = new Player((Tank) tank, true);
            player.getTank().setBullets(new ArrayList<>());
            game.getPlayers().add(player);
        }
    }

    private static void makeThicketsBlock(List<BattleFieldObject> thickets, int x, int y) {
        Thickets thicket;
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 3; j++) {
                thicket = new Thickets(new Position(x + 50 * j, y + 50 * i));
                thickets.add(thicket);
            }
        }
    }

    private static void makeWallsBlock(List<BattleFieldObject> walls, int x, int y) {
        Wall wall;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 12; j++) {
                wall = new Wall(new Position(x + j * 50, y + i * 50));
                walls.add(wall);
            }
        }
    }

}
