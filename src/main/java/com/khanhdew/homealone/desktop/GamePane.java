package com.khanhdew.homealone.desktop;

import com.khanhdew.homealone.config.GameConfiguration;
import com.khanhdew.homealone.engine.GameApp;
import com.khanhdew.homealone.engine.GameEngine;
import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.BorderPane;
import javafx.stage.Window;
import javafx.stage.WindowEvent;
import lombok.Getter;

@Getter
public class GamePane extends BorderPane {
    private final GameConfiguration configuration = GameConfiguration.getInstance();
    ;
    private final Canvas canvas = new Canvas(configuration.getWindowWidth(), configuration.getWindowHeight());
    ;
    private GameEngine gameEngine;
    private DesktopRenderer renderer;
    private DesktopInputHandler inputHandler;
    private GameApp gameApp;

    public GamePane() {
        setupPane();
        setupCanvas();
        init();

    }

    private void init() {
        gameEngine = new GameEngine();
        renderer = new DesktopRenderer(canvas);
        inputHandler = new DesktopInputHandler(gameEngine, this);
        gameApp = new GameApp(gameEngine, renderer, inputHandler);
        gameApp.start();

    }

    private void setupCanvas() {
        canvas.setFocusTraversable(true);
        canvas.requestFocus();
    }

    private void setupPane() {
        setPrefSize(configuration.getWindowWidth(), configuration.getWindowHeight());
        setCenter(canvas);
        setFocusTraversable(true);
        requestFocus();
    }

    public void windowFocusLost() {
        gameApp.stop();
        inputHandler.getKeyEventEventHandler().resetDirMovement();
    }

//    @Override
//    public void run() {
//        double timePerFrame = 1000000000.0 / 60;
//        double timePerUpdate = 1000000000.0 / 120;
//
//        long previousTime = System.nanoTime();
//
//        int frames = 0;
//        int updates = 0;
//        long lastCheck = System.currentTimeMillis();
//
//        double deltaU = 0;
//        double deltaF = 0;
//
//        while (true) {
//            long currentTime = System.nanoTime();
//
//            deltaU += (currentTime - previousTime) / timePerUpdate;
//            deltaF += (currentTime - previousTime) / timePerFrame;
//            previousTime = currentTime;
//
//            if (deltaU >= 1) {
//                gameApp.update();
//                updates++;
//                deltaU--;
//            }
//
//            if (deltaF >= 1) {
//                gameApp.update();
//                frames++;
//                deltaF--;
//            }
//
//            if (System.currentTimeMillis() - lastCheck >= 1000) {
//                lastCheck = System.currentTimeMillis();
//                System.out.println("FPS: " + frames + " | UPS: " + updates);
//                frames = 0;
//                updates = 0;
//
//            }
//        }
//
//    }
}