package ru.vsu.cs.galimov.tasks.clientServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Objects;
import java.util.Scanner;

public class AppClient {

    private final String host;
    private final int port;
    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;

    public static void main(String[] args) throws IOException {
        AppClient client = new AppClient("localhost", 9999);
        client.start();
    }

    public AppClient (String host, int port) {
        this.host = host;
        this.port = port;
    }

    public String makeStep(){
        Scanner sc = new Scanner(System.in);
        System.out.println("enter value: ");
        String str = sc.next();
        if (Objects.equals(str, "w"))
            return str;
        else if (Objects.equals(str, "s"))
            return str;
        else if (Objects.equals(str, "a"))
            return str;
        else if (Objects.equals(str, "d"))
            return str;
        else if (Objects.equals(str, "f"))
            return str;
        else if (Objects.equals(str, "q"))
            return str;
        else return "Error";
    }

    public void start() throws IOException {
        socket = new Socket(host, port);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream(), true);
        while (!socket.isClosed()) {
            System.out.println("From server before: accepted");

            String str = makeStep();
            String resp;
            if (Objects.equals(str, "w"))
                resp = Command.MOVE.getCommandString()+ Command.SEPARATOR + str;
            else if (Objects.equals(str, "s"))
                resp = Command.MOVE.getCommandString()+ Command.SEPARATOR + str;
            else if (Objects.equals(str, "a"))
                resp = Command.MOVE.getCommandString()+ Command.SEPARATOR + str;
            else if (Objects.equals(str, "d"))
                resp = Command.MOVE.getCommandString()+ Command.SEPARATOR + str;
            else if (Objects.equals(str, "f"))
                resp = Command.SHOOT.getCommandString()+ Command.SEPARATOR + str;
            else if (Objects.equals(str, "q"))
                resp = Command.END.getCommandString()+ Command.SEPARATOR + str;
            else resp = Command.END.getCommandString()+ Command.SEPARATOR + str;
            System.out.println("To server: "+resp);
            out.println(resp);

        }
    }
}
