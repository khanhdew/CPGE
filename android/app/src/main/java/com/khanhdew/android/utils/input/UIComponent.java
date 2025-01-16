package com.khanhdew.android.utils.input;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;

import com.khanhdew.android.main.GamePanel;
import com.khanhdew.gameengine.config.GameConfiguration;
import com.khanhdew.gameengine.entity.movable.player.Player;

public abstract class UIComponent {
    protected GamePanel gamePanel;
    protected GameConfiguration gameConfiguration = GameConfiguration.getInstance();
    protected Player player;
    protected float xCenter;
    protected float yCenter;
    protected float r;
    protected Paint paint;


    public UIComponent(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        this.player = gamePanel.getGameApp().getGameEngine().getPlayer();
        this.paint = new Paint();
    }

    public void draw(Canvas canvas){
        canvas.drawCircle(xCenter, yCenter, r, paint);
    }

    public void onTouch(MotionEvent event, Runnable action){
        float x = event.getX();
        float y = event.getY();
        float a = Math.abs(x - xCenter);
        float b = Math.abs(y - yCenter);
        float c = (float) Math.hypot(a, b);
        if (c <= r) {
            action.run();
        }
    }

    public boolean touchEvent(MotionEvent event){
        return false;
    };

}
