package com.khanhdew.android.main.processor;

import android.annotation.SuppressLint;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.khanhdew.android.main.GamePanel;
import com.khanhdew.android.utils.Touch;
import com.khanhdew.android.utils.input.AttackButton;
import com.khanhdew.android.utils.input.Joystick;
import com.khanhdew.android.utils.input.UIComponent;
import com.khanhdew.gameengine.engine.GameEngine;
import com.khanhdew.gameengine.utils.InputHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AndroidInputHandler implements InputHandler, View.OnTouchListener {
    private GamePanel gamePanel;
    private Joystick joystick;
    private AttackButton attackButton;
    private final List<UIComponent> uiComponents;
    private final Map<Integer, TouchPoint> activePointers;

    private static final String TAG = "AndroidInputHandler";

    public AndroidInputHandler(GameEngine gameEngine, GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        uiComponents = new ArrayList<>();
        activePointers = new HashMap<>();
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public void handleInput() {
        gamePanel.setOnTouchListener(this);
    }

    @Override
    public void handleRelease() {
    }

    public void setGamePanel(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        this.joystick = new Joystick(gamePanel);
        this.attackButton = new AttackButton(gamePanel);
        uiComponents.addAll(List.of(joystick, attackButton));
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        final int action = event.getActionMasked();
        final int pointerIndex = event.getActionIndex();
        final int pointerId = event.getPointerId(pointerIndex);

        switch (action) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_POINTER_DOWN: {
                float xDown = event.getX(pointerIndex);
                float yDown = event.getY(pointerIndex);
                activePointers.put(pointerId, new TouchPoint(xDown, yDown));
                break;
            }

            case MotionEvent.ACTION_MOVE: {
                for (int i = 0; i < event.getPointerCount(); i++) {
                    int id = event.getPointerId(i);
                    float x = event.getX(i);
                    float y = event.getY(i);

                    TouchPoint touchPoint = activePointers.get(id);
                    if (touchPoint != null) {
                        touchPoint.x = x;
                        touchPoint.y = y;
                    } else {
                        activePointers.put(id, new TouchPoint(x, y));
                    }
                    for (UIComponent uiComponent : uiComponents) {
                        if (uiComponent.onTouch(x, y, id)) {
                            uiComponent.touchEvent(event, id);
                        }
                    }
                }
                break;
            }

            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_POINTER_UP:
            case MotionEvent.ACTION_CANCEL: {
                activePointers.remove(pointerId);
                for (UIComponent uiComponent : uiComponents) {
                    if (uiComponent.pointerId == pointerId) {
                        uiComponent.touchEvent(event, pointerId);
                        uiComponent.pointerId = -1;

                        // Kiểm tra các pointer còn lại có nằm trong component không
                        for (Map.Entry<Integer, TouchPoint> entry : activePointers.entrySet()) {
                            int activePointerId = entry.getKey();
                            TouchPoint touchPoint = entry.getValue();
                            if (uiComponent.onTouch(touchPoint.x, touchPoint.y, activePointerId)) {
                                uiComponent.pointerId = activePointerId;
                                // Cập nhật trạng thái component với pointer mới
                                MotionEvent fakeEvent = MotionEvent.obtain(
                                        event.getDownTime(),
                                        event.getEventTime(),
                                        MotionEvent.ACTION_MOVE,
                                        touchPoint.x,
                                        touchPoint.y,
                                        0
                                );
                                uiComponent.touchEvent(fakeEvent, activePointerId);
                                fakeEvent.recycle();
                            }
                        }
                    }
                }
                break;
            }
        }

        return true;
    }
    public List<UIComponent> getUiComponents() {
        return uiComponents;
    }

    public Joystick getJoystick() {
        return joystick;
    }

    private static class TouchPoint {
        float x, y;

        public TouchPoint(float x, float y) {
            this.x = x;
            this.y = y;
        }
    }
}