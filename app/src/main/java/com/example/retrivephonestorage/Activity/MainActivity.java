package com.example.retrivephonestorage.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.retrivephonestorage.R;

public class MainActivity extends AppCompatActivity {

    private ImageView xImage;
    private ImageView xMusic;
    private ImageView xVideo;
    private ImageView xDocument;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        xDocument = findViewById(R.id.xDocument);
        xDocument.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, DocumentFileActivity.class));
            }
        });

        xImage = findViewById(R.id.xImage);
        xImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ImageActivity.class));
            }
        });


        xMusic = findViewById(R.id.xMusic);
        xMusic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, MusicActivity.class));
            }
        });

        xVideo = findViewById(R.id.xVideo);
        xVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, VideoActivity.class));
            }
        });

    }
}