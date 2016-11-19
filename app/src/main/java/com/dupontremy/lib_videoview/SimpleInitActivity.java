package com.dupontremy.lib_videoview;

import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.dupontremy.libmaterialvideoview.MaterialVideoView;
import com.dupontremy.libmaterialvideoview.ScalableType;

public class SimpleInitActivity extends AppCompatActivity {

    protected MaterialVideoView materialVideoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_init);

        materialVideoView = (MaterialVideoView) findViewById(R.id.videoview);
        materialVideoView.autoPlay(true).create("video.mp4");
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        // Checks the orientation of the screen
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            materialVideoView.setScalableType(ScalableType.FIT_CENTER);
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){
            materialVideoView.setScalableType(ScalableType.FIT_CENTER);
        }
    }
}
