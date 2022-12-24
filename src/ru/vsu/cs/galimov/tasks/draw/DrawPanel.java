package ru.vsu.cs.galimov.tasks.draw;

import ru.vsu.cs.galimov.tasks.clientServer.BattleMapPanel;
import ru.vsu.cs.galimov.tasks.logic.Game;
import ru.vsu.cs.galimov.tasks.model.BattleFieldObject;
import ru.vsu.cs.galimov.tasks.model.staticObject.Eagle;
import ru.vsu.cs.galimov.tasks.player.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.List;

public class DrawPanel extends JPanel {
    private final Timer timer1;
    private final Timer timer2;
    private final Game game;
    private final JButton button = new JButton();

    public DrawPanel(Game game) {
        this.game = game;

        BattleMapPanel.initAllObjects(game);

        timer1 = new Timer(125, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < game.getPlayers().get(0).getTank().getBullets().size(); i++) {
                    game.getPlayers().get(0).getTank().getBullets().get(i).move();
                    update();
                }
            }
        });

        timer2 = new Timer(125, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < game.getPlayers().get(1).getTank().getBullets().size(); i++) {
                    game.getPlayers().get(1).getTank().getBullets().get(i).move();
                    update();
                }
            }
        });

        this.getInputMap().put(
                KeyStroke.getKeyStroke(KeyEvent.VK_A, 0), LEFT_P1);
        Action leftFp = new AbstractAction(LEFT_P1) {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.leftButton(0);
                update();
            }
        };

        this.getActionMap().put(LEFT_P1, leftFp);

        this.getInputMap().put(
                KeyStroke.getKeyStroke(KeyEvent.VK_D, 0), RIGHT_P1);
        Action rightFp = new AbstractAction(RIGHT_P1) {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.rightButton(0);
                update();
            }
        };
        this.getActionMap().put(RIGHT_P1, rightFp);

        this.getInputMap().put(
                KeyStroke.getKeyStroke(KeyEvent.VK_W, 0), UPP1);
        Action upFp = new AbstractAction(UPP1) {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.upButton(0);
                update();
            }
        };
        this.getActionMap().put(UPP1, upFp);

        this.getInputMap().put(
                KeyStroke.getKeyStroke(KeyEvent.VK_S, 0), DOWN_P1);
        Action downFp = new AbstractAction(DOWN_P1) {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.downButton(0);
                update();
            }
        };
        this.getActionMap().put(DOWN_P1, downFp);

        this.getInputMap().put(
                KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0), LEFT_P2);
        Action leftSp = new AbstractAction(LEFT_P2) {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.leftButton(1);
                update();
            }
        };
        this.getActionMap().put(LEFT_P2, leftSp);
        this.getInputMap().put(
                KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0), RIGHT_P2);
        Action rightSp = new AbstractAction(RIGHT_P2) {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.rightButton(1);
                update();
            }
        };
        this.getActionMap().put(RIGHT_P2, rightSp);

        this.getInputMap().put(
                KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0), UP_P2);
        Action upSp = new AbstractAction(UP_P2) {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.upButton(1);
                update();
            }
        };
        this.getActionMap().put(UP_P2, upSp);

        this.getInputMap().put(
                KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0), DOWN_P2);
        Action downSp = new AbstractAction(DOWN_P2) {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.downButton(1);
                update();
            }
        };

        this.getActionMap().put(DOWN_P2, downSp);

        this.getInputMap().put(
                KeyStroke.getKeyStroke(KeyEvent.VK_F, 0), FIRE_P1);
        Action fireFp = new AbstractAction(FIRE_P1) {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (game.getPlayers().get(0).isCondition()) {
                    game.fireButton(0);
                    update();
                    if (!timer1.isRunning()) {
                        timer1.start();
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
                if (game.getPlayers().get(1).isCondition()) {
                    game.fireButton(1);
                    update();
                    if (!timer2.isRunning()) {
                        timer2.start();
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

    private void restart() {
        game.restart();
        BattleMapPanel.initAllObjects(game);
        this.repaint();
    }

    private void valid() {
        this.repaint();
        button.setText("restart");
        button.setSize(100, 50);
        button.setLocation(600, 0);
        button.setVisible(false);

        button.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                restart();
                button.setVisible(false);
            }
        });
        add(button);

        button.setVisible(true);
        this.repaint();
    }

    private void drawGrid(Graphics2D g) {
        g.setColor(Color.LIGHT_GRAY);

        int velocity = 50;
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

        for (BattleFieldObject eagle : game.getEagles()) {
            if (!(((Eagle) eagle).isAlive())) {
                for (Player player : game.getPlayers()) {
                    player.setCondition(false);
                }
                update();
                valid();
                break;
            }
        }

        for (Player player : game.getPlayers()) {
            if (player.isCondition()) {
                player.getTank().draw(g2d);
            }
        }
        drawList(g2d, game.getEagles());
        drawList(g2d, game.getWalls());
        drawList(g2d, game.getIndestructibleWalls());
        drawList(g2d, game.getLakes());

        if (timer1.isRunning() || timer2.isRunning()) {
            for (Player player : game.getPlayers()) {
                for (int j = 0; j < player.getTank().getBullets().size(); j++) {
                    player.getTank().getBullets().get(j).draw(g2d);
                }
            }

            for (Player player : game.getPlayers()) {
                for (int j = 0; j < player.getTank().getBullets().size(); j++) {
                    player.getTank().getBullets().get(j).draw(g2d);
                }
            }

            game.destroyObjectsByBullet();
            update();

            boolean flag = false;
            for (Player player : game.getPlayers()) {
                if (player.getTank().getBullets().size() != 0) {
                    flag = true;
                    break;
                }
            }
            if (!flag) {
                timer1.stop();
                timer2.stop();
            }
        }
        drawList(g2d, game.getThickets());
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
