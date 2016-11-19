package com.dupontremy.lib_videoview;

import android.content.res.AssetFileDescriptor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.dupontremy.libmaterialvideoview.MaterialVideoView;

public class CommonMethodsActivity extends AppCompatActivity implements View.OnClickListener {

    protected MaterialVideoView materialVideoView;
    protected Button play, pause;
    protected Button visibilityDefault, visibilityVisible, visibilityGone;
    protected Button oneSecond, threeSeconds, fiveSeconds;
    protected Button setProgress5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_common_methods);

        materialVideoView = (MaterialVideoView) findViewById(R.id.smavideoview);
        AssetFileDescriptor afd = Utils.getAssetFileDescriptor("video.mp4", this);
        materialVideoView
                .autoPlay(true)
                .create(afd);

        play = (Button) findViewById(R.id.play);
        play.setOnClickListener(this);

        pause = (Button) findViewById(R.id.pause);
        pause.setOnClickListener(this);

        visibilityDefault = (Button) findViewById(R.id.visibilityDefault);
        visibilityDefault.setOnClickListener(this);

        visibilityVisible = (Button) findViewById(R.id.visibilityVisible);
        visibilityVisible.setOnClickListener(this);

        visibilityGone = (Button) findViewById(R.id.visibilityGone);
        visibilityGone.setOnClickListener(this);

        oneSecond = (Button) findViewById(R.id.oneSecond);
        oneSecond.setOnClickListener(this);

        threeSeconds = (Button) findViewById(R.id.threeSeconds);
        threeSeconds.setOnClickListener(this);

        fiveSeconds = (Button) findViewById(R.id.fiveSeconds);
        fiveSeconds.setOnClickListener(this);

        setProgress5 = (Button) findViewById(R.id.setProgress5);
        setProgress5.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.play:
                materialVideoView.play();
                break;
            case R.id.pause:
                materialVideoView.pause();
                break;
            case R.id.visibilityDefault:
                materialVideoView.setPlayerVisibility(0);
                break;
            case R.id.visibilityVisible:
                materialVideoView.setPlayerVisibility(1);
                break;
            case R.id.visibilityGone:
                materialVideoView.setPlayerVisibility(2);
                break;
            case R.id.oneSecond:
                materialVideoView.setVisibleTime(1000);
                break;
            case R.id.threeSeconds:
                materialVideoView.setVisibleTime(3000);
                break;
            case R.id.fiveSeconds:
                materialVideoView.setVisibleTime(5000);
                break;
            case R.id.setProgress5:
                materialVideoView.setProgress(5000);
                break;
        }
    }
}
