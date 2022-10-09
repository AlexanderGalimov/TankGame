package ru.vsu.cs.galimov.tasks.draw;

import ru.vsu.cs.galimov.tasks.model.movable.*;
import ru.vsu.cs.galimov.tasks.model.staticObject.Thickets;
import ru.vsu.cs.galimov.tasks.model.staticObject.IndestructibleWall;
import ru.vsu.cs.galimov.tasks.model.staticObject.Wall;
import ru.vsu.cs.galimov.tasks.model.staticObject.Water;
import ru.vsu.cs.galimov.tasks.player.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class DrawPanel extends JPanel {
    private final Timer timer;
    private final List<Position> tankStartPositions = new ArrayList<>();
    private List<List<Bullet>> playersBullets = new ArrayList<>();
    private List<Player> players = new ArrayList<>();
    private Bullet bullet;
    private List<Wall> walls = new ArrayList<>();
    private List<IndestructibleWall> indestructibleWalls = new ArrayList<>();
    private List<Water> lakes = new ArrayList<>();
    private List<Thickets> thickets = new ArrayList<>();

    public DrawPanel() {

        initAllObjects();

        timer = new Timer(125, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (Player player : players) {
                    for (int j = 0; j < player.getBullets().size(); j++) {
                        player.getBullets().get(j).move();
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
                if (players.get(0).isCondition()) {
                    checkLayeringAndMovementForPlayers(players, MoveDirections.LEFT, -50, 0, 0);
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
                if (players.get(0).isCondition()) {
                    checkLayeringAndMovementForPlayers(players, MoveDirections.RIGHT, 50, 0, 0);
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
                if (players.get(0).isCondition()) {
                    checkLayeringAndMovementForPlayers(players, MoveDirections.UP, 0, -50, 0);
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
                if (players.get(0).isCondition()) {
                    checkLayeringAndMovementForPlayers(players, MoveDirections.DOWN, 0, 50, 0);
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
                if (players.get(1).isCondition()) {
                    checkLayeringAndMovementForPlayers(players, MoveDirections.LEFT, -50, 0, 1);
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
                if (players.get(1).isCondition()) {
                    checkLayeringAndMovementForPlayers(players, MoveDirections.RIGHT, 50, 0, 1);
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
                if (players.get(1).isCondition()) {
                    checkLayeringAndMovementForPlayers(players, MoveDirections.UP, 0, -50, 1);
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
                if (players.get(1).isCondition()) {
                    checkLayeringAndMovementForPlayers(players, MoveDirections.DOWN, 0, 50, 1);
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
                    setBulletParams(players.get(0));
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
                    setBulletParams(players.get(1));
                    if (!timer.isRunning()) {
                        timer.start();
                    }
                }
            }
        };
        this.getActionMap().put(FIRE_P2, fireSp);
    }

    // TODO same
    private void setBulletParams(Player player) {
        bullet = Initialization.initBullet(player.getTank());
        bullet.getMp().setDirection(player.getTank().getMp().getDirection());
        player.getBullets().add(bullet);
    }

    // // TODO same
    private void checkLayeringAndMovementForPlayers(List<Player> players, MoveDirections direction, int changeX, int changeY, int numberOfPlayer) {
        for (int j = 0; j < players.size(); j++) {
            if (!layering(players.get(numberOfPlayer).getTank(), changeX, changeY) && !pairTankLayering(players.get(numberOfPlayer), players.get(j), changeX, changeY) && numberOfPlayer != j) {
                players.get(numberOfPlayer).getTank().getMp().setDirection(direction);
                players.get(numberOfPlayer).getTank().move();
            }
        }
    }

    // TODO same
    private void isBulletReachedObject() {
        for (Player player : players) {
            if (player.isCondition()) {
                checkDestroy(player.getBullets());
            }
        }

        for (int i = 0; i < players.size(); i++) {
            for (int j = 0; j < players.size() && i != j; j++) {
                checkDestroyForBullets(players.get(i), players.get(j));
            }
        }
    }

    // TODO same
    private boolean layering(Tank tank, int changeX, int changeY) {
        for (Wall wall : walls) {
            if (wall.intersects(new Position(tank.getPosition().x() + changeX, tank.getPosition().y() + changeY))) {
                return true;
            }
        }

        for (IndestructibleWall indestructibleWall : indestructibleWalls) {
            if (indestructibleWall.intersects(new Position(tank.getPosition().x() + changeX, tank.getPosition().y() + changeY))) {
                return true;
            }
        }

        for (Water lake : lakes) {
            if (lake.intersects(new Position(tank.getPosition().x() + changeX, tank.getPosition().y() + changeY))) {
                return true;
            }
        }

        return false;
    }

    private boolean pairTankLayering(Player player1, Player player2, int changeX, int changeY) {
        if (player1.isCondition() && player2.isCondition()) {
            return player2.getTank().intersects(new Position(player1.getTank().getPosition().x() + changeX, player1.getTank().getPosition().y() + changeY));
        }
        return false;
    }

    // TODO same
    private void checkDestroy(List<Bullet> bullets) {
        for (int i = 0; i < bullets.size(); i++) {
            for (int j = 0; j < walls.size(); j++) {
                if (walls.get(j).destroy(walls.get(j).getPosition(), bullets.get(i))) {
                    walls.remove(j);
                    bullets.remove(i);
                    this.repaint();
                    break;
                }
            }
        }

        for (int i = 0; i < bullets.size(); i++) {
            for (IndestructibleWall indestructibleWall : indestructibleWalls) {
                if (bullets.get(i).destroy(indestructibleWall.getPosition(), bullets.get(i))) {
                    bullets.remove(i);
                    this.repaint();
                    break;
                }
            }
        }

        for (int i = 0; i < bullets.size(); i++) {
            for (Player player : players) {
                if (checkTankIntersection(bullets, i, player)) break;
            }
        }
    }


    private boolean checkTankIntersection(List<Bullet> bullets, int i, Player player) {
        if (player.isCondition()) {
            if (player.getTank().destroy(player.getTank().getPosition(), bullets.get(i))) {
                player.setCondition(false);
                bullets.remove(i);
                this.repaint();
                player.setTank(null);
                return true;
            }
        }
        return false;
    }


    public void checkDestroyForBullets(Player player1, Player player2) {
        for (int i = 0; i < player1.getBullets().size(); i++) {
            for (int j = 0; j < player2.getBullets().size(); j++) {
                if (player1.isCondition()) {
                    if (player1.getBullets().get(i).destroy(player2.getBullets().get(j))
                            || player1.getBullets().get(i).destroy(player1.getBullets().get(i).getPosition(), new Bullet(new Position(player2.getBullets().get(j).getPosition().x() + 50, player2.getBullets().get(j).getPosition().y()), new MoveParameters(50)))
                            || player1.getBullets().get(i).destroy(player1.getBullets().get(i).getPosition(), new Bullet(new Position(player2.getBullets().get(j).getPosition().x() - 50, player2.getBullets().get(j).getPosition().y()), new MoveParameters(50)))
                            || player1.getBullets().get(i).destroy(player1.getBullets().get(i).getPosition(), new Bullet(new Position(player2.getBullets().get(j).getPosition().x(), player2.getBullets().get(j).getPosition().y() + 50), new MoveParameters(50)))
                            || player1.getBullets().get(i).destroy(player1.getBullets().get(i).getPosition(), new Bullet(new Position(player2.getBullets().get(j).getPosition().x(), player2.getBullets().get(j).getPosition().y() - 50), new MoveParameters(50)))) {
                        player1.getBullets().remove(i);
                        player2.getBullets().remove(j);
                        update();
                        break;
                    }
                }
            }
        }
    }

    private void update() {
        this.removeAll();
        this.repaint();
        this.revalidate();
    }

    private void initAllObjects() {
        tankStartPositions.add(new Position(75, 125));
        tankStartPositions.add(new Position(1225, 625));
        List<Tank> tankList = Initialization.initTanks(tankStartPositions);

        playersBullets = new ArrayList<>();
        for (int i = 0; i < tankList.size(); i++) {
            playersBullets.add(new ArrayList<>());
        }

        players = Initialization.initPlayers(tankList, playersBullets);

        walls = Initialization.initWalls();
        indestructibleWalls = Initialization.initIndestructibleWalls();
        lakes = Initialization.initWater();
        thickets = Initialization.initThickets();
    }

    private void drawGrid(Graphics2D g) {
        g.setColor(Color.LIGHT_GRAY);

        for (int x = 650; x < 1300; x += 50) {
            g.drawLine(x, 0, x, 1000);
        }

        for (int x = 650; x > 0; x -= 50) {
            g.drawLine(x, 0, x, 1000);
        }

        for (int y = 500; y < getHeight(); y += 50) {
            g.drawLine(0, y, 1300, y);
        }

        for (int y = 500; y > 0; y -= 50) {
            g.drawLine(0, y, 1300, y);
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

        for (Wall wall : walls) {
            wall.draw(g2d);
        }

        for (IndestructibleWall indestructibleWall : indestructibleWalls) {
            indestructibleWall.draw(g2d);
        }

        for (Water lake : lakes) {
            lake.draw(g2d);
        }

        if (timer.isRunning()) {
            for (Player player : players) {
                for (int j = 0; j < player.getBullets().size(); j++) {
                    player.getBullets().get(j).draw(g2d);
                }
            }

            for (List<Bullet> playersBullet : playersBullets) {
                for (Bullet value : playersBullet) {
                    value.draw(g2d);
                }
            }

            isBulletReachedObject();
            boolean flag = false;
            for (List<Bullet> playersBullet : playersBullets) {
                if (playersBullet.size() != 0) {
                    flag = true;
                    break;
                }
            }
            if (!flag) {
                timer.stop();
            }
        }

        for (Thickets thicket : thickets) {
            thicket.draw(g2d);
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
