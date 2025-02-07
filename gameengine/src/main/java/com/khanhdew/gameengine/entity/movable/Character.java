package com.khanhdew.gameengine.entity.movable;

import com.khanhdew.gameengine.entity.gameitem.Weapon;
import com.khanhdew.gameengine.entity.movable.projectile.ProjectileManager;
import lombok.Getter;

@Getter
public class Character extends MovableEntity implements CharacterBehavior {
    protected final ProjectileManager projectileManager;
    protected Weapon weapon;
    protected double health = 100;

    public Character(double x, double y, double w, double h) {
        super(x, y, w, h);
        projectileManager = new ProjectileManager();
    }

    @Override
    public void shoot(double targetX, double targetY) {
        projectileManager.add(x + w/2,y + h/2,targetX,targetY);
    }

    public void shoot(double angle){
        projectileManager.add(x + w/2, y + h/2, angle, 0);
    }

    public void takeDamage(double damage) {
        health -= damage;
        speed = 5;
    }
}
