package ru.vsu.cs.galimov.tasks.clientServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class SocketStrategy{

    private final Socket socket;
    private final BufferedReader in;
    private final PrintWriter out;

    public SocketStrategy(Socket socket) {
        this.socket = socket;
        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);
        } catch (IOException ex) {
            throw new IllegalStateException("Cannot connect to client", ex);
        }
    }

    public String makeMove(){
        String answer = "";
        try {
            System.out.println("To client: "+"Alright");
            out.println("Accepted");
            while ((answer = in.readLine()) == null) {
            }
            System.out.println("From client: "+answer);
            String[] answerParsed = answer.split(Command.SEPARATOR);
            if (!Command.END.getCommandString().equals(answerParsed[0])) {
                System.out.println("Player's move: " + answerParsed[1]);
            } else {
                throw new IllegalArgumentException("Client response is not recognized: "+ answer);
            }
            return answerParsed[1];
        } catch (IOException ex) {
            throw new IllegalStateException("Cannot communicate with a client", ex);
        }
    }


}
