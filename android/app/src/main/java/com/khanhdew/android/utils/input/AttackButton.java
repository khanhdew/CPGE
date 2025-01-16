package com.khanhdew.android.utils.input;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;

import com.khanhdew.android.main.GamePanel;

public class AttackButton extends Joystick {
    private InnerCircle innerCircle;
    private float dx;
    private float dy;
    private boolean touchDown;

    public AttackButton(GamePanel gamePanel) {
        super(gamePanel);
        init();
    }

    private void init() {
        xCenter = 2100;
        yCenter = 800;
        r = 150;
        innerCircle = new InnerCircle(gamePanel, 200, 800, 80);
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        if (touchDown) {
//            innerCircle.draw(canvas);
            player.shoot(dx, dy);
        }
    }

    @Override
    public boolean touchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_MOVE:
                float x = event.getX();
                float y = event.getY();
                onTouch(event, () -> {
                    touchDown = true;
                    innerCircle.setX(x);
                    innerCircle.setY(y);
                });
                dx = (float) ((x - xCenter) * player.getX());
                dy = (float) ((y - yCenter) * player.getY());
                break;
            case MotionEvent.ACTION_UP:
                touchDown = false;
                innerCircle.setX(xCenter);
                innerCircle.setY(yCenter);
                break;
        }
        return true;
    }

}
