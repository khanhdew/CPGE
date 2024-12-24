package com.khanhdew.homealone.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public abstract class BaseEntity {
    protected int x;
    protected int y;
    protected int w;
    protected int h;
//    protected int speed;

    public abstract void update();
    // check va chạm
    public boolean intersects(BaseEntity other){
        return this.x < other.x + other.w &&
               this.x + this.w > other.x &&
               this.y < other.y + other.h &&
               this.y + this.h > other.y;
    }
    // di chuyển
    public void offset(int dx, int dy) {
        this.x += dx;
        this.y += dy;
    }
}
