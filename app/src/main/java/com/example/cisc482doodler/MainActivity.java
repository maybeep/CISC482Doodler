package com.example.cisc482doodler;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.Toast;

import java.io.OutputStream;

public class MainActivity extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {

    private DoodleView doodleView;
    private ImageButton currentColor, strokeWeight, opacity, clear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        doodleView = findViewById(R.id.canvas);
        currentColor = findViewById(R.id.colorBlack);
        strokeWeight = findViewById(R.id.strokeWeight);
        opacity = findViewById(R.id.opacity);
        clear = findViewById(R.id.clear);
    }

    public void save(View v) {
        Bitmap doodle = doodleView.save();
        OutputStream imageOutStream = null;
        ContentValues cv = new ContentValues();
        cv.put(MediaStore.Images.Media.DISPLAY_NAME, "doodle.jpg");
        cv.put(MediaStore.Images.Media.MIME_TYPE, "image/jpg");
        cv.put(MediaStore.Images.Media.RELATIVE_PATH, Environment.DIRECTORY_PICTURES);
        Uri uri = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, cv);
        try {
            imageOutStream = getContentResolver().openOutputStream(uri);
            doodle.compress(Bitmap.CompressFormat.PNG, 100, imageOutStream);
            imageOutStream.close();
            Context context = getApplicationContext();
            CharSequence text = "Saving doodle to images...";
            int duration = Toast.LENGTH_LONG;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void clear(View v) {
        doodleView.clear();
    }

    public void changeColor(View v){
        String color = (String) v.getTag();
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
                doodleView.setOpacity(0xA);
                return true;
            case R.id.opacity75:
                doodleView.setOpacity(0x14);
                return true;
            case R.id.opacity100:
                doodleView.setOpacity(0xFF);
                return true;
            default:
                return false;
        }
    }
}