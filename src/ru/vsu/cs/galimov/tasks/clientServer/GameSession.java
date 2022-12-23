package ru.vsu.cs.galimov.tasks.clientServer;

import ru.vsu.cs.galimov.tasks.draw.MainWindow;
import ru.vsu.cs.galimov.tasks.logic.Game;

import java.net.Socket;

public class GameSession implements Runnable{
    private final Game game;

    public GameSession(Socket socket){
        game = new Game();
        MainWindow mainWindowPlayer1 = new MainWindow(game, 0);
        MainWindow mainWindowPlayer2 = new MainWindow(game, 1);
        mainWindowPlayer1.setVisible(true);
        mainWindowPlayer2.setVisible(true);
    }

    @Override
    public void run() {
        while (!game.isCondition()){

        }
    }
}
