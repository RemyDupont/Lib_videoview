package com.dupontremy.lib_videoview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.dupontremy.libmaterialvideoview.MaterialVideoView;

public class SimpleInitActivity extends AppCompatActivity {

    protected MaterialVideoView materialVideoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_init);

        materialVideoView = (MaterialVideoView) findViewById(R.id.videoview);
        materialVideoView.autoPlay(true).create("video.mp4");
    }
}
