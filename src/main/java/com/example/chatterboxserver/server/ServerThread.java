package com.example.chatterboxserver.server;

import com.example.chatterboxserver.cipher.CipherAES;
import javafx.scene.control.TextArea;
import utils.DbUtils;


import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.*;
import java.net.ConnectException;
import java.net.Socket;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class ServerThread extends Thread implements Runnable {
    private static final String SECRET = "Open Sesame";
    private ArrayList<ServerThread> threadList = new ArrayList<>();
    private Socket socket;
    private PrintWriter output;
    private String clientUsername;
    private TextArea textArea;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    private PrintStream writer;

    private Connection connection;

    public ServerThread(Socket socket, TextArea textArea) throws IOException {
        this.socket = socket;
        this.textArea = textArea;
        this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        //this.clientUsername = this.bufferedReader.readLine();
        writer = new PrintStream(this.socket.getOutputStream(), true);
        threadList.add(this);
    }

    public void run() {
        try {
            while (socket.isConnected()) {
                //Read client request
                String messageFromClient = this.bufferedReader.readLine();
                String decryptedMessage = decryptMessage(messageFromClient);

                //Implement the protocol
                if (decryptedMessage != null) {
                    textArea.appendText("\nThe client says : " + decryptedMessage + "\n");
                    System.out.println(decryptedMessage);

                    String[] req = decryptedMessage.split(":");
                    String command = req[0];


                    //connect to DB and get the userName password
                    if(connection == null) {
                        connection = DbUtils.connectToDb();
                    }
                    String clientId;
                    String transactionId;

                    switch (command) {
                        case "LOGIN":
                            String username = req[1];
                            String password = req[2];
                            transactionId = req[3];
                            ThreadedServer.addClientObject(username, socket);
                            writer.println("Greetings from the TCP server " + username);
                            loginOperation(req);

                            //log to History table transaction and message
                            clientId = getUserClientId(username);
                            insertHistory(transactionId, clientId, messageFromClient, "Received");

                            break;
                        case "SEND":
                            String sender = req[1];
                            String receiver = req[2];
                            String message = req[3];
                            transactionId = req[4];

                            Socket receiverSoc = ThreadedServer.getClientObject(receiver);
                            sendMessage(receiverSoc, sender + " : " + message);

                            //log to History table transaction and message
                            clientId = getUserClientId(sender);
                            insertHistory(transactionId, clientId, messageFromClient, "Received");
                            break;
                    }
                }
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (socket != null) {
                    socket.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

//    private void loginOperation(String req[]) throws IOException, InvalidAlgorithmParameterException, NoSuchPaddingException, IllegalBlockSizeException, InvalidKeySpecException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
//        String username = req[1];
//        String password = req[2];
////            String decryptedPassword = decryptMessage(password);
//
//        if ((username.equals("bob") && password.equals("bob")) || (username.equals("marley") && password.equals("marley"))) {
//            String msg = "valid";
//            writer.println(msg);
//            this.clientUsername = username;
//            System.out.println("User logged in succesfully: " + username);
//        } else {
//            String msg = "invalid";
//            writer.println(msg);
//            System.out.println("invalid user");
//        }
//    }

    private void loginOperation(String req[]) throws IOException, InvalidAlgorithmParameterException, NoSuchPaddingException, IllegalBlockSizeException, InvalidKeySpecException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException, SQLException, ClassNotFoundException {
        String username = req[1];
        String password = req[2];

        //connect to DB and get the userName password
        if(connection == null) {
            connection = DbUtils.connectToDb();
        }

        String DbPassword = getUserPasswordFromDB(username);

        //validate User password
        if ((!DbPassword.isEmpty() && password.equals(DbPassword))) {
            String msg = "valid";
            writer.println(msg);
            this.clientUsername = username;
            System.out.println("User logged in succesfully: " + username);
        } else {
            String msg = "invalid";
            writer.println(msg);
            System.out.println("invalid user");
        }
    }

    private String getUserPasswordFromDB(String userName) throws SQLException, InvalidAlgorithmParameterException, NoSuchPaddingException, UnsupportedEncodingException, IllegalBlockSizeException, InvalidKeySpecException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        ResultSet resultSet = DbUtils.executeQuery(connection, "SELECT * FROM CLIENTINFO WHERE USERNAME = '" + userName +  "'" );
        while(resultSet.next()){
            String password = resultSet.getString("Password");
            String decryptedPassword = decryptMessage(password);
            System.out.println("Password from DB: " + password);
            System.out.println("Decrypted Password: "  + decryptedPassword);
            return decryptedPassword;
        }

        return null;
    }

    private void insertHistory(String guid, String clientId, String message, String action) throws SQLException {
        String timestamp = LocalDateTime.now().toString();
        DbUtils.updateQuery(connection, "INSERT INTO history (Id, ClientId, Timestamp, Message, Action, Source) VALUES ('"
                + guid + "', '"
                + clientId + "', ' "
                + timestamp + "', '"
                + message + "', '"
                + action + "', "
                + "'Server');");
    }

    private String getUserClientId(String userName) throws SQLException {
        ResultSet resultSet = DbUtils.executeQuery(connection, "SELECT * FROM CLIENTINFO WHERE USERNAME = '" + userName +  "'" );
        while(resultSet.next()){
            String clientId = resultSet.getString("ClientId");
            System.out.println("ClientId: " + clientId);
            return clientId;
        }

        return null;
    }

    private void sendMessage(Socket socket, String message) throws IOException, InvalidAlgorithmParameterException, NoSuchPaddingException, IllegalBlockSizeException, InvalidKeySpecException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException, SQLException, ClassNotFoundException {
        String encryptedMessage = encryptMessage(message);

        PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
        writer.println(message);
    }

    //Below 2 methods are samples on how to call the CipherEAS
    //For now the SECRET is pre-defined but can be in an external location and outside of the source code
    private String encryptMessage(String message) throws InvalidAlgorithmParameterException, NoSuchPaddingException, UnsupportedEncodingException, IllegalBlockSizeException, InvalidKeySpecException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        String encryptedMessage = CipherAES.encrypt(message, SECRET);
        System.out.println("Encrypted message:" + encryptedMessage);
        return encryptedMessage;
    }

    private String decryptMessage(String message) throws InvalidAlgorithmParameterException, NoSuchPaddingException, UnsupportedEncodingException, IllegalBlockSizeException, InvalidKeySpecException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        String decryptedMessage = CipherAES.decrpyt(message, SECRET);
        System.out.println("Decrypted message:" + decryptedMessage);
        return decryptedMessage;
    }

}




