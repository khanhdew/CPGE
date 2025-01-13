package com.khanhdew.android.utils.input;

import android.graphics.Color;
import android.view.MotionEvent;

import com.khanhdew.android.main.GamePanel;

public class AttackButton extends UIComponent{

    public AttackButton() {
        super(2100, 800, 120);
        paint.setColor(Color.GREEN);
    }
    public AttackButton(GamePanel gamePanel) {
        super(gamePanel, 2100, 800, 120);
        paint.setColor(Color.GREEN);
    }

    @Override
    public boolean touchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_MOVE:
                float x = event.getX();
                float y = event.getY();
                float dx =(float) ((x - xCenter) * player.getX());
                float dy =(float) ((y - yCenter) * player.getY());
                onTouch(event, () -> {
                    player.shoot(dx , dy);
                });
                break;
        }
        return true;
    }

}
