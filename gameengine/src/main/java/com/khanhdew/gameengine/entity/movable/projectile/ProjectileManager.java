package com.khanhdew.gameengine.entity.movable.projectile;

import com.khanhdew.gameengine.config.GameConfiguration;
import com.khanhdew.gameengine.entity.BaseEntity;
import com.khanhdew.gameengine.entity.EntityManager;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Getter
public class ProjectileManager implements EntityManager {
    private final List<Projectile> projectiles;
    private final GameConfiguration configuration = GameConfiguration.getInstance();

    public ProjectileManager() {
        this.projectiles = Collections.synchronizedList(new ArrayList<>());
    }

    @Override
    public void update() {
        projectiles.forEach(Projectile::update);
        remove();
    }

    @Override
    public void add(double playerX, double playerY, double targetX, double targetY) {
        Optional<Projectile> inactiveProjectile = projectiles.stream()
                .filter(e -> !e.isActive())
                .findFirst();

        if (inactiveProjectile.isPresent()) {
            Projectile e = inactiveProjectile.get();
            e.setActive(true);
            e.reset(playerX, playerY, targetX, targetY);

        } else {
            // Nếu không có projectile nào không hoạt động, tạo mới
            projectiles.add(new Projectile(playerX, playerY, targetX, targetY));
        }
    }

    @Override
    public void remove() {
//        for (int i = projectiles.size() - 1; i >= 0; i--) {
//            Projectile projectile = projectiles.get(i);
//            if (projectile.getX() < 0
//                    || projectile.getX() > configuration.getWindowWidth()
//                    || projectile.getY() < 0
//                    || projectile.getY() > configuration.getWindowHeight()) {
//                projectile.setActive(false);
//            }
//        }
    }

}
