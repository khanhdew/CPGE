package com.khanhdew.android;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.khanhdew.android.main.GamePanel;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_FULLSCREEN |
                        View.SYSTEM_UI_FLAG_HIDE_NAVIGATION |
                        View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
        );

        // Ẩn thanh action bar nếu có
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);

        // Chiều rộng và chiều cao màn hình (đơn vị: pixel)
        int screenWidth = metrics.widthPixels;
        int screenHeight = metrics.heightPixels;
        System.out.println("Screen width: " + screenWidth);
        System.out.println("Screen height: " + screenHeight);

        GamePanel gamePanel = new GamePanel(this, screenWidth, screenHeight);
        setContentView(gamePanel);
    }
}