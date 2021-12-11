package com.example.chatterboxserver.server;

import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;


public class ThreadedServer {


    private TextArea textArea;
    private Label label;
    private int port;
    private ServerSocket listener;
    private Socket socket;
    private static HashMap<String, Socket> clientSocketsMap = new HashMap<>();

    public ThreadedServer(TextArea textArea, int port, ServerSocket listener) {
        this.textArea = textArea;
        this.port = port;
        this.listener = listener;
    }

    public void listen() throws IOException {
        textArea.appendText("The server started on " + port);
        System.out.println("Server started");


        while (true) {
            socket = listener.accept();
            textArea.appendText("Client: "+ socket+"\n");
            new ServerThread(socket, textArea).start();
        }
    }

    //update Label on UI
    public void updateLabel() {
        Platform.runLater(() -> label.setText("Server started on: " + listener.getLocalPort()));
    }
    public static void addClientObject(String username, Socket socket) {
        clientSocketsMap.put(username, socket);
    }

    public static Socket getClientObject(String username) {
        return clientSocketsMap.get(username);
    }
}


