package com.khanhdew.gameengine.entity;

import com.khanhdew.gameengine.config.GameConfiguration;
import com.khanhdew.gameengine.entity.movable.enemy.Enemy;
import com.khanhdew.gameengine.entity.movable.player.Player;
import com.khanhdew.gameengine.entity.movable.projectile.Projectile;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class BaseEntity {
    protected double x;
    protected double y;
    protected double w;
    protected double h;
    protected boolean active;
    protected GameConfiguration configuration = GameConfiguration.getInstance();

    public BaseEntity(double x, double y, double w, double h) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.active = true;
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
    public void translateXY(double dx, double dy) {
        this.x += dx;
        this.y += dy;
    }

    public void reset(double x, double y, double w, double h) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;

    }

    public static BaseEntity create(BaseEntity entity) {
        switch (entity.getClass().getSimpleName()) {
            case "Player":
                return new Player(entity.getX(), entity.getY(), entity.getW(), entity.getH());
            case "Enemy":
                return new Enemy(entity.getX(), entity.getY(), entity.getW(), entity.getH());
            case "Projectile":
                return new Projectile(entity.getX(), entity.getY(), 0);
            default:
                return null;
        }
    }

}
