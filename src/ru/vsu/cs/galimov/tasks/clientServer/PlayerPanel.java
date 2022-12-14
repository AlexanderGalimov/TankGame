package ru.vsu.cs.galimov.tasks.clientServer;

import ru.vsu.cs.galimov.tasks.logic.Game;
import ru.vsu.cs.galimov.tasks.model.BattleFieldObject;
import ru.vsu.cs.galimov.tasks.model.staticObject.Eagle;
import ru.vsu.cs.galimov.tasks.player.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class PlayerPanel extends JPanel {
    private final Timer timer;

    private final JButton button = new JButton();
    private final Game game;
    private Queue<String> actions = new LinkedList<>();

    public PlayerPanel(Game game, int indexOfPlayer) {
        this.game = game;

        BattleMapPanel.initAllObjects(game);

        timer = new Timer(125, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < game.getPlayers().get(indexOfPlayer).getTank().getBullets().size(); i++) {
                    game.timerBulletRunning(indexOfPlayer,i);
                    update();
                }
            }
        });

        this.getInputMap().put(
                KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0), LEFT);
        Action leftSp = new AbstractAction(LEFT) {
            @Override
            public void actionPerformed(ActionEvent e) {
                actions.add(LEFT);
                update();
            }
        };
        this.getActionMap().put(LEFT, leftSp);
        this.getInputMap().put(
                KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0), RIGHT);
        Action rightSp = new AbstractAction(RIGHT) {
            @Override
            public void actionPerformed(ActionEvent e) {
                actions.add(RIGHT);
                update();
            }
        };
        this.getActionMap().put(RIGHT, rightSp);

        this.getInputMap().put(
                KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0), UP);
        Action upSp = new AbstractAction(UP) {
            @Override
            public void actionPerformed(ActionEvent e) {
                actions.add(UP);
                update();
            }
        };
        this.getActionMap().put(UP, upSp);

        this.getInputMap().put(
                KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0), DOWN);
        Action downSp = new AbstractAction(DOWN) {
            @Override
            public void actionPerformed(ActionEvent e) {
                actions.add(DOWN);
                update();
            }
        };
        this.getActionMap().put(DOWN, downSp);

        this.getInputMap().put(
                KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0), FIRE);
        Action fireSp = new AbstractAction(FIRE) {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (game.getPlayers().get(indexOfPlayer).isCondition()) {
                    game.fireButton(indexOfPlayer);
                    update();
                    if (!timer.isRunning()) {
                        timer.start();
                    }
                    actions.add(FIRE);
                }
            }
        };
        this.getActionMap().put(FIRE, fireSp);
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

        for (int x = 650; x < 1300; x += game.getVelocity()) {
            g.drawLine(x, 0, x, 1000);
        }

        for (int x = 650; x > 0; x -= game.getVelocity()) {
            g.drawLine(x, 0, x, 1000);
        }

        for (int y = 500; y < getHeight(); y += game.getVelocity()) {
            g.drawLine(0, y, 1300, y);
        }

        for (int y = 500; y > 0; y -= game.getVelocity()) {
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
                    game.setCondition(false);
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

        if (timer.isRunning()) {
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
                timer.stop();
                //timer2.stop();
            }
        }
        drawList(g2d, game.getThickets());
    }


    private static final String LEFT = "Left";

    private static final String RIGHT = "Right";

    private static final String UP = "Up";

    private static final String DOWN = "Down";

    private static final String FIRE = "Fire";


}
