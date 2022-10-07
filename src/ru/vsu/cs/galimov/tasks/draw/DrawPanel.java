package ru.vsu.cs.galimov.tasks.draw;

import ru.vsu.cs.galimov.tasks.model.movable.*;
import ru.vsu.cs.galimov.tasks.model.staticObject.Thickets;
import ru.vsu.cs.galimov.tasks.model.staticObject.UndestroyableWall;
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
    private Player player1;
    private Player player2;
    private final List<Bullet> bullets1 = new ArrayList<>();
    private final List<Bullet> bullets2 = new ArrayList<>();
    private Bullet bullet;
    private List<Wall> walls = new ArrayList<>();
    private List<UndestroyableWall> undestroyableWalls = new ArrayList<>();
    private List<Water> lakes = new ArrayList<>();
    private List<Thickets> thickets = new ArrayList<>();

    public DrawPanel() {

        initAllObjects();

        timer = new Timer(125, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (Bullet value : bullets1) {
                    value.move();
                    update();
                }
                for (Bullet value : bullets2) {
                    value.move();
                    update();
                }
            }
        });

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }
        });

        this.getInputMap().put(
                KeyStroke.getKeyStroke(KeyEvent.VK_A, 0), LEFTP1);
        Action leftFp = new AbstractAction(LEFTP1) {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (player1.isCondition()) {
                    if (!layering(player1.getTank(), -50, 0) && !tankLayering(player1.getTank(),player2.getTank(), -50, 0)) {
                        player1.getTank().getMp().setDirection(MoveDirections.LEFT);
                        player1.getTank().setConditionIndex(1);
                        player1.getTank().move();
                        update();
                    }
                }
            }
        };
        this.getActionMap().put(LEFTP1, leftFp);

        this.getInputMap().put(
                KeyStroke.getKeyStroke(KeyEvent.VK_D, 0), RIGHTP1);
        Action rightFp = new AbstractAction(RIGHTP1) {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (player1.isCondition()) {
                    if (!layering(player1.getTank(), 50, 0) && !tankLayering(player1.getTank(),player2.getTank(), 50, 0)) {
                        player1.getTank().getMp().setDirection(MoveDirections.RIGHT);
                        player1.getTank().setConditionIndex(2);
                        player1.getTank().move();
                        update();
                    }
                }
            }
        };
        this.getActionMap().put(RIGHTP1, rightFp);

        this.getInputMap().put(
                KeyStroke.getKeyStroke(KeyEvent.VK_W, 0), UPP1);
        Action upFp = new AbstractAction(UPP1) {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (player1.isCondition()) {
                    if (!layering(player1.getTank(), 0, -50)&& !tankLayering(player1.getTank(),player2.getTank(), 0, -50)) {
                        player1.getTank().getMp().setDirection(MoveDirections.UP);
                        player1.getTank().setConditionIndex(3);
                        player1.getTank().move();
                        update();
                    }
                }
            }
        };
        this.getActionMap().put(UPP1, upFp);
        this.getInputMap().put(
                KeyStroke.getKeyStroke(KeyEvent.VK_S, 0), DOWNP1);
        Action downFp = new AbstractAction(DOWNP1) {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (player1.isCondition()) {
                    if (!layering(player1.getTank(), 0, 50)&& !tankLayering(player1.getTank(),player2.getTank(),0, 50)) {
                        player1.getTank().getMp().setDirection(MoveDirections.DOWN);
                        player1.getTank().setConditionIndex(4);
                        player1.getTank().move();
                        update();
                    }
                }
            }
        };
        this.getActionMap().put(DOWNP1, downFp);

        this.getInputMap().put(
                KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0), LEFTP2);
        Action leftSp = new AbstractAction(LEFTP2) {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (player2.isCondition()) {
                    if (!layering(player2.getTank(), -50, 0) && !tankLayering(player2.getTank(),player1.getTank(), -50, 0)) {
                        player2.getTank().getMp().setDirection(MoveDirections.LEFT);
                        player2.getTank().setConditionIndex(1);
                        player2.getTank().move();
                        update();
                    }
                }
            }
        };
        this.getActionMap().put(LEFTP2, leftSp);
        this.getInputMap().put(
                KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0), RIGHTP2);
        Action rightSp = new AbstractAction(RIGHTP2) {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (player2.isCondition()) {
                    if (!layering(player2.getTank(), 50, 0) && !tankLayering(player2.getTank(),player1.getTank(), 50, 0)) {
                        player2.getTank().getMp().setDirection(MoveDirections.RIGHT);
                        player2.getTank().setConditionIndex(2);
                        player2.getTank().move();
                        update();
                    }
                }
            }
        };
        this.getActionMap().put(RIGHTP2, rightSp);

        this.getInputMap().put(
                KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0), UPP2);
        Action upSp = new AbstractAction(UPP2) {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (player2.isCondition()) {
                    if (!layering(player2.getTank(), 0, -50) && !tankLayering(player2.getTank(),player1.getTank(), 0, -50)) {
                        player2.getTank().getMp().setDirection(MoveDirections.UP);
                        player2.getTank().setConditionIndex(3);
                        player2.getTank().move();
                        update();
                    }
                }
            }
        };
        this.getActionMap().put(UPP2, upSp);

        this.getInputMap().put(
                KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0), DOWNP2);
        Action downSp = new AbstractAction(DOWNP2) {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (player2.isCondition()) {
                    if (!layering(player2.getTank(), 0, 50) && !tankLayering(player2.getTank(),player1.getTank(), 0, 50)) {
                        player2.getTank().getMp().setDirection(MoveDirections.DOWN);
                        player2.getTank().setConditionIndex(4);
                        player2.getTank().move();
                        update();
                    }
                }
            }
        };

        this.getActionMap().put(DOWNP2, downSp);

        this.getInputMap().put(
                KeyStroke.getKeyStroke(KeyEvent.VK_F, 0), FIREP1);
        Action fireFp = new AbstractAction(FIREP1) {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (player1.isCondition()) {
                    bullet = Initialization.initBullet(player1.getTank());
                    bullet.getMp().setDirection(player1.getTank().getMp().getDirection());
                    bullet.setConditionIndex(player1.getTank().getConditionIndex());
                    bullets1.add(bullet);
                    if (!timer.isRunning()) {
                        timer.start();
                    }
                }
            }
        };

        this.getActionMap().put(FIREP1, fireFp);

        this.getInputMap().put(
                KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0), FIREP2);
        Action fireSp = new AbstractAction(FIREP2) {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (player2.isCondition()) {
                    bullet = Initialization.initBullet(player2.getTank());
                    bullet.getMp().setDirection(player2.getTank().getMp().getDirection());
                    bullet.setConditionIndex(player2.getTank().getConditionIndex());
                    bullets2.add(bullet);
                    if (!timer.isRunning()) {
                        timer.start();
                    }
                }
            }
        };
        this.getActionMap().put(FIREP2, fireSp);
    }

    private void isBulletReachedObject() {
        if(player1.isCondition()){
            checkDestroy(bullets1);
        }
        if(player2.isCondition()){
            checkDestroy(bullets2);
        }
        if(player1.isCondition() && player2.isCondition()){
            checkDestroyForBullets();
        }
    }

    private boolean checkIntersects(Position position1, Position position2) {
        return position1.x() == position2.x() && position1.y() == position2.y();
    }

    private boolean layering(Tank tank, int changeX, int changeY) {
        for (Wall wall : walls) {
            if (checkIntersects(wall.getPosition(), new Position(tank.getPosition().x() + changeX, tank.getPosition().y() + changeY))) {
                return true;
            }
        }

        for (UndestroyableWall undestroyableWall : undestroyableWalls) {
            if (checkIntersects(undestroyableWall.getPosition(), new Position(tank.getPosition().x() + changeX, tank.getPosition().y() + changeY))) {
                return true;
            }
        }

        for (Water lake : lakes) {
            if (checkIntersects(lake.getPosition(), new Position(tank.getPosition().x() + changeX, tank.getPosition().y() + changeY))) {
                return true;
            }
        }

        return false;
    }

    public boolean tankLayering(Tank tank1, Tank tank2, int changeX, int changeY){
        if(player1.isCondition() && player2.isCondition()){
            if (checkIntersects(new Position(tank2.getPosition().x(), tank2.getPosition().y()), new Position(tank1.getPosition().x() + changeX, tank1.getPosition().y() + changeY))) {
                return true;
            }
        }
        return false;
    }

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
            for (UndestroyableWall undestroyableWall : undestroyableWalls) {
                if (bullets.get(i).destroy(undestroyableWall.getPosition(), bullets.get(i))) {
                    bullets.remove(i);
                    this.repaint();
                    break;
                }
            }
        }

        for (int i = 0; i < bullets.size(); i++) {
            if (checkTankIntersection(bullets, i, player1)) break;
            if (checkTankIntersection(bullets, i, player2)) break;
        }
    }

    private boolean checkTankIntersection(List<Bullet> bullets, int i, Player player) {
        if(player.isCondition()){
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

    public void checkDestroyForBullets(){
        for (int i = 0; i < player1.getBullets().size(); i++) {
            for (int j = 0; j < player2.getBullets().size(); j++) {
                if(player1.isCondition()){
                    if(player1.getBullets().get(i).destroy(player2.getBullets().get(j))
                            || player1.getBullets().get(i).destroy(player1.getBullets().get(i).getPosition(), new Bullet(new Position(player2.getBullets().get(j).getPosition().x() + 50, player2.getBullets().get(j).getPosition().y()),new MoveParameters(50)))
                            || player1.getBullets().get(i).destroy(player1.getBullets().get(i).getPosition(), new Bullet(new Position(player2.getBullets().get(j).getPosition().x() - 50, player2.getBullets().get(j).getPosition().y()),new MoveParameters(50)))){
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
        List<Tank> tankList = Initialization.initTanks();
        player1 = new Player(tankList.get(0), bullets1, true);
        player2 = new Player(tankList.get(1), bullets2, true);

        walls = Initialization.initWalls();
        undestroyableWalls = Initialization.initUndestroyableWalls();
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

        if (player1.isCondition()) {
            player1.getTank().draw(g2d);
        }

        if (player2.isCondition()) {
            player2.getTank().draw(g2d);
        }

        for (Wall wall : walls) {
            wall.draw(g2d);
        }

        for (UndestroyableWall undestroyableWall : undestroyableWalls) {
            undestroyableWall.draw(g2d);
        }

        for (Water lake : lakes) {
            lake.draw(g2d);
        }

        if (timer.isRunning()) {
            for (Bullet item : bullets1) {
                item.draw(g2d);
            }
            for (Bullet value : bullets2) {
                value.draw(g2d);
            }
            isBulletReachedObject();
            if (bullets1.size() == 0 && bullets2.size() == 0) {
                timer.stop();
            }
        }

        for (Thickets thicket: thickets){
            thicket.draw(g2d);
        }

    }

    private static final String LEFTP1 = "Left1";

    private static final String RIGHTP1 = "Right1";

    private static final String UPP1 = "Up1";

    private static final String DOWNP1 = "Down1";

    private static final String LEFTP2 = "Left2";

    private static final String RIGHTP2 = "Right2";

    private static final String UPP2 = "Up2";

    private static final String DOWNP2 = "Down2";

    private static final String FIREP1 = "Fire1";

    private static final String FIREP2 = "Fire2";


}
