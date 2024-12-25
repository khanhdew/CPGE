package com.khanhdew.gameengine.engine;

import com.khanhdew.gameengine.entity.BaseEntity;
import com.khanhdew.gameengine.entity.Enemy;
import com.khanhdew.gameengine.entity.Player;
import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;
import java.util.List;

@Setter
@Getter
public class GameEngine {
    private List<BaseEntity> entities;
    private GameState state;
    private Player player;

    public GameEngine() {
        entities = Arrays.asList(
                new Enemy(50, 10, 100, 80),
                new Enemy(100, 70, 70, 120),
                new Enemy(279, 659, 120, 110),
                new Enemy(384, 389, 50, 90),
                new Enemy(700, 600, 40, 70)
        );
        state = new GameState();
        player = new Player(900, 100, 50, 50);
    }

    public void update() {
        for (BaseEntity entity : entities) {
            entity.update();
        }
        player.update();
        checkCollision();
        System.out.println(entities.size());
    }

    public void checkCollision() {
        for (int i = entities.size() - 1; i >= 0; i--) {
            if (entities.get(i).intersects(player)) {
                entities.remove(i);
            }
        }
    }
}
