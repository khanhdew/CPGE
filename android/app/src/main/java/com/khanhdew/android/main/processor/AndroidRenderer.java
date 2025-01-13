package com.khanhdew.android.main.processor;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.SurfaceHolder;

import androidx.annotation.NonNull;

import com.khanhdew.android.utils.input.AttackButton;
import com.khanhdew.android.utils.input.Joystick;
import com.khanhdew.android.utils.input.UIRender;
import com.khanhdew.gameengine.config.GameConfiguration;
import com.khanhdew.gameengine.engine.GameApp;
import com.khanhdew.gameengine.engine.GameEngine;
import com.khanhdew.gameengine.engine.GameRenderer;
import com.khanhdew.gameengine.entity.BaseEntity;
import com.khanhdew.gameengine.entity.movable.player.Player;

import java.util.List;

public class AndroidRenderer implements GameRenderer , SurfaceHolder.Callback{
    private final GameEngine gameEngine;
    private final Player player;
    private final Paint paint;
    private final SurfaceHolder holder;

    private final List<UIRender> uiComponents;

    public AndroidRenderer(GameEngine gameEngine, SurfaceHolder holder) {
        this.gameEngine = gameEngine;
        this.player = gameEngine.getPlayer();
        this.paint = new Paint();
        this.holder = holder;
        this.holder.addCallback(this);
        uiComponents = List.of(
                new Joystick(),
                new AttackButton()
        );
    }

    @Override
    public void draw() {
        Canvas canvas = null;
        try {
            canvas = holder.lockCanvas();
            if (canvas != null) {
                canvas.drawColor(Color.WHITE);
                drawPlayer(canvas);
                drawEnemy(canvas);
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
        for (UIRender uiComponent : uiComponents) {
            uiComponent.draw(canvas);
        }
    }


    public void drawPlayer(Canvas canvas) {
        paint.setColor(Color.BLACK);
        canvas.drawRect(new RectF((float) player.getX(), (float) player.getY(),
                (float) (player.getX() + player.getW()), (float) (player.getY() + player.getH())), paint);
    }


    public void drawEnemy(Canvas canvas) {
        paint.setColor(Color.RED);
        synchronized (gameEngine.getEnemyManager().getEnemies()) {
            for (BaseEntity enemy : gameEngine.getEnemyManager().getEnemies()) {
                if (enemy.isActive()) {
                    canvas.drawRect(new RectF((float) enemy.getX(), (float) enemy.getY(),
                            (float) (enemy.getX() + enemy.getW()), (float) (enemy.getY() + enemy.getH())), paint);
                }
            }
        }
    }


    public void drawProjectile(Canvas canvas) {
        paint.setColor(Color.BLUE);
        synchronized (player.getProjectileManager().getProjectiles()) {
            for (BaseEntity projectile : player.getProjectileManager().getProjectiles()) {
                if (projectile.isActive()) {
                    canvas.drawRect(new RectF((float) projectile.getX(), (float) projectile.getY(),
                            (float) (projectile.getX() + projectile.getW()), (float) (projectile.getY() + projectile.getH())), paint);
                }
            }
        }
    }


    public void showFPS(Canvas canvas) {
        if (GameConfiguration.getInstance().isSHOW_FPS()) {
            paint.setColor(Color.BLACK);
            paint.setTextSize(50);
            canvas.drawText("FPS: " + GameApp.fps, 0, 50, paint);
//            canvas.drawLine(0, 100, GameConfiguration.getInstance().getWindowWidth(), 100, paint);
//            canvas.drawLine(100,0,100,GameConfiguration.getInstance().getWindowHeight(),paint);
//            canvas.drawLine(GameConfiguration.getInstance().getWindowWidth()-100,0,GameConfiguration.getInstance().getWindowWidth()-100,GameConfiguration.getInstance().getWindowHeight(),paint);
            canvas.drawLine(0,GameConfiguration.getInstance().getWindowHeight(),GameConfiguration.getInstance().getWindowWidth(),GameConfiguration.getInstance().getWindowHeight(),paint);
//            canvas.drawRect(0,0,GameConfiguration.getInstance().getWindowWidth(),GameConfiguration.getInstance().getWindowHeight(),paint);
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