package com.example.chatterboxserver;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;


public class Server {

    private TextField tf_port;
    private TextArea textArea;
    private int port;
    //list to add all clients to the thread
    ArrayList <ServerThread> clients = new ArrayList<>();
    private static HashMap<String, Socket> sockets = new HashMap<>();

    public Server(TextArea textArea, TextField tf_port) {
        this.textArea = textArea;
        this.tf_port = tf_port;
    }

    public void listen() throws IOException {
        port = Integer.parseInt(tf_port.getText());
        System.out.println("port number " + port);
        ServerSocket listener = new ServerSocket(port);
        textArea.appendText("The server started on " + port);
        System.out.println("Server started");
        while (true) {
            Socket socket = listener.accept();
            textArea.appendText("\nA client said : " + socket);
            new ServerThread(socket, textArea).start();
        }
    }

    public static void addSocket(String username, Socket socket) {
        sockets.put(username, socket);
    }

    public static Socket getSocket(String username) {
        return sockets.get(username);
    }
}


