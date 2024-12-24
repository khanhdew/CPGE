module com.khanhdew.homealone {
    requires javafx.controls;
    requires javafx.fxml;
    requires static lombok;
    requires javafx.swing;


    opens com.khanhdew.homealone to javafx.fxml;
    exports com.khanhdew.homealone;
}