package com.khanhdew.gameengine.entity.movable.projectile;

import com.khanhdew.gameengine.entity.movable.MovableEntity;

public class Projectile extends MovableEntity {
    protected double damage; 


    public Projectile(double startX, double startY, double targetX, double targetY) {
        super(startX, startY, 10, 10);
        this.x = startX;
        this.y = startY;
        this.speed = 2;
        setDirect(startX, startY, targetX, targetY);
    }

    public void setDirect(double startX, double startY, double targetX, double targetY) {
        this.x = startX;
        this.y = startY;
        // Tính toán hướng di chuyển
        double directionX = targetX - startX;
        double directionY = targetY - startY;

        double magnitude = Math.sqrt(directionX * directionX + directionY * directionY);

        // Chuẩn hóa vector hướng và nhân với tốc độ
        this.vX = (directionX / magnitude) * speed;
        this.vY = (directionY / magnitude) * speed;
    }


    @Override
    public void update() {
        if (isActive()) {
            this.x += this.vX;
            this.y += this.vY;
        }
    }


}
