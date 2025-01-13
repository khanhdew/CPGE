package com.khanhdew.android.utils.input;

import android.graphics.Color;
import android.view.MotionEvent;

import com.khanhdew.android.main.GamePanel;

public class Joystick extends UIComponent {

    private float touchX;
    private float touchY;
    private boolean touchDown;

    public Joystick() {
        super(170, 770, 150);
        paint.setColor(Color.GREEN);
    }

    public Joystick(GamePanel gamePanel) {
        super(gamePanel, 170, 770, 150);
        paint.setColor(Color.GREEN);
    }

    public boolean touchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_MOVE:
                touchDown = true;
                touchX = event.getX();
                touchY = event.getY();
                float x = event.getX();
                float y = event.getY();
                float a = Math.abs(x - xCenter);
                float b = Math.abs(y - yCenter);
                float c = (float) Math.hypot(a, b);
                float angle = (float) Math.toDegrees(Math.atan2(y - yCenter, x - xCenter));
                // calculate dx, dy
                float dx = (float) (20 * Math.cos(Math.toRadians(angle)));
                float dy = (float) (20 * Math.sin(Math.toRadians(angle)));
                onTouch(event, () -> {
                    player.translateXY(dx, dy);
                });
                break;
        }
        return true;

    }

}
