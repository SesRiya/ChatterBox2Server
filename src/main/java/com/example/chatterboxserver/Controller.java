package com.example.chatterboxserver;

import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.IOException;

public class Controller {
    @FXML
    public TextArea textArea;
    @FXML
    public TextField tf_port;


    @FXML
    public void startButtonClicked(Event e) {
        service.start();
    }

    Service<Void> service = new Service<>() {
        @Override
        protected Task<Void> createTask() {
            return new Task<>() {
                @Override
                protected Void call() throws IOException {
                    Server threadedServer = new Server(textArea, tf_port);
                    threadedServer.listen();
                    return null;
                }
            };
        }
    };


}
