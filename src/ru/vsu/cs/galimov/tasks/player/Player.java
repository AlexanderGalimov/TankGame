package ru.vsu.cs.galimov.tasks.player;

import ru.vsu.cs.galimov.tasks.model.movable.Bullet;
import ru.vsu.cs.galimov.tasks.model.movable.Tank;

import java.util.List;

public class Player {
    private Tank tank;
    private List<Bullet> bullets;
    private boolean condition;

    public Player(Tank tank, List<Bullet> bullets, boolean condition) {
        this.tank = tank;
        this.bullets = bullets;
        this.condition = condition;
    }

    public Tank getTank() {
        return tank;
    }

    public void setTank(Tank tank) {
        this.tank = tank;
    }

    public List<Bullet> getBullets() {
        return bullets;
    }

    public void setBullets(List<Bullet> bullets) {
        this.bullets = bullets;
    }

    public boolean isCondition() {
        return condition;
    }

    public void setCondition(boolean condition) {
        this.condition = condition;
    }
}
