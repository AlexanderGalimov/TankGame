package ru.vsu.cs.galimov.tasks;

import ru.vsu.cs.galimov.tasks.draw.MainWindow;
import ru.vsu.cs.galimov.tasks.field.ConsoleField;
import ru.vsu.cs.galimov.tasks.logic.Game;

import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Выберите режим использования(1 - консоль, 2 - интерфейс): ");
        String str = scanner.next();
        Game game= new Game();
        if("1".equals(str)){
            new ConsoleField(game);
        }
        else if("2".equals(str)){
            MainWindow mainWindow = new MainWindow();
            mainWindow.setVisible(true);
        }
    }
}
