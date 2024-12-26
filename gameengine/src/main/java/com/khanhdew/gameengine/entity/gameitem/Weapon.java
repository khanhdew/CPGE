package com.khanhdew.gameengine.entity.gameitem;

import com.khanhdew.gameengine.entity.BaseEntity;

public abstract class Weapon extends BaseEntity {
    protected double cooldown = 0;
    protected double firingRate = 0.5;
    protected double range = 0;

    public Weapon(double x, double y, double w, double h) {
        super(x, y, w, h);
    }

    @Override
    public void update() {
        if (cooldown > 0) {
            cooldown -= System.nanoTime();
        }
    }
}
