package ru.vsu.cs.galimov.tasks.logic;

import ru.vsu.cs.galimov.tasks.model.movable.MoveDirections;
import ru.vsu.cs.galimov.tasks.player.Player;

public interface Moving {

    void moveTank(Player player, MoveDirections direction);

    void turnTank(Player player, MoveDirections direction);
}
