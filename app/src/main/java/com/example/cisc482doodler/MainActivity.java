package com.example.cisc482doodler;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.PopupMenu;

public class MainActivity extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {

    private DoodleView doodleView;
    private ImageButton currentColor, strokeWeight, opacity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        doodleView = findViewById(R.id.canvas);
        currentColor = findViewById(R.id.colorBlack);
        strokeWeight = findViewById(R.id.strokeWeight);
        opacity = findViewById(R.id.opacity);
    }

    public void clear(View v) {
        doodleView.clear();
    }

    public void changeColor(View v){
        String color = v.getTag().toString();
        doodleView.setColor(color);
    }

    public void changeStrokeWeight(View v){
        PopupMenu popupMenu = new PopupMenu(this, v);
        popupMenu.setOnMenuItemClickListener(this);
        popupMenu.inflate(R.menu.stroke_menu);
        popupMenu.show();
    }

    public void changeOpacity(View v){
        PopupMenu popupMenu = new PopupMenu(this, v);
        popupMenu.setOnMenuItemClickListener(this);
        popupMenu.inflate(R.menu.opacity_menu);
        popupMenu.show();
    }

    public boolean onMenuItemClick(MenuItem item){
        switch (item.getItemId()) {
            case R.id.small:
                doodleView.setStrokeWeight(5);
                return true;
            case R.id.medium:
                doodleView.setStrokeWeight(50);
                return true;
            case R.id.large:
                doodleView.setStrokeWeight(100);
                return true;
            case R.id.opacity50:
                doodleView.setOpacity(128);
                return true;
            case R.id.opacity75:
                doodleView.setOpacity(191);
                return true;
            case R.id.opacity100:
                doodleView.setOpacity(255);
                return true;
            default:
                return false;
        }
    }
}