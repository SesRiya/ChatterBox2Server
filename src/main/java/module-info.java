module com.example.chatterboxserver {
    requires javafx.controls;
    requires javafx.fxml;



    exports com.example.chatterboxserver.server;
    opens com.example.chatterboxserver.server to javafx.fxml;
    exports com.example.chatterboxserver;
    opens com.example.chatterboxserver to javafx.fxml;

}