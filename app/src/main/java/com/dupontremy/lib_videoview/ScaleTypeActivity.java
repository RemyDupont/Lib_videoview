package com.dupontremy.lib_videoview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.dupontremy.libmaterialvideoview.MaterialVideoView;
import com.dupontremy.libmaterialvideoview.ScalableType;

public class ScaleTypeActivity extends AppCompatActivity implements View.OnClickListener{

    MaterialVideoView materialVideoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scale_type);

        materialVideoView = (MaterialVideoView) findViewById(R.id.videoview);
        materialVideoView.create("video.mp4");

        Button fitXY = (Button) findViewById(R.id.fitXY);
        Button fitStart = (Button) findViewById(R.id.fitStart);
        Button fitCenter = (Button) findViewById(R.id.fitCenter);
        Button fitEnd = (Button) findViewById(R.id.fitEnd);

        Button leftTop = (Button) findViewById(R.id.leftTop);
        Button leftCenter = (Button) findViewById(R.id.leftCenter);
        Button leftBottom = (Button) findViewById(R.id.leftBottom);
        Button centerTop = (Button) findViewById(R.id.centerTop);
        Button center = (Button) findViewById(R.id.center);
        Button centerBottom = (Button) findViewById(R.id.centerBottom);
        Button rightTop = (Button) findViewById(R.id.rightTop);
        Button rightCenter = (Button) findViewById(R.id.rightCenter);
        Button rightBottom = (Button) findViewById(R.id.rightBottom);

        Button leftTopCrop = (Button) findViewById(R.id.leftTopCrop);
        Button leftCenterCrop = (Button) findViewById(R.id.leftCenterCrop);
        Button leftBottomCrop = (Button) findViewById(R.id.leftBottomCrop);
        Button centerTopCrop = (Button) findViewById(R.id.centerTopCrop);
        Button centerCrop = (Button) findViewById(R.id.centerCrop);
        Button centerBottomCrop = (Button) findViewById(R.id.centerBottomCrop);
        Button rightTopCrop = (Button) findViewById(R.id.rightTopCrop);
        Button rightCenterCrop = (Button) findViewById(R.id.rightCenterCrop);
        Button rightBottomCrop = (Button) findViewById(R.id.rightBottomCrop);

        Button startInside = (Button) findViewById(R.id.startInside);
        Button centerInside = (Button) findViewById(R.id.centerInside);
        Button endInside = (Button) findViewById(R.id.endInside);

        fitXY.setOnClickListener(this);
        fitStart.setOnClickListener(this);
        fitCenter.setOnClickListener(this);
        fitEnd.setOnClickListener(this);

        leftTop.setOnClickListener(this);
        leftCenter.setOnClickListener(this);
        leftBottom.setOnClickListener(this);
        centerTop.setOnClickListener(this);
        center.setOnClickListener(this);
        centerBottom.setOnClickListener(this);
        rightTop.setOnClickListener(this);
        rightCenter.setOnClickListener(this);
        rightBottom.setOnClickListener(this);

        leftTopCrop.setOnClickListener(this);
        leftCenterCrop.setOnClickListener(this);
        leftBottomCrop.setOnClickListener(this);
        centerTopCrop.setOnClickListener(this);
        centerCrop.setOnClickListener(this);
        centerBottomCrop.setOnClickListener(this);
        rightTopCrop.setOnClickListener(this);
        rightCenterCrop.setOnClickListener(this);
        rightBottomCrop.setOnClickListener(this);

        startInside.setOnClickListener(this);
        centerInside.setOnClickListener(this);
        endInside.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fitXY:
                materialVideoView.setScalableType(ScalableType.FIT_XY);
                break;
            case R.id.fitStart:
                materialVideoView.setScalableType(ScalableType.FIT_START);
                break;
            case R.id.fitCenter:
                materialVideoView.setScalableType(ScalableType.FIT_CENTER);
                break;
            case R.id.fitEnd:
                materialVideoView.setScalableType(ScalableType.FIT_END);
                break;
            case R.id.leftTop:
                materialVideoView.setScalableType(ScalableType.LEFT_TOP);
                break;
            case R.id.leftCenter:
                materialVideoView.setScalableType(ScalableType.LEFT_CENTER);
                break;
            case R.id.leftBottom:
                materialVideoView.setScalableType(ScalableType.LEFT_BOTTOM);
                break;
            case R.id.centerTop:
                materialVideoView.setScalableType(ScalableType.CENTER_TOP);
                break;
            case R.id.center:
                materialVideoView.setScalableType(ScalableType.CENTER);
                break;
            case R.id.centerBottom:
                materialVideoView.setScalableType(ScalableType.CENTER_BOTTOM);
                break;
            case R.id.rightTop:
                materialVideoView.setScalableType(ScalableType.RIGHT_TOP);
                break;
            case R.id.rightCenter:
                materialVideoView.setScalableType(ScalableType.RIGHT_CENTER);
                break;
            case R.id.rightBottom:
                materialVideoView.setScalableType(ScalableType.RIGHT_BOTTOM);
                break;
            case R.id.leftTopCrop:
                materialVideoView.setScalableType(ScalableType.LEFT_TOP_CROP);
                break;
            case R.id.leftCenterCrop:
                materialVideoView.setScalableType(ScalableType.LEFT_CENTER_CROP);
                break;
            case R.id.leftBottomCrop:
                materialVideoView.setScalableType(ScalableType.LEFT_BOTTOM_CROP);
                break;
            case R.id.centerTopCrop:
                materialVideoView.setScalableType(ScalableType.CENTER_TOP_CROP);
                break;
            case R.id.centerCrop:
                materialVideoView.setScalableType(ScalableType.CENTER_CROP);
                break;
            case R.id.centerBottomCrop:
                materialVideoView.setScalableType(ScalableType.CENTER_BOTTOM_CROP);
                break;
            case R.id.rightTopCrop:
                materialVideoView.setScalableType(ScalableType.RIGHT_TOP_CROP);
                break;
            case R.id.rightCenterCrop:
                materialVideoView.setScalableType(ScalableType.RIGHT_CENTER_CROP);
                break;
            case R.id.rightBottomCrop:
                materialVideoView.setScalableType(ScalableType.RIGHT_BOTTOM_CROP);
                break;
        }
    }
}
