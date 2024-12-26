package com.khanhdew.gameengine.entity.movable;

import com.khanhdew.gameengine.entity.gameitem.Weapon;
import com.khanhdew.gameengine.entity.movable.projectile.ProjectileManager;
import lombok.Getter;

@Getter
public class Character extends MovableEntity implements CharacterBehavior {
    private final ProjectileManager projectileManager;
    private Weapon weapon;

    public Character(double x, double y, double w, double h) {
        super(x, y, w, h);
        projectileManager = new ProjectileManager();
    }

    @Override
    public void shoot(double targetX, double targetY) {
        projectileManager.add(this.x,this.y,targetX,targetY);
    }
}
