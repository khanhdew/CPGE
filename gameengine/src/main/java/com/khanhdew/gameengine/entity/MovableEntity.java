package com.khanhdew.gameengine.entity;

public class MovableEntity extends BaseEntity{
    protected float walkSpeed;
    protected int vX, vY;
    public MovableEntity(int x, int y, int w, int h) {
        super(x, y, w, h);
    }

    @Override
    public void update() {

    }
}
