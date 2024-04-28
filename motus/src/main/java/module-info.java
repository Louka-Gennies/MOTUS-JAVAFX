module org.example {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    requires jdk.jconsole;

    opens org.game to javafx.fxml;
    exports org.game;
}
