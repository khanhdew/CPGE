package com.khanhdew.android.utils.input;

import android.graphics.Canvas;
import android.view.MotionEvent;

import com.khanhdew.android.main.GamePanel;

public class AttackButton extends Joystick {
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
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        if (touchDown) {
            innerCircle.draw(canvas);
            player.shoot(calAngle());
        }
    }

    @Override
    public boolean touchEvent(MotionEvent event, int pointerId) {
        switch (event.getActionMasked()) {
            case MotionEvent.ACTION_MOVE: {
                float x = 0;
                float y = 0;
                for (int i = 0; i < event.getPointerCount(); i++) {
                    int currentPointerId = event.getPointerId(i);
                    if (currentPointerId == this.pointerId) {
                        x = event.getX(i);
                        y = event.getY(i);
                        break;
                    }
                }
                touchDown = true;
                dx = x - xCenter;
                dy = y - yCenter;
                innerCircle.updatePosition(x,y, xCenter, yCenter,r);
                break;
            }
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_POINTER_UP:
                if(this.pointerId == pointerId){
                    touchDown = false;
                    dx = 0;
                    dy = 0;
                    innerCircle.setX(xCenter);
                    innerCircle.setY(yCenter);
                    this.pointerId = -1;
                }
                break;
        }
        return true;
    }

    public double calAngle() {
        // Bắn đạn theo góc
        return (float) Math.atan2(dy, dx);
    }

}
