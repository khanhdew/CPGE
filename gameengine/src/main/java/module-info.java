module com.khanhdew.gameengine {
    requires static lombok;

    exports com.khanhdew.gameengine.engine;
    exports com.khanhdew.gameengine.entity;
    exports com.khanhdew.gameengine.utils;
    exports com.khanhdew.gameengine.config;
    exports com.khanhdew.gameengine.entity.movable;
    exports com.khanhdew.gameengine.entity.gameitem;
    exports com.khanhdew.gameengine.entity.movable.player;
    exports com.khanhdew.gameengine.entity.movable.enemy;
    exports com.khanhdew.gameengine.entity.movable.projectile;
}