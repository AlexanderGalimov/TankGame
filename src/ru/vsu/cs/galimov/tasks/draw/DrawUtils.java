package ru.vsu.cs.galimov.tasks.draw;

import ru.vsu.cs.galimov.tasks.model.movable.Bullet;
import ru.vsu.cs.galimov.tasks.model.movable.MoveDirections;
import ru.vsu.cs.galimov.tasks.model.movable.Position;
import ru.vsu.cs.galimov.tasks.model.movable.Tank;
import ru.vsu.cs.galimov.tasks.model.staticObject.*;

import javax.swing.*;
import java.awt.*;

public class DrawUtils {
    private static final Image tankLeftImage = loadImage("Images/tankImages/tankLeft.png");
    private static final Image tankRightImage = loadImage("Images/tankImages/tankRight.png");
    private static final Image tankUpImage = loadImage("Images/tankImages/tankUp.png");
    private static final Image tankDownImage = loadImage("Images/tankImages/tankDown.png");
    private static final Image bulletLeftImage = loadImage("Images/bulletImages/bulletLeft.png");
    private static final Image bulletRightImage = loadImage("Images/bulletImages/bulletRight.png");
    private static final Image bulletUpImage = loadImage("Images/bulletImages/bulletUp.jpeg");
    private static final Image bulletDownImage = loadImage("Images/bulletImages/bulletDown.png");
    private static final Image wallImage = loadImage("Images/wall.jpeg");
    private static final Image indestructibleWallImage = loadImage("Images/indestructibleWall.jpeg");
    private static final Image waterImage = loadImage("Images/water.jpeg");
    private static final Image thicketsImage = loadImage("Images/thickets.jpeg");
    private static final Image eagleImage = loadImage("Images/eagle.jpg");

    public static void drawTank(Graphics2D g2d, Tank tank) {
        Position newPosition = new Position(tank.getPosition().x() - 25, tank.getPosition().y() - 25);
        draw(g2d, newPosition, tank.getMp().getDirection(), tankLeftImage, tankRightImage, tankUpImage, tankDownImage);
        g2d.setColor(Color.black);
        g2d.drawRect(tank.getPosition().x() - 25, tank.getPosition().y() - 25, 50, 50);
    }

    public static void drawWall(Graphics2D g2d, Wall wall) {
        g2d.drawImage(wallImage, wall.getPosition().x() - 25, wall.getPosition().y() - 25, null);
    }

    public static void drawIndestructibleWall(Graphics2D g2d, IndestructibleWall indestructibleWall) {
        g2d.drawImage(indestructibleWallImage, indestructibleWall.getPosition().x() - 25, indestructibleWall.getPosition().y() - 25, null);
    }

    public static void drawEagle(Graphics2D g2d, Eagle eagle) {
        g2d.drawImage(eagleImage, eagle.getPosition().x() - 25, eagle.getPosition().y() - 25, null);
    }

    public static void drawWater(Graphics2D g2d, Water water) {
        g2d.drawImage(waterImage, water.getPosition().x() - 25, water.getPosition().y() - 25, null);
    }

    public static void drawThickets(Graphics2D g2d, Thickets thicket) {
        g2d.drawImage(thicketsImage, thicket.getPosition().x() - 25, thicket.getPosition().y() - 25, null);
    }

    public static void drawBullet(Graphics2D g2d, Bullet bullet) {
        Position newPosition;
        if (bullet.getMp().getDirection() == MoveDirections.LEFT || bullet.getMp().getDirection() == MoveDirections.RIGHT) {
            newPosition = new Position(bullet.getPosition().x() - 25, bullet.getPosition().y() - 8);
            draw(g2d, newPosition, bullet.getMp().getDirection(), bulletLeftImage, bulletRightImage, bulletUpImage, bulletDownImage);
        }
        if (bullet.getMp().getDirection() == MoveDirections.UP || bullet.getMp().getDirection() == MoveDirections.DOWN) {
            newPosition = new Position(bullet.getPosition().x() - 8, bullet.getPosition().y() - 25);
            draw(g2d, newPosition, bullet.getMp().getDirection(), bulletLeftImage, bulletRightImage, bulletUpImage, bulletDownImage);
        }
    }

    private static void draw(Graphics2D g2d, Position position, MoveDirections direction, Image left, Image right, Image up, Image down) {
        if (direction == MoveDirections.LEFT) {
            g2d.drawImage(left, position.x(), position.y(), null);
        }
        if (direction == MoveDirections.RIGHT) {
            g2d.drawImage(right, position.x(), position.y(), null);
        }
        if (direction == MoveDirections.UP) {
            g2d.drawImage(up, position.x(), position.y(), null);
        }
        if (direction == MoveDirections.DOWN) {
            g2d.drawImage(down, position.x(), position.y(), null);
        }
    }

    public static Image loadImage(String src) {
        return new ImageIcon(src).getImage();
    }
}
