package ru.vsu.cs.galimov.tasks.draw;

import ru.vsu.cs.galimov.tasks.model.movable.*;
import ru.vsu.cs.galimov.tasks.model.staticObject.Thickets;
import ru.vsu.cs.galimov.tasks.model.staticObject.IndestructibleWall;
import ru.vsu.cs.galimov.tasks.model.staticObject.Wall;
import ru.vsu.cs.galimov.tasks.model.staticObject.Water;
import ru.vsu.cs.galimov.tasks.player.Player;

import java.util.ArrayList;
import java.util.List;

public class Initialization {
    public static List<Tank> initTanks(List<Position> positionList) {
        List<Tank> lt = new ArrayList<>();
        Tank tank;
        for (Position position : positionList) {
            tank = new Tank(position, new MoveParameters(50));
            tank.getMp().setDirection(MoveDirections.UP);
            lt.add(tank);
        }
        return lt;
    }

    public static List<Player> initPlayers(List<Tank> tanks, List<List<Bullet>> bullets){
        Player player;
        List<Player> players = new ArrayList<>();
        System.out.println(bullets.size());
        System.out.println(tanks.size());
        for (int i = 0; i < tanks.size(); i++) {
            player = new Player(tanks.get(i), bullets.get(i),true);
            players.add(player);
        }
        return players;
    }

    public static Bullet initBullet(Tank tank) {
        int x = tank.getPosition().x();
        int y = tank.getPosition().y();
        return new Bullet(new Position(x, y), new MoveParameters(50));
    }

    public static List<Wall> initWalls() {
        List<Wall> walls = new ArrayList<>();
        Wall wall;
        int x = 75;
        int y = 175;
        for (int i = 0; i < 4; i++) {
            wall = new Wall(new Position(x + 50 * i, y));
            walls.add(wall);
        }
        x = 1075;
        y = 575;
        for (int i = 0; i < 4; i++) {
            wall = new Wall(new Position(x + 50 * i, y));
            walls.add(wall);
        }

        x = 375;
        y = 275;
        for (int i = 0; i < 5; i++) {
            wall = new Wall(new Position(x, y + i * 50));
            walls.add(wall);
        }
        x = 925;
        for (int i = 0; i < 5; i++) {
            wall = new Wall(new Position(x, y + i * 50));
            walls.add(wall);
        }
        x = 375;
        y = 75;
        wallsBlock(walls, x, y);

        y = 575;
        wallsBlock(walls, x, y);


        return walls;
    }

    private static void wallsBlock(List<Wall> walls, int x, int y) {
        Wall wall;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 12; j++) {
                wall = new Wall(new Position(x + j * 50, y + i * 50));
                walls.add(wall);
            }
        }
    }

    public static List<Thickets> initThickets() {
        List<Thickets> thickets = new ArrayList<>();
        Thickets thicket;

        int x = 125;
        int y = 75;
        thicketsBlock(thickets, x, y);
        x = 1075;
        y = 625;
        thicketsBlock(thickets, x, y);

        x = 375;
        y = 225;

        for (int j = 0; j < 12; j++) {
            thicket = new Thickets(new Position(x + j * 50, y));
            thickets.add(thicket);
        }
        y = 525;
        for (int j = 0; j < 12; j++) {
            thicket = new Thickets(new Position(x + j * 50, y));
            thickets.add(thicket);
        }


        return thickets;
    }

    private static void thicketsBlock(List<Thickets> thickets, int x, int y) {
        Thickets thicket;
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 3; j++) {
                thicket = new Thickets(new Position(x + 50 * j, y + 50 * i));
                thickets.add(thicket);
            }
        }
    }

    public static List<Water> initWater() {
        List<Water> lakes = new ArrayList<>();
        Water lake;
        int x = 425;
        int y = 275;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 10; j++) {
                lake = new Water(new Position(x + j * 50, y + i * 50));
                lakes.add(lake);
            }
        }
        return lakes;
    }

    public static List<IndestructibleWall> initIndestructibleWalls() {
        List<IndestructibleWall> walls = new ArrayList<>();
        IndestructibleWall wall;
        int x = 25;

        for (int i = 0; i < 26; i++) {
            wall = new IndestructibleWall(new Position(x + 50 * i, 25));
            walls.add(wall);
        }

        int y = 75;
        for (int i = 0; i < 13; i++) {
            wall = new IndestructibleWall(new Position(25, y + 50 * i));
            walls.add(wall);
        }
        for (int i = 0; i < 13; i++) {
            wall = new IndestructibleWall(new Position(1275, y + 50 * i));
            walls.add(wall);
        }
        for (int i = 0; i < 26; i++) {
            wall = new IndestructibleWall(new Position(x + 50 * i, 725));
            walls.add(wall);
        }

        x = 125;
        y = 325;
        for (int i = 0; i < 3; i++) {
            wall = new IndestructibleWall(new Position(x, y + i * 50));
            walls.add(wall);
        }

        x = 1175;
        for (int i = 0; i < 3; i++) {
            wall = new IndestructibleWall(new Position(x, y + i * 50));
            walls.add(wall);
        }
        return walls;
    }
}
