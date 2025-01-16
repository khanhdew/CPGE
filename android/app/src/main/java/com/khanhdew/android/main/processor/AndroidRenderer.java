package com.khanhdew.android.main.processor;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.SurfaceHolder;

import androidx.annotation.NonNull;

import com.khanhdew.android.utils.input.UIComponent;
import com.khanhdew.gameengine.config.GameConfiguration;
import com.khanhdew.gameengine.engine.GameApp;
import com.khanhdew.gameengine.engine.GameEngine;
import com.khanhdew.gameengine.engine.GameRenderer;
import com.khanhdew.gameengine.entity.BaseEntity;
import com.khanhdew.gameengine.entity.movable.player.Player;
import com.khanhdew.gameengine.utils.Camera;

import java.util.List;

public class AndroidRenderer implements GameRenderer, SurfaceHolder.Callback {
    private final GameEngine gameEngine;
    private final Player player;
    private final Paint paint;
    private final SurfaceHolder holder;

    private final Camera camera;
    private final List<UIComponent> uiComponents;

    public AndroidRenderer(GameEngine gameEngine, SurfaceHolder holder, List<UIComponent> uiComponents) {
        this.gameEngine = gameEngine;
        this.player = gameEngine.getPlayer();
        this.camera = new Camera(player);
        this.uiComponents = uiComponents;
        this.paint = new Paint();
        this.holder = holder;
        this.holder.addCallback(this);
    }

    @Override
    public void draw() {
        Canvas canvas = null;
        try {
            canvas = holder.lockCanvas();
            if (canvas != null) {
                canvas.drawColor(Color.WHITE);
                camera.update();
                drawEnemy(canvas);
                drawPlayer(canvas);
                drawProjectile(canvas);
                drawUIComponents(canvas);
                showFPS(canvas);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (canvas != null) {
                holder.unlockCanvasAndPost(canvas);
            }
        }
    }

    private void drawUIComponents(Canvas canvas) {
        for (UIComponent uiComponent : uiComponents) {
            uiComponent.draw(canvas);
        }
    }


    public void drawPlayer(Canvas canvas) {
        paint.setColor(Color.BLACK);
        canvas.drawRect(new RectF(
                (float) (player.getX() - camera.getX()),
                (float) (player.getY() - camera.getY()),
                (float) ((player.getX() + player.getW()) - camera.getX()),
                (float) ((player.getY() + player.getH()) - camera.getY())), paint);
    }


    public void drawEnemy(Canvas canvas) {
        paint.setColor(Color.RED);
        synchronized (gameEngine.getEnemyManager().getEnemies()) {
            for (BaseEntity enemy : gameEngine.getEnemyManager().getEnemies()) {
                if (enemy.isActive()) {
                    canvas.drawRect(new RectF(
                            (float) (enemy.getX() - camera.getX()),
                            (float) (enemy.getY() - camera.getY()),
                            (float) ((enemy.getX() + enemy.getW()) - camera.getX()),
                            (float) ((enemy.getY() + enemy.getH()) - camera.getY())), paint);
                }
            }
        }
    }


    public void drawProjectile(Canvas canvas) {
        paint.setColor(Color.BLUE);
        synchronized (player.getProjectileManager().getProjectiles()) {
            for (BaseEntity projectile : player.getProjectileManager().getProjectiles()) {
                if (projectile.isActive()) {
                    canvas.drawRect(new RectF(
                            (float)( projectile.getX() - camera.getX()),
                            (float) (projectile.getY() - camera.getY()),
                            (float) ((projectile.getX() + projectile.getW()) - camera.getX()),
                            (float) ((projectile.getY() + projectile.getH()) - camera.getY())), paint);
                }
            }
        }
    }


    public void showFPS(Canvas canvas) {
        if (GameConfiguration.getInstance().isSHOW_FPS()) {
            paint.setColor(Color.BLACK);
            paint.setTextSize(50);
            canvas.drawText("FPS: " + GameApp.fps, 50, 50, paint);
        }
    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder holder) {

    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder holder) {

    }
}