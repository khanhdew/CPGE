package com.khanhdew.gameengine.engine;

import com.khanhdew.gameengine.config.GameConfiguration;
import com.khanhdew.gameengine.entity.BaseEntity;
import com.khanhdew.gameengine.entity.Enemy;
import com.khanhdew.gameengine.entity.Player;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Setter
@Getter
public class GameEngine {
    private List<BaseEntity> entities;
    private GameState state;
    private Player player;
    private Random random;

    public GameEngine() {
        random = new Random();
        entities = new ArrayList<>();
        spawnEnemy(10);
        state = new GameState();
        player = new Player(900, 100, 50, 50);
    }

    private void spawnEnemy(int numberOfEnemies) {
        for (int i = 0; i < numberOfEnemies; i++) {
            int x = random.nextInt(0, GameConfiguration.windowWidth);
            int y = random.nextInt(0, GameConfiguration.windowHeight);
            int w = random.nextInt(50, 200);
            int h = random.nextInt(50, 200);
            entities.add(new Enemy(x, y, w, h));
        }
        System.out.println("Spawned " + numberOfEnemies + " enemies");
    }

    public void update() {
        for (BaseEntity entity : entities) {
            entity.update();
        }
        player.update();
        checkCollision();
    }

    public void checkCollision() {
        for (int i = entities.size() - 1; i >= 0; i--) {
            if (entities.get(i).intersects(player)) {
                entities.remove(i);
            }
        }
    }

    public void spawnEnemyPerSecond(int second) {
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        scheduler.scheduleAtFixedRate(() -> spawnEnemy(3), 2, second, TimeUnit.SECONDS);

    }
}
