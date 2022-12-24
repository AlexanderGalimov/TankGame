package ru.vsu.cs.galimov.tasks.draw;

import ru.vsu.cs.galimov.tasks.clientServer.PlayerPanel;
import ru.vsu.cs.galimov.tasks.logic.Game;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {

    public MainWindow() throws HeadlessException {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1300, 1000);
        Game game = new Game();
        DrawPanel mainPanel = new DrawPanel(game);
        mainPanel.setFocusable(true);
        requestFocus();
        this.add(mainPanel);
    }

    public MainWindow(Game game,int indexOfPlayer) throws HeadlessException {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1300, 1000);

        PlayerPanel playerPanel = new PlayerPanel(game, indexOfPlayer);
        playerPanel.setFocusable(true);
        requestFocus();
        this.add(playerPanel);
    }

}
