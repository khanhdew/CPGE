package com.khanhdew.gameengine.entity.movable;

import com.khanhdew.gameengine.entity.BaseEntity;

public abstract class MovableEntity extends BaseEntity {
    protected double speed = 1;
    protected double vX, vY;

    public MovableEntity(double x, double y, double w, double h) {
        super(x, y, w, h);
    }

    @Override
    public void update() {
    }
}
