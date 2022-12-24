package ru.vsu.cs.galimov.tasks.clientServer;

import ru.vsu.cs.galimov.tasks.logic.Game;

import java.util.Objects;

public class StringToLogic {
    private Game game;
    private int indexOfPlayer;

    public StringToLogic(Game game, int indexOfPlayer) {
        this.game = game;
        this.indexOfPlayer = indexOfPlayer;
    }

    public void toAction(String string){
        if(Objects.equals(string, "Left")){
            game.leftButton(indexOfPlayer);
        }
        else if(Objects.equals(string, "Right")){
            game.rightButton(indexOfPlayer);
        }
        else if(Objects.equals(string, "Up")){
            game.upButton(indexOfPlayer);
        }
        else if(Objects.equals(string, "Down")){
            game.downButton(indexOfPlayer);
        }
        else if(Objects.equals(string, "Fire")){
            game.fireButton(indexOfPlayer);
        }
    }
}
