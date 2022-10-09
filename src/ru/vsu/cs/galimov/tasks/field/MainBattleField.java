package ru.vsu.cs.galimov.tasks.field;

import ru.vsu.cs.galimov.tasks.model.movable.*;
import ru.vsu.cs.galimov.tasks.model.staticObject.IndestructibleWall;
import ru.vsu.cs.galimov.tasks.model.staticObject.Wall;
import ru.vsu.cs.galimov.tasks.model.staticObject.Water;
import ru.vsu.cs.galimov.tasks.player.Player;

import java.util.*;

public class MainBattleField {
    private final char[][] field = new char[11][11];
    private final Player player = new Player(null, null, false);
    private Position oldPosition = new Position(0, 0);
    private final List<Bullet> bullets = new ArrayList<>();
    private final Scanner sc = new Scanner(System.in);
    private final List<IndestructibleWall> indestructibleWall = new ArrayList<>();
    private final List<Wall> walls = new ArrayList<>();
    private final List<Water> lakes = new ArrayList<>();

    private boolean inputKey() {
        String str = sc.next();
        if (Objects.equals(str, "w")) {
            checkLayeringAndMovementForPlayers(player,MoveDirections.UP,0,-1);
            return true;
        } else if (Objects.equals(str, "s")) {
            checkLayeringAndMovementForPlayers(player,MoveDirections.DOWN,0,1);
            return true;
        } else if (Objects.equals(str, "a")) {
            checkLayeringAndMovementForPlayers(player,MoveDirections.LEFT,-1,0);
            return true;
        } else if (Objects.equals(str, "d")) {
            checkLayeringAndMovementForPlayers(player,MoveDirections.RIGHT,1,0);
            return true;
        }

        if (Objects.equals(str, "f")) {
            player.getTank().setFire(true);
            if (player.getTank().getMp().getDirection() == MoveDirections.LEFT) {
                setBulletParams(player, MoveDirections.LEFT, -1, 0);
            } else if (player.getTank().getMp().getDirection() == MoveDirections.RIGHT) {
                setBulletParams(player, MoveDirections.RIGHT, 1, 0);
            } else if (player.getTank().getMp().getDirection() == MoveDirections.UP) {
                setBulletParams(player, MoveDirections.UP, 0, -1);
            } else if (player.getTank().getMp().getDirection() == MoveDirections.DOWN) {
                setBulletParams(player, MoveDirections.DOWN, 0, 1);
            }
            return true;
        }
        return !Objects.equals(str, "q");
    }

    // TODO same
    private void setBulletParams(Player player, MoveDirections direction, int changeX, int changeY) {
        player.getBullets().add(new Bullet(new Position(player.getTank().getPosition().x() + changeX, player.getTank().getPosition().y() + changeY), new MoveParameters(1)));
        player.getBullets().get(0).getMp().setDirection(direction);
    }

    // TODO same
    private void checkLayeringAndMovementForPlayers(Player player, MoveDirections direction, int changeX, int changeY) {
        if (layering(player.getTank(), changeX, changeY)) {
            player.getTank().getMp().setDirection(direction);
            player.getTank().move();
        }
    }

    //Todo same
    private boolean layering(Tank tank, int changeX, int changeY) {
        for (IndestructibleWall indestructibleWall : indestructibleWall) {
            if (indestructibleWall.intersects(new Position(tank.getPosition().x() + changeX, tank.getPosition().y() + changeY))) {
                return false;
            }
        }

        for (Wall wall : walls) {
            if (wall.intersects(new Position(tank.getPosition().x() + changeX, tank.getPosition().y() + changeY))) {
                return false;
            }
        }

        for (Water lake : lakes) {
            if (lake.intersects(new Position(tank.getPosition().x() + changeX, tank.getPosition().y() + changeY))) {
                return false;
            }
        }

        return true;
    }

    // TODO same
    private void isBulletReachedObject() {
        checkDestroy(player.getBullets());
    }

    // TODO same
    private void checkDestroy(List<Bullet> bullets) {
        for (int i = 0; i < bullets.size(); i++) {
            for (int j = 0; j < walls.size(); j++) {
                if (walls.get(j).destroy(walls.get(j).getPosition(), bullets.get(i))) {
                    field[walls.get(j).getPosition().y()][walls.get(j).getPosition().x()] = 'x';
                    walls.remove(j);
                    bullets.remove(i);
                    break;
                }
            }
        }

        for (int i = 0; i < bullets.size(); i++) {
            for (IndestructibleWall indestructibleWall : indestructibleWall) {
                if (bullets.get(i).destroy(indestructibleWall.getPosition(), bullets.get(i))) {
                    bullets.remove(i);
                    break;
                }
            }
        }
    }

    public void initializeGame() {
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[0].length; j++) {
                field[i][j] = 'x';
            }
        }

        IndestructibleWall wall;
        for (int i = 0; i < field[0].length; i++) {
            wall = new IndestructibleWall(new Position(0, i));
            indestructibleWall.add(wall);
        }

        for (int i = 0; i < field[0].length; i++) {
            wall = new IndestructibleWall(new Position(field.length - 1, i));
            indestructibleWall.add(wall);
        }

        for (int i = 0; i < field.length; i++) {
            wall = new IndestructibleWall(new Position(i, 0));
            indestructibleWall.add(wall);
        }

        for (int i = 0; i < field.length; i++) {
            wall = new IndestructibleWall(new Position(i, field[0].length - 1));
            indestructibleWall.add(wall);
        }

        for (IndestructibleWall indestructibleWall : indestructibleWall) {
            field[indestructibleWall.getPosition().y()][indestructibleWall.getPosition().x()] = 'D';
        }

        Wall wall1 = new Wall(new Position(4, 4));
        Wall wall2 = new Wall(new Position(4, 5));
        Wall wall3 = new Wall(new Position(4, 6));
        Wall wall4 = new Wall(new Position(4, 7));
        walls.add(wall1);
        walls.add(wall2);
        walls.add(wall3);
        walls.add(wall4);

        for (Wall currentWall : walls) {
            field[currentWall.getPosition().y()][currentWall.getPosition().x()] = 'W';
        }

        Tank tank = new Tank(new Position(5, 5), new MoveParameters(1));
        tank.getMp().setDirection(MoveDirections.UP);
        player.setTank(tank);
        player.setBullets(bullets);
        player.setCondition(true);

        field[player.getTank().getPosition().y()][player.getTank().getPosition().x()] = '^';

        printField();

        inputActions(player.getTank());

        printField();
    }

    // TODO
    private void inputActions(Tank tank) {
        while (true) {
            oldPosition = player.getTank().getPosition();
            if (inputKey()) {
                if (tank.isFire()) {
                    while (true) {
                        if (player.getBullets().size() != 0) {
                            isBulletReachedObject();
                            for (int i = 0; i < player.getBullets().size(); i++) {
                                player.getBullets().get(i).move();
                            }
                            isBulletReachedObject();
                        } else {
                            break;
                        }
                    }
                    tank.setFire(false);
                }
            } else {
                break;
            }
            updateField();
        }
    }


    private void updateField() {

        field[oldPosition.y()][oldPosition.x()] = 'x';

        if (player.getTank().getMp().getDirection() == MoveDirections.LEFT) {
            field[player.getTank().getPosition().y()][player.getTank().getPosition().x()] = '<';
        } else if (player.getTank().getMp().getDirection() == MoveDirections.RIGHT) {
            field[player.getTank().getPosition().y()][player.getTank().getPosition().x()] = '>';
        } else if (player.getTank().getMp().getDirection() == MoveDirections.UP) {
            field[player.getTank().getPosition().y()][player.getTank().getPosition().x()] = '^';
        } else if (player.getTank().getMp().getDirection() == MoveDirections.DOWN) {
            field[player.getTank().getPosition().y()][player.getTank().getPosition().x()] = 'v';
        }

        for (Wall wall : walls) {
            field[wall.getPosition().y()][wall.getPosition().x()] = 'W';
        }

        oldPosition = null;

        printField();
    }

    private void printField() {
        for (char[] chars : field) {
            for (int j = 0; j < field[0].length; j++) {
                System.out.print(chars[j] + " ");
            }
            System.out.println();
        }
        for (int i = 0; i < field[0].length; i++) {
            System.out.print("- ");
        }
        System.out.println();
    }
}
