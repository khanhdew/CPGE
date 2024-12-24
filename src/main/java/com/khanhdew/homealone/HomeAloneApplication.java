package com.khanhdew.homealone;

import com.khanhdew.homealone.desktop.GamePane;
import com.khanhdew.homealone.desktop.GameScene;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

public class HomeAloneApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        GamePane gamePane = new GamePane();
        GameScene gameScene = new GameScene(gamePane);
        stage.setTitle("Hello!");
        stage.setScene(gameScene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}