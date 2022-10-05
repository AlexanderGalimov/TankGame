package ru.vsu.cs.galimov.tasks.draw;

import ru.vsu.cs.galimov.tasks.model.movable.*;
import ru.vsu.cs.galimov.tasks.model.staticObject.Thickets;
import ru.vsu.cs.galimov.tasks.model.staticObject.UndestroyableWall;
import ru.vsu.cs.galimov.tasks.model.staticObject.Wall;
import ru.vsu.cs.galimov.tasks.model.staticObject.Water;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Initialization {
    public static List<Tank> initTanks() {
        List<Tank> lt = new ArrayList<>();
        Tank tank1 = new Tank(new Position(425, 425), new MoveParameters(50));
        tank1.setConditionIndex(2);
        Tank tank2 = new Tank(new Position(1025, 425), new MoveParameters(50));
        tank2.setConditionIndex(1);
        tank1.getMp().setDirection(MoveDirections.RIGHT);
        tank2.getMp().setDirection(MoveDirections.LEFT);
        lt.add(tank1);
        lt.add(tank2);
        return lt;
    }

    public static Bullet initBullet(Tank tank) {
        int x = tank.getPosition().x();
        int y = tank.getPosition().y();
        return new Bullet(new Position(x, y), new MoveParameters(50));
    }

    public static List<Wall> initWalls() {
        List<Wall> walls = new ArrayList<>();
        Wall wall = new Wall(new Position(525, 225));
        walls.add(wall);
        return walls;
    }

    public static List<Thickets> initThickets(){
        List<Thickets> thickets = new ArrayList<>();
        Thickets thicket = new Thickets(new Position(225,325));
        thickets.add(thicket);
        return thickets;
    }

    public static List<Water> initWater(){
        List<Water> lakes = new ArrayList<>();
        Water lake = new Water(new Position(675, 575));
        lakes.add(lake);
        return lakes;
    }

    public static List<UndestroyableWall> initUndestroyableWalls() {
        List<UndestroyableWall> walls = new ArrayList<>();
        UndestroyableWall wall;
        int x = 25;

        for (int i = 0; i < 26; i++) {
            wall = new UndestroyableWall(new Position(x + 50 * i, 25));
            walls.add(wall);
        }

        int y = 75;
        for (int i = 0; i < 13; i++) {
            wall = new UndestroyableWall(new Position(25, y + 50 * i));
            walls.add(wall);
        }
        for (int i = 0; i < 13; i++) {
            wall = new UndestroyableWall(new Position(1275, y + 50 * i));
            walls.add(wall);
        }
        for (int i = 0; i < 26; i++) {
            wall = new UndestroyableWall(new Position(x + 50 * i, 725));
            walls.add(wall);
        }
        return walls;
    }
}
