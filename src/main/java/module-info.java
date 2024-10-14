module org.example.spellingbee {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.fasterxml.jackson.databind;


    opens org.example.spellingbee to javafx.fxml;
    exports org.example.spellingbee;
}