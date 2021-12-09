package com.example.chatterboxserver;

import javafx.scene.control.TextArea;


import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class ServerThread extends Thread implements Runnable {
    private ArrayList<ServerThread> threadList = new ArrayList<>();
    private Socket socket;
    private PrintWriter output;
    private String clientUsername;
    private TextArea textArea;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;


    public ServerThread(Socket socket, TextArea textArea) throws IOException {
        this.socket = socket;
        this.textArea = textArea;
        this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        //this.clientUsername = this.bufferedReader.readLine();
        threadList.add(this);
    }

    public void run() {
        try {
            //Read client request
            String messageFromClient = this.bufferedReader.readLine();
//            textArea.appendText("\nThe client says : " + messageFromClient);
            //Implement the protocol
            String req[] = messageFromClient.split(":");
            String command = req[0];
            System.out.println(messageFromClient);

            switch (command) {
                case "LOGIN":
                    //Perform the login operation
                    loginOperation(req);
                    //If login is success
                    String username = req[1];
                    String password = req[2];
                    Server.addSocket(username, socket);
                    //TODO delete these lines
                    PrintWriter writer = new PrintWriter(this.socket.getOutputStream(), true);
                    writer.println("Hello from server!!!");
                    break;
                case "SEND":
                    String sender = req[1];
                    String receiver = req[2];
                    String message  = req[3];
                    Socket receiverSoc = Server.getSocket(receiver);

                    sendMessage(receiverSoc, sender+" : "+message);
                    break;
            }

//            if (messageFromClient != null) {
//                this.broadcastMessage(messageFromClient);
//            }

            //Send the XML to client as the response
           // PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
           // out.println();
         //   socket.close();

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void loginOperation(String req[] ) throws IOException {
        if (req.length == 3) {
            String username = req[1];
            String password = req[2];

            if ((username.equals("bob") && password.equals("bob")) || (username.equals("marley") && password.equals("marley"))) {
                String msg = "ok login\n";
                bufferedWriter.write(msg);
                this.clientUsername = username;
                System.out.println("User logged in succesfully: " + username);
            }
        }
    }

    private void sendMessage(Socket socket, String message) throws IOException {
        PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
        writer.println(message);
    }

//    public void broadcastMessage(String messageToSend) {
//        Iterator var2 = threadList.iterator();
//
//        while (var2.hasNext()) {
//            ServerThread clientHandler = (ServerThread) var2.next();
//
//            try {
//                if (!clientHandler.clientUsername.equals(this.clientUsername)) {
//                    clientHandler.bufferedWriter.write(messageToSend);
//                    clientHandler.bufferedWriter.newLine();
//                    clientHandler.bufferedWriter.flush();
//                }
//            } catch (IOException var5) {
//                this.closeEverything(this.socket, this.bufferedReader, this.bufferedWriter);
//            }
//        }
//
//    }

    public void closeEverything(Socket socket, BufferedReader bufferedReader, BufferedWriter bufferedWriter) {

        try {
            if (bufferedReader != null) {
                bufferedReader.close();
            }

            if (bufferedWriter != null) {
                bufferedWriter.close();
            }

            if (socket != null) {
                socket.close();
            }
        } catch (IOException var5) {
            var5.printStackTrace();
        }

    }
}




