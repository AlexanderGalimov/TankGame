package ru.vsu.cs.galimov.tasks.clientServer;

public enum Command {
    SHOOT("SHOOT"),
    MOVE("MOVE"),
    END("END");

    public static final String SEPARATOR = ":";

    private final String commandString;

    Command(String commandString) {
        this.commandString = commandString;
    }

    public String getCommandString() {
        return commandString;
    }
}
