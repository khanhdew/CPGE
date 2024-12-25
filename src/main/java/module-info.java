module com.khanhdew.desktop {
    requires javafx.controls;
    requires javafx.fxml;
    requires lombok;
    requires javafx.media;
    requires javafx.swing;
    requires com.khanhdew.gameengine;

    opens com.khanhdew.desktop to javafx.fxml;
    exports com.khanhdew.desktop;
    exports com.khanhdew.desktop.main;
    opens com.khanhdew.desktop.main to javafx.fxml;
    exports com.khanhdew.desktop.main.processor;
    opens com.khanhdew.desktop.main.processor to javafx.fxml;
}