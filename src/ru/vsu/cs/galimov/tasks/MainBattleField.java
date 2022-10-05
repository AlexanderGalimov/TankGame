package ru.vsu.cs.galimov.tasks;

import ru.vsu.cs.galimov.tasks.model.movable.*;
import ru.vsu.cs.galimov.tasks.player.Player;

import java.util.*;

public class MainBattleField {
    private final char[][] field = new char[11][11];
    private Player player = new Player(null,null,false);
    private Position oldPosition = new Position(0,0);
    private MoveDirections oldDirection = MoveDirections.NONE;
    private final List<Bullet> bullets = new ArrayList<>();
    private final Scanner sc = new Scanner(System.in);

    private boolean inputKey() {
        String str = sc.next();
        if (Objects.equals(str, "w")) {
            player.getTank().getMp().setDirection(MoveDirections.UP);
            return true;
        } else if (Objects.equals(str, "s")) {
            player.getTank().getMp().setDirection(MoveDirections.DOWN);
            return true;
        } else if (Objects.equals(str, "a")) {
            player.getTank().getMp().setDirection(MoveDirections.LEFT);
            return true;
        } else if (Objects.equals(str, "d")) {
            player.getTank().getMp().setDirection(MoveDirections.RIGHT);
            return true;
        }
        return !Objects.equals(str, "q");
    }

    /*private boolean isReadyToFire() {
        String str = sc.nextLine();
        if (Objects.equals(str, "fire")) {
            player.getBullets().get(0).getMp().setDirection(player.getTank().getMp().getDirection());
            if(player.getTank().getMp().getDirection() == MoveDirections.LEFT){
                player.getBullets().add(0,new Bullet(new Position(player.getTank().getPosition().x() - 2,player.getTank().getPosition().y()),new MoveParameters(1)));
            }
            else if(player.getTank().getMp().getDirection() == MoveDirections.RIGHT){
                player.getBullets().add(0,new Bullet(new Position(player.getTank().getPosition().x() + 2,player.getTank().getPosition().y()),new MoveParameters(1)));
            }
            else if(player.getTank().getMp().getDirection() == MoveDirections.UP){
                player.getBullets().add(0,new Bullet(new Position(player.getTank().getPosition().x(),player.getTank().getPosition().y() - 2),new MoveParameters(1)));
            }
            else if(player.getTank().getMp().getDirection() == MoveDirections.DOWN){
                player.getBullets().add(0,new Bullet(new Position(player.getTank().getPosition().x(),player.getTank().getPosition().y() + 2),new MoveParameters(1)));
            }
            System.out.println(player.getBullets().get(0).getPosition());
            return true;
        }
        return !Objects.equals(str, "q");
    }*/

    public void initializeGame() {
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[0].length; j++) {
                field[i][j] = 'x';
            }
        }

        Tank tank = new Tank(new Position(5, 5), new MoveParameters(1));
        tank.setConditionIndex(4);
        tank.getMp().setDirection(MoveDirections.UP);
        player.setTank(tank);
        player.setBullets(bullets);
        player.setCondition(true);

        field[player.getTank().getPosition().y()][player.getTank().getPosition().x()] = '0';
        field[player.getTank().getPosition().y() - 1][player.getTank().getPosition().x()] = '|';

        /*Bullet bullet1 = new Bullet(new Position(player.getTank().getPosition().x(), player.getTank().getPosition().y()), new MoveParameters(1));
        bullets.add(bullet1);*/

        printField();
        while (true) {
            oldPosition = player.getTank().getPosition();
            oldDirection = player.getTank().getMp().getDirection();
            if (!inputKey()) {
                return;
            }
            player.getTank().move();
            updateField();
        }
    }

    private void updateField() {

        if(oldDirection == MoveDirections.LEFT){
            field[oldPosition.y()][oldPosition.x() - 1] = 'x';
        }
        if(oldDirection == MoveDirections.RIGHT){
            field[oldPosition.y()][oldPosition.x() + 1] = 'x';
        }
        if(oldDirection == MoveDirections.UP){
            field[oldPosition.y() - 1][oldPosition.x()] = 'x';
        }
        if(oldDirection == MoveDirections.DOWN){
            field[oldPosition.y() + 1][oldPosition.x()] = 'x';
        }
        field[oldPosition.y()][oldPosition.x()] = 'x';

        field[player.getTank().getPosition().y()][player.getTank().getPosition().x()] = 'O';
        if (player.getTank().getMp().getDirection() == MoveDirections.UP) {
            field[player.getTank().getPosition().y() - 1][player.getTank().getPosition().x()] = '|';
            //player.getBullets().get(0).getMp().setDirection(MoveDirections.UP);
        } else if (player.getTank().getMp().getDirection() == MoveDirections.DOWN) {
            field[player.getTank().getPosition().y() + 1][player.getTank().getPosition().x()] = '|';
            //player.getBullets().get(0).getMp().setDirection(MoveDirections.DOWN);
        } else if (player.getTank().getMp().getDirection() == MoveDirections.LEFT) {
            field[player.getTank().getPosition().y()][player.getTank().getPosition().x() - 1] = '-';
            //player.getBullets().get(0).getMp().setDirection(MoveDirections.LEFT);
        } else if (player.getTank().getMp().getDirection() == MoveDirections.RIGHT) {
            field[player.getTank().getPosition().y()][player.getTank().getPosition().x() + 1] = '-';
            //player.getBullets().get(0).getMp().setDirection(MoveDirections.RIGHT);
        } else {
            System.out.println("something go wrong");
        }

        /*if (isReadyToFire()) {

            printField();
        }*/

        oldPosition = null;
        oldDirection = MoveDirections.NONE;

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
