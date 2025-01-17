package com.khanhdew.gameengine.entity.movable.projectile;

import com.khanhdew.gameengine.entity.movable.MovableEntity;

public class Projectile extends MovableEntity {
    protected double damage;
    protected double range;

    public Projectile(double startX, double startY, double targetX, double targetY) {
        super(startX, startY, 10, 10);
        this.x = startX;
        this.y = startY;
        this.speed = 5;
        this.range = 500;
        reset(startX, startY, targetX, targetY);
    }

    public void reset(double startX, double startY, double targetX, double targetY) {
        this.x = startX;
        this.y = startY;
        // Tính toán hướng di chuyển
        double directionX = targetX - startX;
        double directionY = targetY - startY;

        double magnitude = Math.sqrt(directionX * directionX + directionY * directionY);

        // Chuẩn hóa vector hướng và nhân với tốc độ
        this.vX = (directionX / magnitude) * speed;
        this.vY = (directionY / magnitude) * speed;

        this.active = true;
        range = 500;
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
