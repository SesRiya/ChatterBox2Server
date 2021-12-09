module com.example.chatterboxserver {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.chatterboxserver to javafx.fxml;
    exports com.example.chatterboxserver;
}