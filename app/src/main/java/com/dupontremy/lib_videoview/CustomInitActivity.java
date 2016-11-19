package com.dupontremy.lib_videoview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;

import com.dupontremy.libmaterialvideoview.MaterialVideoView;


public class CustomInitActivity extends AppCompatActivity {

    MaterialVideoView materialVideoView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_init);

        materialVideoView = (MaterialVideoView) findViewById(R.id.videoview);
        materialVideoView

                .thumbDrawable(Utils.getDensityDrawable("thumb.png", DisplayMetrics.DENSITY_XHIGH, this))
                .playDrawable(Utils.getDensityDrawable("play.png", DisplayMetrics.DENSITY_XHIGH, this))
                .pauseDrawable(Utils.getDensityDrawable("pause.png", DisplayMetrics.DENSITY_XHIGH, this))

                .setVideoBackgroundColor("#3F51B5")
                .setPlayerBackgroundColor("#000000")
                .setThumbColor("#ff0000")
                .setProgressFinishedColor("#FF0000")
                .setProgressUnfinishedColor("#ff8888")
                .setTimeColor("#ff0000")
                .setPlayPauseBtnColor("#ff0000")
                .create("video.mp4");
    }
}
