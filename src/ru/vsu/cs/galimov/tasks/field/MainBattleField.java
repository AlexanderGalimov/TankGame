package ru.vsu.cs.galimov.tasks.field;

import ru.vsu.cs.galimov.tasks.initialization.Initialization;
import ru.vsu.cs.galimov.tasks.logic.LogicRealization;
import ru.vsu.cs.galimov.tasks.logic.Turn;
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
    private final LogicRealization logicRealization = new LogicRealization();
    private final List<Turn> turns = new ArrayList<>();

    private boolean inputKey(int numberOfPlayer) {
        System.out.println("player" + " " + (numberOfPlayer + 1));
        String str = sc.next();
        if (Objects.equals(str, "w")) {
            turns.get(numberOfPlayer).setTurned(turns.get(numberOfPlayer).getDirection() == MoveDirections.UP);
            if(!turns.get(numberOfPlayer).isTurned()){
                logicRealization.turnTank(players.get(numberOfPlayer), MoveDirections.UP);
                turns.get(numberOfPlayer).setDirection(MoveDirections.UP);
            }
            else {
                logicRealization.checkLayering(players, walls, indestructibleWalls, lakes, MoveDirections.UP, 0, -1, numberOfPlayer);
            }
            return true;
        } else if (Objects.equals(str, "s")) {
            turns.get(numberOfPlayer).setTurned(turns.get(numberOfPlayer).getDirection() == MoveDirections.DOWN);
            if(!turns.get(numberOfPlayer).isTurned()){
                logicRealization.turnTank(players.get(numberOfPlayer), MoveDirections.DOWN);
                turns.get(numberOfPlayer).setDirection(MoveDirections.DOWN);
            }
            else {
                logicRealization.checkLayering(players, walls, indestructibleWalls, lakes, MoveDirections.DOWN, 0, 1, numberOfPlayer);
            }
            return true;
        } else if (Objects.equals(str, "a")) {
            turns.get(numberOfPlayer).setTurned(turns.get(numberOfPlayer).getDirection() == MoveDirections.LEFT);
            if(!turns.get(numberOfPlayer).isTurned()){
                logicRealization.turnTank(players.get(numberOfPlayer), MoveDirections.LEFT);
                turns.get(numberOfPlayer).setDirection(MoveDirections.LEFT);
            }
            else {
                logicRealization.checkLayering(players, walls, indestructibleWalls, lakes, MoveDirections.LEFT, -1, 0, numberOfPlayer);
            }
            return true;
        } else if (Objects.equals(str, "d")) {
            turns.get(numberOfPlayer).setTurned(turns.get(numberOfPlayer).getDirection() == MoveDirections.RIGHT);
            if(!turns.get(numberOfPlayer).isTurned()){
                logicRealization.turnTank(players.get(numberOfPlayer), MoveDirections.RIGHT);
                turns.get(numberOfPlayer).setDirection(MoveDirections.RIGHT);
            }
            else{
                logicRealization.checkLayering(players, walls, indestructibleWalls, lakes, MoveDirections.RIGHT, 1, 0, numberOfPlayer);
            }
            return true;
        }

        if (Objects.equals(str, "f")) {
            players.get(numberOfPlayer).getTank().setFire(true);
            Initialization.setBulletParams(players.get(numberOfPlayer).getTank(), 1);
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

        Tank tank1 = Initialization.initTank(new Position(5, 5), 1);
        tanks.add(tank1);

        Tank tank2 = Initialization.initTank(new Position(8, 5), 1);
        tanks.add(tank2);

        players = Initialization.initPlayers(tanks);

        Turn turn;
        for (int i = 0; i < 2; i++) {
            turn = Initialization.initTurn(players.get(i));
            turns.add(turn);
        }

        updateField();

        boolean flag = false;
        while (true) {
            for (int i = 0; i < players.size(); i++) {
                if (players.get(i).isCondition()) {
                    flag = true;
                    if (inputKey(i)) {
                        inputActions(players.get(i).getTank());
                    }
                }
                updateField();
            }
            printField();
            if (!flag) {
                break;
            }
            flag = false;
        }


        printField();
    }

    private void inputActions(Tank tank) {
        if (tank.isFire()) {
            if (tank.getBullets().size() != 0) {
                for (int i = 0; i < tank.getBullets().size(); i++) {
                    int size = tank.getBullets().size();
                    logicRealization.checkBulletReachedObject(players, walls, indestructibleWalls);
                    while (size == tank.getBullets().size()){
                        tank.getBullets().get(i).move();
                        logicRealization.checkBulletReachedObject(players, walls, indestructibleWalls);
                    }
                }
            }
            tank.setFire(false);
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

        for (Player player : players) {
            if (player.isCondition()) {
                if (player.getTank().getMp().getDirection() == MoveDirections.LEFT) {
                    field[player.getTank().getPosition().y()][player.getTank().getPosition().x()] = '<';
                } else if (player.getTank().getMp().getDirection() == MoveDirections.RIGHT) {
                    field[player.getTank().getPosition().y()][player.getTank().getPosition().x()] = '>';
                } else if (player.getTank().getMp().getDirection() == MoveDirections.UP) {
                    field[player.getTank().getPosition().y()][player.getTank().getPosition().x()] = '^';
                } else if (player.getTank().getMp().getDirection() == MoveDirections.DOWN) {
                    field[player.getTank().getPosition().y()][player.getTank().getPosition().x()] = 'v';
                }
            }
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
