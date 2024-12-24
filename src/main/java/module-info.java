module com.khanhdew.homealone {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.khanhdew.homealone to javafx.fxml;
    exports com.khanhdew.homealone;
}