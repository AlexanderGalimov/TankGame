package ru.vsu.cs.galimov.tasks.draw;

import ru.vsu.cs.galimov.tasks.model.movable.MoveDirections;
import ru.vsu.cs.galimov.tasks.model.movable.Position;

import javax.swing.*;
import java.awt.*;

public class DrawUtils {
    private static final Image tankLeft = loadImage("Images/tankImages/tankLeft.png");
    private static final Image tankRight = loadImage("Images/tankImages/tankRight.png");
    private static final Image tankUp = loadImage("Images/tankImages/tankUp.png");
    private static final Image tankDown = loadImage("Images/tankImages/tankDown.png");
    private static final Image bulletLeft = loadImage("Images/bulletImages/bulletLeft.png");
    private static final Image bulletRight = loadImage("Images/bulletImages/bulletRight.png");
    private static final Image bulletUp = loadImage("Images/bulletImages/bulletUp.jpeg");
    private static final Image bulletDown = loadImage("Images/bulletImages/bulletDown.png");
    private static final Image wall = loadImage("Images/wall.jpeg");
    private static final Image indestructibleWall = loadImage("Images/indestructibleWall.jpeg");
    private static final Image water = loadImage("Images/water.jpeg");
    private static final Image thickets = loadImage("Images/thickets.jpeg");
    private static final Image eagle = loadImage("Images/eagle.jpg");

    public static void drawTank(Graphics2D g2d, Position position, MoveDirections direction) {
        Position newPosition = new Position(position.x() - 25, position.y() - 25);
        draw(g2d, newPosition, direction, tankLeft, tankRight, tankUp, tankDown);
        g2d.setColor(Color.black);
        g2d.drawRect(position.x() - 25, position.y() - 25, 50, 50);
    }

    public static void drawWall(Graphics2D g2d, Position position) {
        g2d.drawImage(wall, position.x() - 25, position.y() - 25, null);
    }

    public static void drawIndestructibleWall(Graphics2D g2d, Position position) {
        g2d.drawImage(indestructibleWall, position.x() - 25, position.y() - 25, null);
    }

    public static void drawEagle(Graphics2D g2d, Position position) {
        g2d.drawImage(eagle, position.x() - 25, position.y() - 25, null);
    }

    public static void drawWater(Graphics2D g2d, Position position) {
        g2d.drawImage(water, position.x() - 25, position.y() - 25, null);
    }

    public static void drawThickets(Graphics2D g2d, Position position) {
        g2d.drawImage(thickets, position.x() - 25, position.y() - 25, null);
    }

    public static void drawBullet(Graphics2D g2d, Position position, MoveDirections directions) {
        Position newPosition;
        if (directions == MoveDirections.LEFT || directions == MoveDirections.RIGHT) {
            newPosition = new Position(position.x() - 25, position.y() - 8);
            draw(g2d, newPosition, directions, bulletLeft, bulletRight, bulletUp, bulletDown);
        }
        if (directions == MoveDirections.UP || directions == MoveDirections.DOWN) {
            newPosition = new Position(position.x() - 8, position.y() - 25);
            draw(g2d, newPosition, directions, bulletLeft, bulletRight, bulletUp, bulletDown);
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
