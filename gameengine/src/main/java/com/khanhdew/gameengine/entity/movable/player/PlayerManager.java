package com.khanhdew.gameengine.entity.movable.player;

import com.khanhdew.gameengine.entity.EntityManager;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;

@Getter
public class PlayerManager implements EntityManager {
    private final Player player;
    private final List<Player> teammates;

    public PlayerManager() {
        player = new Player(900, 100, 50, 50);
        teammates = new ArrayList<>();
    }

    @Override
    public void add(double x, double y, double w, double h) {
        teammates.add(new Player(x, y, w, h));
    }

    @Override
    public void remove() {
        for (int i = teammates.size() - 1; i >= 0; i--) {
            Player teammate = teammates.get(i);
            if (!teammate.isActive()) {
                teammates.remove(i);
            }
        }
    }

    public void update() {
        player.update();
        player.getProjectileManager().update();
        for (Player teammate : teammates) {
            teammate.update();
        }
    }
}
