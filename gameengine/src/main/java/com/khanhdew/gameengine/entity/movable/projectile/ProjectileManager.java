package com.khanhdew.gameengine.entity.movable.projectile;

import com.khanhdew.gameengine.config.GameConfiguration;
import com.khanhdew.gameengine.entity.BaseEntity;
import com.khanhdew.gameengine.entity.EntityManager;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class ProjectileManager implements EntityManager {
    private final List<Projectile> projectiles;
    private final GameConfiguration configuration = GameConfiguration.getInstance();

    public ProjectileManager() {
        this.projectiles = new ArrayList<>();
    }

    @Override
    public void update() {
        projectiles.forEach(Projectile::update);
        remove();
    }

    @Override
    public void add(double playerX, double playerY, double targetX, double targetY) {
        projectiles.add(new Projectile(playerX, playerY, targetX, targetY));
    }

    @Override
    public void remove() {
        for (int i = projectiles.size() - 1; i >= 0; i--) {
            Projectile projectile = projectiles.get(i);
            if (projectile.getX() < 0
                    || projectile.getX() > configuration.getWindowWidth()
                    || projectile.getY() < 0
                    || projectile.getY() > configuration.getWindowHeight()) {
                projectiles.remove(i);
                System.out.println("REMOVED");
            }
        }
    }

    public Projectile checkCollision(BaseEntity entity) {
        for (int i = projectiles.size() - 1; i >= 0; i--) {
            Projectile projectile = projectiles.get(i);
            if (projectile.intersects(entity)) {
                projectiles.remove(i);
                return projectile;
            }
        }
        return null;
    }
}
