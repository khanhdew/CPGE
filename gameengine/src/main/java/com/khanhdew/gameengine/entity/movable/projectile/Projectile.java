package com.khanhdew.gameengine.entity.movable.projectile;

import com.khanhdew.gameengine.entity.movable.MovableEntity;

import lombok.Getter;

@Getter
public class Projectile extends MovableEntity {
    protected double damage = 100;
    protected double range = 1000;

    public enum Type {
        NORMAL

    }

    public Projectile() {
        super(0, 0, 0, 0);
    }

    public Projectile(double startX, double startY, double angle) {
        super(startX, startY, 10, 10);
        this.x = startX;
        this.y = startY;
        this.speed = 5;
        reset(startX, startY, angle);
    }

    public void reset(double startX, double startY, double angle) {
        this.x = startX;
        this.y = startY;
        // Tính toán hướng di chuyển
//        double directionX = targetX - startX;
//        double directionY = targetY - startY;
//
//        double magnitude = Math.sqrt(directionX * directionX + directionY * directionY);
//
//        // Chuẩn hóa vector hướng và nhân với tốc độ
//        this.vX = (directionX / magnitude) * speed;
//        this.vY = (directionY / magnitude) * speed;

        this.vX = Math.cos(angle) * speed;
        this.vY = Math.sin(angle) * speed;

        this.active = true;
        this.range = 1000;
    }


    @Override
    public void update() {
        if (isActive() && range > 0) {
            this.x += this.vX;
            this.y += this.vY;
            range -= speed;
        } else {
            setActive(false);
        }
    }

}
