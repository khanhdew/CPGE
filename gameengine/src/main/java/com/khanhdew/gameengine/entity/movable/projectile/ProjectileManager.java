package com.khanhdew.gameengine.entity.movable.projectile;

import com.khanhdew.gameengine.config.GameConfiguration;
import com.khanhdew.gameengine.entity.EntityManager;
import com.khanhdew.gameengine.entity.ObjectPool;

import lombok.Getter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Getter
public class ProjectileManager implements EntityManager {

    private final ObjectPool<Projectile> projectilePool = new ObjectPool<>() {
        @Override
        protected Projectile create() {
            return new Projectile(0, 0, 0);
        }
    };
    private final List<Projectile> entities;
    private final GameConfiguration configuration = GameConfiguration.getInstance();

    public ProjectileManager() {
        this.entities = Collections.synchronizedList(new ArrayList<>());
    }

    @Override
    public void update() {
        entities.forEach(Projectile::update);
        remove();
    }

    @Override
    public void add(double playerX, double playerY, double angle, double nothing) {
        Optional<Projectile> inactiveProjectile = entities.stream()
                .filter(e -> !e.isActive())
                .findFirst();

        if (inactiveProjectile.isPresent()) {
            Projectile e = inactiveProjectile.get();
            e.setActive(true);
            e.reset(playerX, playerY, angle);
        } else {
            Projectile projectile = projectilePool.borrow();
            projectile.reset(playerX, playerY, angle);
            entities.add(projectile);
        }
    }

    @Override
    public void remove() {
        Optional<Projectile> inactiveProjectile = entities.stream()
                .filter(e -> !e.isActive())
                .findFirst();
        if (inactiveProjectile.isPresent()) {
            Projectile e = inactiveProjectile.get();
            entities.remove(e);
            projectilePool.returnToPool(e);
        }
    }

}
