package com.khanhdew.gameengine.entity.movable.enemy;

import com.khanhdew.gameengine.config.GameConfiguration;
import com.khanhdew.gameengine.entity.EntityManager;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Getter
public class EnemyManager implements EntityManager {
    private List<Enemy> enemies;
    private final GameConfiguration configuration = GameConfiguration.getInstance();

    public EnemyManager() {
        enemies = Collections.synchronizedList(new ArrayList<>());
    }

    @Override
    public void add(double x, double y, double w, double h) {
        enemies.add(new Enemy(x, y, w, h));
    }

    @Override
    public void remove() {

    }

    @Override
    public void update() {
        enemies.forEach(Enemy::update);
    }
}
