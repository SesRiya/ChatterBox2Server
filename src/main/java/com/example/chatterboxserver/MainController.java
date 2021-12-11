package com.example.chatterboxserver;

import com.example.chatterboxserver.server.ThreadedServer;
import javafx.application.Platform;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.concurrent.Worker;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.UnknownHostException;

public class MainController {
    @FXML
    public TextArea textArea;
    @FXML
    public TextField tf_port;
    @FXML
    public Label serverLabel;

    private ServerSocket listener;



    //create new Service object
    Service<Void> service = new Service<>() {
        @Override
        protected Task<Void> createTask() {
            return new Task<>() {
                @Override
                protected Void call() throws IOException {
                    //validate if port number is within 0 and 65000
                    int portNumber = Integer.parseInt(tf_port.getText());
                    if (portNumber >= 0 && portNumber <= 65000) {
                        listener = new ServerSocket(portNumber);
                        ThreadedServer threadedServer = new ThreadedServer(textArea, portNumber, listener);
                        threadedServer.listen();
                        return null;
                    } else {
                        Platform.runLater(() -> serverLabel.setText("invalid number"));
                    }
                    return null;
                }
            };
        }
    };

    @FXML
    public void startButtonClicked(Event e) throws UnknownHostException {
        if (service.isRunning() || service.getState() == Worker.State.FAILED) {
            service.cancel();
            service.restart();
            serverLabel = ipAddress();
        } else {
            serverLabel = ipAddress();
            service.start();
        }
    }

    //set Label
    public Label ipAddress() throws UnknownHostException {
        serverLabel.setText(InetAddress.getLocalHost().getHostAddress());
        return serverLabel;
    }

    @FXML
    public void stopButtonClicked(Event event){
        try {
            service.cancel();
            listener.close();
            System.out.println("Server stopped");
            Platform.runLater(() -> serverLabel.setText("Server stopped"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
