package ru.vsu.cs.galimov.tasks.logic;

import ru.vsu.cs.galimov.tasks.model.movable.MoveDirections;
import ru.vsu.cs.galimov.tasks.player.Player;

public interface Moving {

    default void moveTank(Player player, MoveDirections direction) {
        player.getTank().getMp().setDirection(direction);
        player.getTank().move();
    }
}
