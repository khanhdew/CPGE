package com.khanhdew.homealone;

import com.khanhdew.homealone.desktop.GamePane;
import com.khanhdew.homealone.desktop.GameScene;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.stage.Stage;


import java.io.IOException;

public class HomeAloneApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        GamePane gamePane = new GamePane();
        GameScene gameScene = new GameScene(gamePane);
        //
        stage.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (!newValue) {
                    gamePane.windowFocusLost();
                } else {
                    gamePane.getGameApp().start();
                }
            }
        });
        stage.setTitle("Hello!");
        stage.setScene(gameScene);
        stage.show();
        stage.requestFocus();
    }

    public static void main(String[] args) {
        launch();
    }
}