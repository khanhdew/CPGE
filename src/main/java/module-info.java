module com.khanhdew.desktop {
    requires javafx.controls;
    requires javafx.fxml;
    requires lombok;
    requires javafx.media;
    requires javafx.swing;
    requires com.khanhdew.gameengine;

    opens com.khanhdew.desktop to javafx.fxml;
    exports com.khanhdew.desktop;
}