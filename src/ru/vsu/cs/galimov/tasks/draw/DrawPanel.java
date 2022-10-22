package ru.vsu.cs.galimov.tasks.draw;

import ru.vsu.cs.galimov.tasks.logic.LogicRealization;
import ru.vsu.cs.galimov.tasks.logic.Turn;
import ru.vsu.cs.galimov.tasks.model.BattleFieldObject;
import ru.vsu.cs.galimov.tasks.model.movable.*;
import ru.vsu.cs.galimov.tasks.model.staticObject.*;
import ru.vsu.cs.galimov.tasks.player.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class DrawPanel extends JPanel {
    private final Timer timer;
    private final List<Player> players = new ArrayList<>();
    private final List<BattleFieldObject> tanks = new ArrayList<>();
    private final List<BattleFieldObject> walls = new ArrayList<>();
    private final List<BattleFieldObject> indestructibleWalls = new ArrayList<>();
    private final List<BattleFieldObject> lakes = new ArrayList<>();
    private final List<BattleFieldObject> thickets = new ArrayList<>();
    private final List<BattleFieldObject> eagles = new ArrayList<>();
    private final LogicRealization logicRealization = new LogicRealization();
    private final List<Turn> turns = new ArrayList<>();
    private final int velocity = 50;

    public DrawPanel() {

        initAllObjects();

        timer = new Timer(125, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (Player player : players) {
                    for (int i = 0; i < player.getTank().getBullets().size(); i++) {
                        player.getTank().getBullets().get(i).move();
                        update();
                    }
                }
            }
        });

        this.getInputMap().put(
                KeyStroke.getKeyStroke(KeyEvent.VK_A, 0), LEFT_P1);
        Action leftFp = new AbstractAction(LEFT_P1) {
            @Override
            public void actionPerformed(ActionEvent e) {
                turns.get(0).setTurned(turns.get(0).getDirection() == MoveDirections.LEFT);
                if (!turns.get(0).isTurned()) {
                    logicRealization.turnTank(players.get(0), MoveDirections.LEFT);
                    turns.get(0).setDirection(MoveDirections.LEFT);
                } else {
                    if (players.get(0).isCondition()) {
                        logicRealization.checkLayering(players, walls, indestructibleWalls, lakes, eagles, MoveDirections.LEFT, -velocity, 0, 0);
                    }
                }
                update();
            }
        };

        this.getActionMap().put(LEFT_P1, leftFp);

        this.getInputMap().put(
                KeyStroke.getKeyStroke(KeyEvent.VK_D, 0), RIGHT_P1);
        Action rightFp = new AbstractAction(RIGHT_P1) {
            @Override
            public void actionPerformed(ActionEvent e) {
                turns.get(0).setTurned(turns.get(0).getDirection() == MoveDirections.RIGHT);
                if (!turns.get(0).isTurned()) {
                    logicRealization.turnTank(players.get(0), MoveDirections.RIGHT);
                    turns.get(0).setDirection(MoveDirections.RIGHT);
                } else {
                    if (players.get(0).isCondition()) {
                        logicRealization.checkLayering(players, walls, indestructibleWalls, lakes, eagles, MoveDirections.RIGHT, velocity, 0, 0);
                    }
                }
                update();
            }
        };
        this.getActionMap().put(RIGHT_P1, rightFp);

        this.getInputMap().put(
                KeyStroke.getKeyStroke(KeyEvent.VK_W, 0), UPP1);
        Action upFp = new AbstractAction(UPP1) {
            @Override
            public void actionPerformed(ActionEvent e) {
                turns.get(0).setTurned(turns.get(0).getDirection() == MoveDirections.UP);
                if (!turns.get(0).isTurned()) {
                    logicRealization.turnTank(players.get(0), MoveDirections.UP);
                    turns.get(0).setDirection(MoveDirections.UP);
                } else {
                    if (players.get(0).isCondition()) {
                        logicRealization.checkLayering(players, walls, indestructibleWalls, lakes, eagles, MoveDirections.UP, 0, -velocity, 0);
                    }
                }
                update();
            }
        };
        this.getActionMap().put(UPP1, upFp);

        this.getInputMap().put(
                KeyStroke.getKeyStroke(KeyEvent.VK_S, 0), DOWN_P1);
        Action downFp = new AbstractAction(DOWN_P1) {
            @Override
            public void actionPerformed(ActionEvent e) {
                turns.get(0).setTurned(turns.get(0).getDirection() == MoveDirections.DOWN);
                if (!turns.get(0).isTurned()) {
                    logicRealization.turnTank(players.get(0), MoveDirections.DOWN);
                    turns.get(0).setDirection(MoveDirections.DOWN);
                } else {
                    if (players.get(0).isCondition()) {
                        logicRealization.checkLayering(players, walls, indestructibleWalls, lakes, eagles, MoveDirections.DOWN, 0, velocity, 0);
                    }
                }
                update();
            }
        };
        this.getActionMap().put(DOWN_P1, downFp);

        this.getInputMap().put(
                KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0), LEFT_P2);
        Action leftSp = new AbstractAction(LEFT_P2) {
            @Override
            public void actionPerformed(ActionEvent e) {
                turns.get(1).setTurned(turns.get(1).getDirection() == MoveDirections.LEFT);
                if (!turns.get(1).isTurned()) {
                    logicRealization.turnTank(players.get(1), MoveDirections.LEFT);
                    turns.get(1).setDirection(MoveDirections.LEFT);
                } else {
                    if (players.get(1).isCondition()) {
                        logicRealization.checkLayering(players, walls, indestructibleWalls, lakes, eagles, MoveDirections.LEFT, -velocity, 0, 1);
                    }
                }
                update();
            }
        };
        this.getActionMap().put(LEFT_P2, leftSp);
        this.getInputMap().put(
                KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0), RIGHT_P2);
        Action rightSp = new AbstractAction(RIGHT_P2) {
            @Override
            public void actionPerformed(ActionEvent e) {

                turns.get(1).setTurned(turns.get(1).getDirection() == MoveDirections.RIGHT);
                if (!turns.get(1).isTurned()) {
                    logicRealization.turnTank(players.get(1), MoveDirections.RIGHT);
                    turns.get(1).setDirection(MoveDirections.RIGHT);
                } else {
                    if (players.get(1).isCondition()) {
                        logicRealization.checkLayering(players, walls, indestructibleWalls, lakes, eagles, MoveDirections.RIGHT, velocity, 0, 1);
                    }
                }
                update();
            }
        };
        this.getActionMap().put(RIGHT_P2, rightSp);

        this.getInputMap().put(
                KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0), UP_P2);
        Action upSp = new AbstractAction(UP_P2) {
            @Override
            public void actionPerformed(ActionEvent e) {

                turns.get(1).setTurned(turns.get(1).getDirection() == MoveDirections.UP);
                if (!turns.get(1).isTurned()) {
                    logicRealization.turnTank(players.get(1), MoveDirections.UP);
                    turns.get(1).setDirection(MoveDirections.UP);
                } else {
                    if (players.get(1).isCondition()) {
                        logicRealization.checkLayering(players, walls, indestructibleWalls, lakes, eagles, MoveDirections.UP, 0, -velocity, 1);
                    }
                }
                update();
            }
        };
        this.getActionMap().put(UP_P2, upSp);

        this.getInputMap().put(
                KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0), DOWN_P2);
        Action downSp = new AbstractAction(DOWN_P2) {
            @Override
            public void actionPerformed(ActionEvent e) {

                turns.get(1).setTurned(turns.get(1).getDirection() == MoveDirections.DOWN);
                if (!turns.get(1).isTurned()) {
                    logicRealization.turnTank(players.get(1), MoveDirections.DOWN);
                    turns.get(1).setDirection(MoveDirections.DOWN);
                } else {
                    if (players.get(1).isCondition()) {
                        logicRealization.checkLayering(players, walls, indestructibleWalls, lakes, eagles, MoveDirections.DOWN, 0, velocity, 1);
                    }
                }
                update();
            }
        };

        this.getActionMap().put(DOWN_P2, downSp);

        this.getInputMap().put(
                KeyStroke.getKeyStroke(KeyEvent.VK_F, 0), FIRE_P1);
        Action fireFp = new AbstractAction(FIRE_P1) {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (players.get(0).isCondition()) {
                    players.get(0).getTank().shoot();
                    update();
                    if (!timer.isRunning()) {
                        timer.start();
                    }
                }
            }
        };

        this.getActionMap().put(FIRE_P1, fireFp);

        this.getInputMap().put(
                KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0), FIRE_P2);
        Action fireSp = new AbstractAction(FIRE_P2) {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (players.get(1).isCondition()) {
                    players.get(1).getTank().shoot();
                    update();
                    if (!timer.isRunning()) {
                        timer.start();
                    }
                }
            }
        };
        this.getActionMap().put(FIRE_P2, fireSp);
    }

    private void update() {
        this.removeAll();
        this.repaint();
        this.revalidate();
    }

    private void drawGrid(Graphics2D g) {
        g.setColor(Color.LIGHT_GRAY);

        for (int x = 650; x < 1300; x += velocity) {
            g.drawLine(x, 0, x, 1000);
        }

        for (int x = 650; x > 0; x -= velocity) {
            g.drawLine(x, 0, x, 1000);
        }

        for (int y = 500; y < getHeight(); y += velocity) {
            g.drawLine(0, y, 1300, y);
        }

        for (int y = 500; y > 0; y -= velocity) {
            g.drawLine(0, y, 1300, y);
        }
    }

    private void drawList(Graphics2D graphics2D, List<BattleFieldObject> list) {
        for (BattleFieldObject object : list) {
            if (object instanceof Eagle) {
                if (((Eagle) object).isAlive()) {
                    object.draw(graphics2D);
                }
            } else {
                object.draw(graphics2D);
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        drawGrid(g2d);

        for (Player player : players) {
            if (player.isCondition()) {
                player.getTank().draw(g2d);
            }
        }
        drawList(g2d, eagles);
        drawList(g2d, walls);
        drawList(g2d, indestructibleWalls);
        drawList(g2d, lakes);

        if (timer.isRunning()) {
            for (Player player : players) {
                for (int j = 0; j < player.getTank().getBullets().size(); j++) {
                    player.getTank().getBullets().get(j).draw(g2d);
                }
            }

            for (Player player : players) {
                for (int j = 0; j < player.getTank().getBullets().size(); j++) {
                    player.getTank().getBullets().get(j).draw(g2d);
                }
            }

            logicRealization.checkBulletReachedObject(players, walls, indestructibleWalls, eagles, velocity);
            update();

            boolean flag = false;
            for (Player player : players) {
                if (player.getTank().getBullets().size() != 0) {
                    flag = true;
                    break;
                }
            }
            if (!flag) {
                timer.stop();
            }
        }
        drawList(g2d, thickets);
    }

    private void initAllObjects() {

        Tank tank1 = new Tank(new Position(75, 125), new MoveParameters(velocity));
        tank1.getMp().setDirection(MoveDirections.RIGHT);
        Tank tank2 = new Tank(new Position(1225, 625), new MoveParameters(velocity));
        tank2.getMp().setDirection(MoveDirections.LEFT);
        tanks.add(tank1);
        tanks.add(tank2);

        Player player;
        for (BattleFieldObject tank : tanks) {
            player = new Player((Tank) tank, true);
            player.getTank().setBullets(new ArrayList<>());
            players.add(player);
        }

        Eagle eagle1 = new Eagle(new Position(75, 375));
        Eagle eagle2 = new Eagle(new Position(1225, 375));

        eagles.add(eagle1);
        eagles.add(eagle2);

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

        wall = new Wall(new Position(75, 325));
        walls.add(wall);
        wall = new Wall(new Position(75, 425));
        walls.add(wall);
        wall = new Wall(new Position(1225, 325));
        walls.add(wall);
        wall = new Wall(new Position(1225, 425));
        walls.add(wall);

        x = 375;
        y = 75;
        makeWallsBlock(x, y);

        y = 575;
        makeWallsBlock(x, y);

        IndestructibleWall indestructibleWall;
        x = 25;

        for (int i = 0; i < 26; i++) {
            indestructibleWall = new IndestructibleWall(new Position(x + 50 * i, 25));
            indestructibleWalls.add(indestructibleWall);
        }

        y = 75;
        for (int i = 0; i < 13; i++) {
            indestructibleWall = new IndestructibleWall(new Position(25, y + 50 * i));
            indestructibleWalls.add(indestructibleWall);
        }
        for (int i = 0; i < 13; i++) {
            indestructibleWall = new IndestructibleWall(new Position(1275, y + 50 * i));
            indestructibleWalls.add(indestructibleWall);
        }
        for (int i = 0; i < 26; i++) {
            indestructibleWall = new IndestructibleWall(new Position(x + 50 * i, 725));
            indestructibleWalls.add(indestructibleWall);
        }

        x = 125;
        y = 325;
        for (int i = 0; i < 3; i++) {
            indestructibleWall = new IndestructibleWall(new Position(x, y + i * 50));
            indestructibleWalls.add(indestructibleWall);
        }

        x = 1175;
        for (int i = 0; i < 3; i++) {
            indestructibleWall = new IndestructibleWall(new Position(x, y + i * 50));
            indestructibleWalls.add(indestructibleWall);
        }

        Water lake;
        x = 425;
        y = 275;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 10; j++) {
                lake = new Water(new Position(x + j * 50, y + i * 50));
                lakes.add(lake);
            }
        }

        Thickets thicket;
        x = 125;
        y = 75;
        makeThicketsBlock(thickets, x, y);
        x = 1075;
        y = 625;
        makeThicketsBlock(thickets, x, y);

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

        Turn turn;
        for (Player value : players) {
            turn = new Turn(false, value.getTank().getMp().getDirection());
            turns.add(turn);
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

    private void makeWallsBlock(int x, int y) {
        Wall wall;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 12; j++) {
                wall = new Wall(new Position(x + j * 50, y + i * 50));
                walls.add(wall);
            }
        }
    }

    private static final String LEFT_P1 = "Left1";

    private static final String RIGHT_P1 = "Right1";

    private static final String UPP1 = "Up1";

    private static final String DOWN_P1 = "Down1";

    private static final String LEFT_P2 = "Left2";

    private static final String RIGHT_P2 = "Right2";

    private static final String UP_P2 = "Up2";

    private static final String DOWN_P2 = "Down2";

    private static final String FIRE_P1 = "Fire1";

    private static final String FIRE_P2 = "Fire2";


}
