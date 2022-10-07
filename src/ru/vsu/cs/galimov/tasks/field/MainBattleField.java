package ru.vsu.cs.galimov.tasks.field;

import ru.vsu.cs.galimov.tasks.model.movable.*;
import ru.vsu.cs.galimov.tasks.model.staticObject.UndestroyableWall;
import ru.vsu.cs.galimov.tasks.model.staticObject.Wall;
import ru.vsu.cs.galimov.tasks.player.Player;

import java.util.*;

public class MainBattleField {
    private final char[][] field = new char[11][11];
    private final Player player = new Player(null, null, false);
    private Position oldPosition = new Position(0, 0);
    private final List<Bullet> bullets = new ArrayList<>();
    private final Scanner sc = new Scanner(System.in);
    private final List<UndestroyableWall> indestrictibleWall = new ArrayList<>();
    private final List<Wall> walls = new ArrayList<>();
    private boolean isFire = false;

    private boolean inputKey() {
        String str = sc.next();
        if (Objects.equals(str, "w")) {
            if (!checkLayering(player.getTank(), 0, -1)) {
                player.getTank().getMp().setDirection(MoveDirections.UP);
                player.getTank().move();
            }
            return true;
        } else if (Objects.equals(str, "s")) {
            if (!checkLayering(player.getTank(), 0, 1)) {
                player.getTank().getMp().setDirection(MoveDirections.DOWN);
                player.getTank().move();
            }
            return true;
        } else if (Objects.equals(str, "a")) {
            if (!checkLayering(player.getTank(), -1, 0)) {
                player.getTank().getMp().setDirection(MoveDirections.LEFT);
                player.getTank().move();
            }
            return true;
        } else if (Objects.equals(str, "d")) {
            if (!checkLayering(player.getTank(), 1, 0)) {
                player.getTank().getMp().setDirection(MoveDirections.RIGHT);
                player.getTank().move();
            }
            return true;
        }

        if (Objects.equals(str, "f")) {
            isFire = true;
            if (player.getTank().getMp().getDirection() == MoveDirections.LEFT) {
                player.getBullets().add(new Bullet(new Position(player.getTank().getPosition().x() - 1, player.getTank().getPosition().y()), new MoveParameters(1)));
                player.getBullets().get(0).getMp().setDirection(MoveDirections.LEFT);
            } else if (player.getTank().getMp().getDirection() == MoveDirections.RIGHT) {
                player.getBullets().add(new Bullet(new Position(player.getTank().getPosition().x() + 1, player.getTank().getPosition().y()), new MoveParameters(1)));
                player.getBullets().get(0).getMp().setDirection(MoveDirections.RIGHT);
            } else if (player.getTank().getMp().getDirection() == MoveDirections.UP) {
                player.getBullets().add(new Bullet(new Position(player.getTank().getPosition().x(), player.getTank().getPosition().y() - 1), new MoveParameters(1)));
                player.getBullets().get(0).getMp().setDirection(MoveDirections.UP);
            } else if (player.getTank().getMp().getDirection() == MoveDirections.DOWN) {
                player.getBullets().add(new Bullet(new Position(player.getTank().getPosition().x(), player.getTank().getPosition().y() + 1), new MoveParameters(1)));
                player.getBullets().get(0).getMp().setDirection(MoveDirections.DOWN);
            }
            return true;
        }

        return !Objects.equals(str, "q");
    }

    public boolean intersects(Position position1, Position position2) {
        return position1.x() == position2.x() && position1.y() == position2.y();
    }

    public boolean checkLayering(Tank tank, int changeX, int changeY) {
        for (UndestroyableWall undestroyableWall : indestrictibleWall) {
            if (intersects(undestroyableWall.getPosition(), new Position(tank.getPosition().x() + changeX, tank.getPosition().y() + changeY))) {
                return true;
            }
        }

        for (Wall wall : walls) {
            if (intersects(wall.getPosition(), new Position(tank.getPosition().x() + changeX, tank.getPosition().y() + changeY))) {
                return true;
            }
        }

        return false;
    }

    private void isBulletReachedObject() {
        checkDestroy(player.getBullets());
    }

    private void checkDestroy(List<Bullet> bullets) {
        for (int i = 0; i < bullets.size(); i++) {
            for (int j = 0; j < walls.size(); j++) {
                if (walls.get(j).destroy(walls.get(j).getPosition(), bullets.get(i))) {
                    field[walls.get(i).getPosition().y()][walls.get(i).getPosition().x()] = 'x';
                    walls.remove(j);
                    bullets.remove(i);
                    break;
                }
            }
        }

        for (int i = 0; i < bullets.size(); i++) {
            for (UndestroyableWall undestroyableWall : indestrictibleWall) {
                if (bullets.get(i).destroy(undestroyableWall.getPosition(), bullets.get(i))) {
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

        UndestroyableWall wall;
        for (int i = 0; i < field[0].length; i++) {
            wall = new UndestroyableWall(new Position(0, i));
            indestrictibleWall.add(wall);
        }

        for (int i = 0; i < field[0].length; i++) {
            wall = new UndestroyableWall(new Position(field.length - 1, i));
            indestrictibleWall.add(wall);
        }

        for (int i = 0; i < field.length; i++) {
            wall = new UndestroyableWall(new Position(i, 0));
            indestrictibleWall.add(wall);
        }

        for (int i = 0; i < field.length; i++) {
            wall = new UndestroyableWall(new Position(i, field[0].length - 1));
            indestrictibleWall.add(wall);
        }

        for (UndestroyableWall undestroyableWall : indestrictibleWall) {
            field[undestroyableWall.getPosition().y()][undestroyableWall.getPosition().x()] = 'D';
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
        tank.setConditionIndex(3);
        tank.getMp().setDirection(MoveDirections.UP);
        player.setTank(tank);
        player.setBullets(bullets);
        player.setCondition(true);

        field[player.getTank().getPosition().y()][player.getTank().getPosition().x()] = '0';

        printField();
        while (true) {
            oldPosition = player.getTank().getPosition();
            if (inputKey()) {
                if (isFire) {
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
                    isFire = false;
                }
            } else {
                break;
            }
            updateField();
        }
        printField();
    }

    private void updateField() {

        field[oldPosition.y()][oldPosition.x()] = 'x';

        field[player.getTank().getPosition().y()][player.getTank().getPosition().x()] = 'O';

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
