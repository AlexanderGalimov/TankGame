package ru.vsu.cs.galimov.tasks.field;

import ru.vsu.cs.galimov.tasks.initialization.Initialization;
import ru.vsu.cs.galimov.tasks.logic.LogicRealizationMainBF;
import ru.vsu.cs.galimov.tasks.model.movable.*;
import ru.vsu.cs.galimov.tasks.model.staticObject.IndestructibleWall;
import ru.vsu.cs.galimov.tasks.model.staticObject.Wall;
import ru.vsu.cs.galimov.tasks.model.staticObject.Water;
import ru.vsu.cs.galimov.tasks.player.Player;

import java.util.*;

public class MainBattleField {
    private final char[][] field = new char[11][11];
    private List<Player> players = new ArrayList<>();
    private final List<Tank> tanks = new ArrayList<>();
    private final Scanner sc = new Scanner(System.in);
    private final List<IndestructibleWall> indestructibleWalls = new ArrayList<>();
    private final List<Wall> walls = new ArrayList<>();
    private final List<Water> lakes = new ArrayList<>();
    private final LogicRealizationMainBF logicRealizationMainBF = new LogicRealizationMainBF();

    private boolean inputKey() {
        String str = sc.next();
        if (Objects.equals(str, "w")) {
            logicRealizationMainBF.checkLayering(players, walls, indestructibleWalls, lakes, MoveDirections.UP, 0, -1, 0);
            return true;
        } else if (Objects.equals(str, "s")) {
            logicRealizationMainBF.checkLayering(players, walls, indestructibleWalls, lakes, MoveDirections.DOWN, 0, 1, 0);
            return true;
        } else if (Objects.equals(str, "a")) {
            logicRealizationMainBF.checkLayering(players, walls, indestructibleWalls, lakes, MoveDirections.LEFT, -1, 0, 0);
            return true;
        } else if (Objects.equals(str, "d")) {
            logicRealizationMainBF.checkLayering(players, walls, indestructibleWalls, lakes, MoveDirections.RIGHT, 1, 0, 0);
            return true;
        }

        if (Objects.equals(str, "f")) {
            players.get(0).getTank().setFire(true);
            Initialization.setBulletParams(players.get(0).getTank(), 1);
            return true;
        }
        return !Objects.equals(str, "q");
    }

    public void initializeGame() {

        IndestructibleWall indestructibleWall;
        for (int i = 0; i < field[0].length; i++) {
            indestructibleWall = Initialization.initIndestructibleWall(new Position(0, i));
            indestructibleWalls.add(indestructibleWall);
        }

        for (int i = 0; i < field[0].length; i++) {
            indestructibleWall = Initialization.initIndestructibleWall(new Position(field.length - 1, i));
            indestructibleWalls.add(indestructibleWall);
        }

        for (int i = 0; i < field.length; i++) {
            indestructibleWall = Initialization.initIndestructibleWall(new Position(i, 0));
            indestructibleWalls.add(indestructibleWall);
        }

        for (int i = 0; i < field.length; i++) {
            indestructibleWall = Initialization.initIndestructibleWall(new Position(i, field[0].length - 1));
            indestructibleWalls.add(indestructibleWall);
        }

        Wall wall;
        for (int i = 4; i < 8; i++) {
            wall = Initialization.initWall(new Position(4, i));
            walls.add(wall);
        }

        Tank tank = Initialization.initTank(new Position(5, 5), 1);
        tanks.add(tank);

        players = Initialization.initPlayers(tanks);

        updateField();

        inputActions(players.get(0).getTank());

        printField();
    }

    private void inputActions(Tank tank) {
        while (true) {
            if (inputKey()) {
                if (tank.isFire()) {
                    while (true) {
                        if (players.get(0).getTank().getBullets().size() != 0) {
                            logicRealizationMainBF.checkBulletReachedObject(players, walls, indestructibleWalls);
                            for (int i = 0; i < players.get(0).getTank().getBullets().size(); i++) {
                                players.get(0).getTank().getBullets().get(i).move();
                            }
                            logicRealizationMainBF.checkBulletReachedObject(players, walls, indestructibleWalls);
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

        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[0].length; j++) {
                field[i][j] = 'x';
            }
        }

        for (IndestructibleWall indestructibleWall : indestructibleWalls) {
            field[indestructibleWall.getPosition().y()][indestructibleWall.getPosition().x()] = 'D';
        }

        for (Wall currentWall : walls) {
            field[currentWall.getPosition().y()][currentWall.getPosition().x()] = 'W';
        }

        if (players.get(0).getTank().getMp().getDirection() == MoveDirections.LEFT) {
            field[players.get(0).getTank().getPosition().y()][players.get(0).getTank().getPosition().x()] = '<';
        } else if (players.get(0).getTank().getMp().getDirection() == MoveDirections.RIGHT) {
            field[players.get(0).getTank().getPosition().y()][players.get(0).getTank().getPosition().x()] = '>';
        } else if (players.get(0).getTank().getMp().getDirection() == MoveDirections.UP) {
            field[players.get(0).getTank().getPosition().y()][players.get(0).getTank().getPosition().x()] = '^';
        } else if (players.get(0).getTank().getMp().getDirection() == MoveDirections.DOWN) {
            field[players.get(0).getTank().getPosition().y()][players.get(0).getTank().getPosition().x()] = 'v';
        }

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
