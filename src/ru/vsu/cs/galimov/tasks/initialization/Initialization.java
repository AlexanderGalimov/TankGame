package ru.vsu.cs.galimov.tasks.initialization;

import ru.vsu.cs.galimov.tasks.logic.Turn;
import ru.vsu.cs.galimov.tasks.model.movable.*;
import ru.vsu.cs.galimov.tasks.model.staticObject.IndestructibleWall;
import ru.vsu.cs.galimov.tasks.model.staticObject.Thickets;
import ru.vsu.cs.galimov.tasks.model.staticObject.Wall;
import ru.vsu.cs.galimov.tasks.model.staticObject.Water;
import ru.vsu.cs.galimov.tasks.player.Player;

import java.util.ArrayList;
import java.util.List;

public class Initialization {

    public static Wall initWall(Position position) {
        return new Wall(position);
    }

    public static IndestructibleWall initIndestructibleWall(Position position) {
        return new IndestructibleWall(position);
    }

    public static Tank initTank(Position position, int velocity) {
        Tank tank = new Tank(position, new MoveParameters(velocity));
        tank.getMp().setDirection(MoveDirections.UP);
        return tank;
    }

    public static List<Player> initPlayers(List<Tank> tanks) {
        List<Player> players = new ArrayList<>();
        Player player;
        for (Tank tank : tanks) {
            player = new Player(tank, true);
            player.getTank().setBullets(new ArrayList<>());
            players.add(player);
        }
        return players;
    }

    public static Thickets initThickets(Position position) {
        return new Thickets(position);
    }

    public static Water initWater(Position position) {
        return new Water(position);
    }

    public static Turn initTurn(Player player){
        return new Turn(false,player.getTank().getMp().getDirection());
    }

    public static void setBulletParams(Tank tank, int velocity) {
        if (tank.getMp().getDirection() == MoveDirections.LEFT) {
            tank.getBullets().add(new Bullet(new Position(tank.getPosition().x() - velocity, tank.getPosition().y()), new MoveParameters(velocity)));
        } else if (tank.getMp().getDirection() == MoveDirections.RIGHT) {
            tank.getBullets().add(new Bullet(new Position(tank.getPosition().x() + velocity, tank.getPosition().y()), new MoveParameters(velocity)));
        } else if (tank.getMp().getDirection() == MoveDirections.UP) {
            tank.getBullets().add(new Bullet(new Position(tank.getPosition().x(), tank.getPosition().y() - velocity), new MoveParameters(velocity)));
        } else if (tank.getMp().getDirection() == MoveDirections.DOWN) {
            tank.getBullets().add(new Bullet(new Position(tank.getPosition().x(), tank.getPosition().y() + velocity), new MoveParameters(velocity)));
        }
        tank.getBullets().get(tank.getBullets().size() - 1).getMp().setDirection(tank.getMp().getDirection());
    }
}
