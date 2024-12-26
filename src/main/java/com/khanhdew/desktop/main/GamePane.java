package com.khanhdew.desktop.main;

import com.khanhdew.desktop.main.processor.DesktopInputHandler;
import com.khanhdew.desktop.main.processor.DesktopRenderer;
import com.khanhdew.gameengine.config.GameConfiguration;
import com.khanhdew.desktop.audio.AudioPlayer;
import com.khanhdew.gameengine.engine.GameApp;
import com.khanhdew.gameengine.engine.GameEngine;
import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.BorderPane;
import lombok.Getter;


@Getter
public class GamePane extends BorderPane {
    private final GameConfiguration configuration = GameConfiguration.getInstance();
    private final Canvas canvas = new Canvas(configuration.getWindowWidth(), configuration.getWindowHeight());
    private GameEngine gameEngine;
    private DesktopRenderer renderer;
    private DesktopInputHandler inputHandler;
    private AudioPlayer audioPlayer;
    private GameApp gameApp;

    public GamePane() {
        setupPane();
        setupCanvas();
        init();
        new AnimationTimer() {
            private long previousTime = System.nanoTime();
            private int frames = 0;
            private long lastCheck = System.nanoTime();
            private double deltaF = 0;

            @Override
            public void handle(long currentTime) {
                try {
                    boolean running = gameEngine.getState().isRunning();
                    if (running) {
                        deltaF += (currentTime - previousTime) / GameConfiguration.getInstance().getTimePerFrame();
                        previousTime = currentTime;

                        if (deltaF >= 1) {
                            renderer.draw();
                            frames++;
                            deltaF--;
                        }

                        if (System.nanoTime() - lastCheck >= 1_000_000_000) {
                            lastCheck = System.nanoTime();
                            GameApp.fps = frames;
                            frames = 0;
                        }
                    }
                } catch (Exception e) {
                    System.err.println("Render thread encountered an error: " + e.getMessage());
                    e.printStackTrace();
                }
            }
        }.start();
    }

    private void init() {
        gameEngine = new GameEngine();
        renderer = new DesktopRenderer(canvas, gameEngine);
        inputHandler = new DesktopInputHandler(gameEngine, this);
        gameApp = new GameApp(gameEngine, renderer, inputHandler, audioPlayer);
        gameApp.start();

    }

    private void setupCanvas() {
        canvas.setFocusTraversable(true);
        canvas.requestFocus();
        // fix canvas bug
        canvas.widthProperty().addListener((observable, oldValue, newValue) -> {
            Platform.runLater(() -> {
                GraphicsContext gc = canvas.getGraphicsContext2D();
                gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
            });
        });

    }

    private void setupPane() {
        setPrefSize(configuration.getWindowWidth(), configuration.getWindowHeight());
        setCenter(canvas);
        setFocusTraversable(true);
        requestFocus();
    }

    public void windowFocusLost() {
        gameApp.stop();
        inputHandler.getKeyHandler().resetDirMovement();
    }
}