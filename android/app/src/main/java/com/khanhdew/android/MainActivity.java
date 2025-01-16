package com.khanhdew.android;

import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

import com.khanhdew.android.main.GamePanel;
import com.khanhdew.gameengine.config.GameConfiguration;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        hideSystemUI();

        // Ẩn thanh action bar nếu có
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);

        // Chiều rộng và chiều cao màn hình (đơn vị: pixel)
        int screenWidth = metrics.widthPixels;
        int screenHeight = metrics.heightPixels;

        GameConfiguration gameConfiguration = new GameConfiguration(screenWidth, screenHeight);
        GamePanel gamePanel = new GamePanel(this);
        setContentView(gamePanel);
    }

    public void hideSystemUI() {
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        getWindow().getAttributes().layoutInDisplayCutoutMode = WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES;
    }
}