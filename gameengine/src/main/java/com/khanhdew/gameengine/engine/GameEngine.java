package com.khanhdew.gameengine.engine;

import com.khanhdew.gameengine.config.GameConfiguration;
import com.khanhdew.gameengine.entity.BaseEntity;
import com.khanhdew.gameengine.entity.movable.enemy.Enemy;
import com.khanhdew.gameengine.entity.movable.enemy.EnemyManager;
import com.khanhdew.gameengine.entity.movable.player.Player;
import com.khanhdew.gameengine.entity.movable.player.PlayerManager;
import com.khanhdew.gameengine.entity.movable.projectile.Projectile;

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
    private EnemyManager enemyManager;
    private PlayerManager playerManager;
    private GameState state;
    private Player player;
    private Random random;

    public GameEngine() {
        random = new Random();
        enemyManager = new EnemyManager();
        playerManager = new PlayerManager();
        player = playerManager.getPlayer();
        spawnEnemy(10);
        state = GameState.getInstance();
    }

    private synchronized void spawnEnemy(int numberOfEnemies) {
        for (int i = 0; i < numberOfEnemies; i++) {
            int x = random.nextInt(GameConfiguration.getInstance().getWindowWidth());
            int y = random.nextInt(GameConfiguration.getInstance().getWindowHeight());
            int w = random.nextInt(200);
            int h = random.nextInt(200);
            enemyManager.add(x, y, w, h);
        }
    }

    public void update() {
        playerManager.update();
        enemyManager.update();
        checkCollision();
    }

    public void checkCollision() {
        // Kiểm tra va chạm giữa projectiles và enemies
        synchronized (player.getProjectileManager().getEntities()) {
            player.getProjectileManager().getEntities().forEach(projectile -> {
                if (projectile.isActive()) {
                    synchronized (enemyManager.getEnemies()) {
                        enemyManager.getEnemies().stream()
                                .filter(enemy -> enemy.isActive() && isColliding(projectile, enemy))
                                .forEach(enemy -> {
                                    projectile.setActive(false);
//                                    enemy.setActive(false);
                                     enemy.takeDamage(projectile.getDamage());
                                });
                    }
                }
            });
        }


        // Kiểm tra va chạm giữa player và enemies
//        for (Enemy enemy : enemyManager.getEnemies()) {
//            if (isColliding(player, enemy)) {
//                player.takeDamage(enemy.getDamage());
//            }
//        }
    }

    public boolean isColliding(BaseEntity e1, BaseEntity e2) {
        return e1.intersects(e2);
    }

    public void spawnEnemyPerSecond(int second, int numberOfenemies) {
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        scheduler.scheduleAtFixedRate(() -> {
                    if (state.isRunning())
                        spawnEnemy(numberOfenemies);
                }
                , 2, second, TimeUnit.SECONDS);
    }
}
