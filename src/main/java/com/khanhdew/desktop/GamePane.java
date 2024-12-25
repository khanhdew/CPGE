package com.khanhdew.desktop;

import com.khanhdew.gameengine.config.GameConfiguration;
import com.khanhdew.desktop.audio.AudioPlayer;
import com.khanhdew.gameengine.engine.GameApp;
import com.khanhdew.gameengine.engine.GameEngine;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.BorderPane;
import lombok.Getter;


@Getter
public class GamePane extends BorderPane {
    private final GameConfiguration configuration = GameConfiguration.getInstance();
    private final Canvas canvas = new Canvas(GameConfiguration.windowWidth, GameConfiguration.windowHeight);
    private GameEngine gameEngine;
    private DesktopRenderer renderer;
    private DesktopInputHandler inputHandler;
    private AudioPlayer audioPlayer;
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
        gameApp = new GameApp(gameEngine, renderer, inputHandler, audioPlayer);
        gameApp.start();

    }

    private void setupCanvas() {
        canvas.setFocusTraversable(true);
        canvas.requestFocus();
    }

    private void setupPane() {
        setPrefSize(GameConfiguration.windowWidth, GameConfiguration.windowHeight);
        setCenter(canvas);
        setFocusTraversable(true);
        requestFocus();
    }

    public void windowFocusLost() {
        gameApp.stop();
        inputHandler.getKeyHandler().resetDirMovement();
    }
}