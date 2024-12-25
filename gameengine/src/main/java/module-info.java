module com.khanhdew.gameengine {
    requires java.desktop;
    requires static lombok;
    exports com.khanhdew.gameengine.engine;
    exports com.khanhdew.gameengine.entity;
    exports com.khanhdew.gameengine.utils;
    exports com.khanhdew.gameengine.config;
}