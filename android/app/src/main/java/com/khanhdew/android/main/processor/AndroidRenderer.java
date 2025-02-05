package com.khanhdew.android.main.processor;

import static android.content.ContentValues.TAG;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.Log;
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

    private static final String TAG = "AndroidRenderer";

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
            Log.e(TAG, "draw: ", e);
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
        drawEntity(canvas, player);
    }

    private void drawEntity(Canvas canvas, BaseEntity entity) {
        if (camera.isInView((float) entity.getX(), (float) entity.getY(), (float) entity.getW(), (float) entity.getH())) {
            RectF rect = new RectF((float) (entity.getX() - camera.getX()), (float) (entity.getY() - camera.getY()),
                    (float) (entity.getX() + entity.getW() - camera.getX()), (float) (entity.getY() + entity.getH() - camera.getY()));
            canvas.drawRect(rect, paint);
        }
    }


    public void drawEnemy(Canvas canvas) {
        paint.setColor(Color.RED);
        synchronized (gameEngine.getEnemyManager().getEnemies()) {
            for (BaseEntity enemy : gameEngine.getEnemyManager().getEnemies()) {
                if (enemy.isActive()) {
                    drawEntity(canvas, enemy);
                }
            }
        }
    }


    public void drawProjectile(Canvas canvas) {
        paint.setColor(Color.BLUE);
        synchronized (player.getProjectileManager().getProjectiles()) {
            for (BaseEntity projectile : player.getProjectileManager().getProjectiles()) {
                if (projectile.isActive()) {
                    drawEntity(canvas, projectile);
                }
            }
        }
    }


    public void showFPS(Canvas canvas) {
        if (GameConfiguration.getInstance().isSHOW_FPS()) {
            paint.setColor(Color.BLACK);
            paint.setTextSize(50);
            canvas.drawText("FPS: " + GameApp.fps, 50, 50, paint);
            canvas.drawText("Player x: " + player.getX(), 50, 100, paint);
            canvas.drawText("Player y: " + player.getY(), 50, 150, paint);
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