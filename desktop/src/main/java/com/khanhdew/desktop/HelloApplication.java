package com.khanhdew.desktop;

import com.khanhdew.desktop.main.GamePane;
import com.khanhdew.desktop.main.GameScene;
import com.khanhdew.gameengine.config.GameConfiguration;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        // get window size


        double windowWidth = stage.getWidth();
        double windowHeight = stage.getHeight();
        GameConfiguration configuration = new GameConfiguration((int) windowWidth, (int) windowHeight);
        GamePane gamePane = new GamePane();
        GameScene gameScene = new GameScene(gamePane);

        // xu ly lost focus
        stage.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (!newValue) {
//                    gamePane.windowFocusLost();
                } else {
//                    gamePane.getGameApp().resume();
                }
            }
        });
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                System.out.println("App closed");
                gamePane.getGameApp().getTaskQueue().stop();
                try {
                    System.exit(0);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        stage.setTitle("Hello!");
        stage.setScene(gameScene);
        stage.show();
        stage.requestFocus();
    }

    public static void main(String[] args) {
        System.setProperty("prism.order", "sw");
        launch();
    }
}