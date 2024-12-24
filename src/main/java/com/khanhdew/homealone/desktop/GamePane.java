package com.khanhdew.homealone.desktop;

import com.khanhdew.homealone.config.GameConfiguration;
import com.khanhdew.homealone.engine.GameApp;
import com.khanhdew.homealone.engine.GameEngine;
import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.BorderPane;
import lombok.Getter;

@Getter
public class GamePane extends BorderPane  {
    private final GameConfiguration configuration = GameConfiguration.getInstance();;
    private final Canvas canvas = new Canvas(configuration.getWindowWidth(), configuration.getWindowHeight());;
    private GameEngine gameEngine;
    private DesktopRenderer renderer;
    private DesktopInputHandler inputHandler;
    private GameApp gameApp;
    private final int FPS_SET = 60;
    private final int UPS_SET = 200;

    public GamePane() {
        setupPane();
        setupCanvas();
        init();
    }

    private void init(){
        gameEngine = new GameEngine();
        renderer = new DesktopRenderer(canvas);
        inputHandler = new DesktopInputHandler(gameEngine, this);
        gameApp = new GameApp(gameEngine,renderer,inputHandler);
        gameApp.start();

//        new AnimationTimer(){
//            long lastUpdate = 0;
//            long lastRender = 0;
//            final double timePerUpdate = 1000000000.0 / UPS_SET;
//            final double timePerFrame = 1000000000.0 / FPS_SET;
//
//            @Override
//            public void handle(long now) {
//                if (lastUpdate == 0) {
//                    lastUpdate = now;
//                    lastRender = now;
//                    gameApp.update();
//                }
//
//                if (now - lastUpdate >= timePerUpdate) {
//                    lastUpdate = now;
//                    gameApp.update();
//                }
//
//                if (now - lastRender >= timePerFrame) {
//                    lastRender = now;
//                    renderer.draw(gameEngine);
//                }
//            }
//        }.start();
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

}