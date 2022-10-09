package ru.vsu.cs.galimov.tasks.draw;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {

    public MainWindow() throws HeadlessException {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1300, 1000);

        DrawPanel mainPanel = new DrawPanel();
        mainPanel.setFocusable(true);
        requestFocus();
        this.add(mainPanel);
    }
}
