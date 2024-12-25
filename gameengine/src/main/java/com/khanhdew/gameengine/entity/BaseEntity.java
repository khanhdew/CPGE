package com.khanhdew.gameengine.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class BaseEntity {
    protected int x;
    protected int y;
    protected int w;
    protected int h;
    protected float walkSpeed;

    public BaseEntity(int x, int y, int w, int h) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }

    public abstract void update();

    // check va chạm
    public boolean intersects(BaseEntity other) {
        return this.x < other.x + other.w &&
                this.x + this.w > other.x &&
                this.y < other.y + other.h &&
                this.y + this.h > other.y;
    }

    // di chuyển
    public void translateXY(int dx, int dy) {
        this.x += dx;
        this.y += dy;
    }
}
