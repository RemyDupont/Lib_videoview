package com.dupontremy.libmaterialvideoview;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.media.MediaPlayer;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.util.Formatter;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;


/**
 * Created by rdupont on 19/09/2016.
 */

public class MaterialVideoView extends FrameLayout {

    // View elements
    protected View view;
    protected Context context;
    protected ScalableVideoView scalableVideoView;
    protected LinearLayout bottomLayout;
    protected ImageView btnPlayPause;
    protected TextView currentTime, totalTime;
    protected SeekBar progressBar;

    protected boolean autoPlay = false;
    protected int isPlayerVisible = 0 ;

    // Drawables
    protected Drawable thumb;
    protected Drawable btnPlay;
    protected Drawable btnPause;

    // Colors
    private int colorBackgroundVideo = Color.parseColor("#000000");
    private int colorBackgroundPlayer = Color.parseColor("#555555");
    private int colorThumb = Color.parseColor("#FFFFFF");
    private int colorProgressFinished = Color.parseColor("#FFFFFF");
    private int colorProgressUnfinished = Color.parseColor("#999999");
    private int colorTime = Color.parseColor("#FFFFFF");
    private int colorButtonPlayPause = Color.parseColor("#FFFFFF");

    // Timer
    protected ProgressTimerTask progressTimerTask;
    protected static Timer timer;
    protected android.os.Handler handler;
    protected int timerLength = 500;
    protected boolean isControlVisible = true;
    protected int milliSeconds = 0;
    protected int nbMilliSecondsVisible = 5000;

    /**
    Constructors
     */
    public MaterialVideoView(Context context) {
        super(context);
        this.context = context;
    }

    public MaterialVideoView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }

    public MaterialVideoView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
    }

    /**
    Public Methods init
     */

    // Drawables
    public MaterialVideoView thumbDrawable(Drawable thumb) {
        this.thumb = thumb;
        return this;
    }

    public MaterialVideoView playDrawable(Drawable play) {
        this.btnPlay = play;
        return this;
    }

    public MaterialVideoView pauseDrawable(Drawable pause) {
        this.btnPause = pause;
        return this;
    }

    // Colors int
    public MaterialVideoView setVideoBackgroundColor(int color) {
        this.colorBackgroundVideo = color;
        return this;
    }
    public MaterialVideoView setPlayerBackgroundColor(int color) {
        this.colorBackgroundPlayer = color;
        return this;
    }
    public MaterialVideoView setThumbColor(int color) {
        this.colorThumb = color;
        return this;
    }
    public MaterialVideoView setProgressFinishedColor(int color) {
        this.colorProgressFinished = color;
        return this;
    }
    public MaterialVideoView setProgressUnfinishedColor(int color) {
        this.colorProgressUnfinished = color;
        return this;
    }
    public MaterialVideoView setTimeColor(int color) {
        this.colorTime = color;
        return this;
    }
    public MaterialVideoView setPlayPauseBtnColor(int color) {
        this.colorButtonPlayPause = color;
        return this;
    }

    // Colors String
    public MaterialVideoView setVideoBackgroundColor(String color) {
        if(color.startsWith("#"))
            this.colorBackgroundVideo = Color.parseColor(color);
        return this;
    }
    public MaterialVideoView setPlayerBackgroundColor(String color) {
        if(color.startsWith("#"))
            this.colorBackgroundPlayer = Color.parseColor(color);
        return this;
    }
    public MaterialVideoView setThumbColor(String color) {
        if(color.startsWith("#"))
            this.colorThumb = Color.parseColor(color);
        return this;
    }
    public MaterialVideoView setProgressFinishedColor(String color) {
        if(color.startsWith("#"))
            this.colorProgressFinished = Color.parseColor(color);
        return this;
    }
    public MaterialVideoView setProgressUnfinishedColor(String color) {
        if(color.startsWith("#"))
            this.colorProgressUnfinished = Color.parseColor(color);
        return this;
    }
    public MaterialVideoView setTimeColor(String color) {
        if(color.startsWith("#"))
          this.colorTime = Color.parseColor(color);
        return this;
    }
    public MaterialVideoView setPlayPauseBtnColor(String color) {
        if(color.startsWith("#"))
         this.colorButtonPlayPause = Color.parseColor(color);
        return this;
    }

    // Others
    public MaterialVideoView setPlayerVisibility(int isPlayerVisible) {
        this.isPlayerVisible =  isPlayerVisible;
        if(bottomLayout != null) {
            switch (isPlayerVisible) {
                case 0:
                    isControlVisible = true;
                    bottomLayout.setVisibility(VISIBLE);
                    milliSeconds = 0;
                    break;
                case 1:
                    bottomLayout.setVisibility(VISIBLE);
                    break;
                case 2:
                    bottomLayout.setVisibility(GONE);
            }
        }
        return this;
    }
    public MaterialVideoView setVisibleTime(int milliSeconds) {
        this.nbMilliSecondsVisible = milliSeconds;
        return this;
    }
    public MaterialVideoView autoPlay(boolean autoPlay) {
        this.autoPlay = autoPlay;
        return this;
    }

    // Init file
    public void create(String fileName) {
        init();
        initDesign();
        try {
            scalableVideoView.setAssetData(fileName);
            scalableVideoView.setVolume(1.0f, 1.0f);
            scalableVideoView.setLooping(false);
            scalableVideoView.prepareAsync(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    totalTime.setText(stringForTime(scalableVideoView.getDuration()));
                    progressBar.setMax(scalableVideoView.getDuration());
                    if (autoPlay) {
                        scalableVideoView.start();
                        togglePlayPause();
                        milliSeconds = 0;
                    }
                }
            });

            startProgressTimer();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void create(AssetFileDescriptor assetFileDescriptor) {
        init();
        initDesign();
        try {
            scalableVideoView.setDataSource(assetFileDescriptor);
            scalableVideoView.setVolume(0, 0);
            scalableVideoView.setLooping(false);
            scalableVideoView.prepareAsync(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    totalTime.setText(stringForTime(scalableVideoView.getDuration()));
                    progressBar.setMax(scalableVideoView.getDuration());
                    if (autoPlay) {
                        scalableVideoView.start();
                        togglePlayPause();
                        milliSeconds = 0;
                    }
                }
            });

            startProgressTimer();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     Public Methods
     */
    public boolean isPlaying() {
        return scalableVideoView.isPlaying();
    }

    public void play() {
        if(!isPlaying()) {
            scalableVideoView.start();
            togglePlayPause();
            milliSeconds = 0;
        }
    }

    public void pause() {
        if (isPlaying()) {
            scalableVideoView.pause();
            togglePlayPause();
        }
    }

    public  void setProgressVisible(boolean visible) {
        if (visible)
            bottomLayout.setVisibility(VISIBLE);
        else
            bottomLayout.setVisibility(GONE);
    }

    public void setProgress(int time) {
        scalableVideoView.seekTo(time);
    }

    public int getCurrentTime() {
        return scalableVideoView.getCurrentPosition();
    }

    public void setScalableType(ScalableType scalableType) {
        scalableVideoView.setScalableType(scalableType);
    }

    /**
    Protected Methods
     */
    protected void init() {
        view = View.inflate(context, R.layout.material_video_view, this);
        scalableVideoView = (ScalableVideoView) view.findViewById(R.id.videoController);

        scalableVideoView.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                if(!isControlVisible && isPlayerVisible == 0) {
                    isControlVisible = true;
                    milliSeconds = 0;
                    bottomLayout.setVisibility(View.VISIBLE);
                }
                else {
                    playPause();
                }
            }
        });

        handler = new android.os.Handler();
        progressBar = (SeekBar) view.findViewById(R.id.progressBar);
        progressBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(scalableVideoView != null && fromUser){
                    scalableVideoView.seekTo(progress);
                    currentTime.setText(stringForTime(progress));
                }
            }
        });

        currentTime = (TextView) view.findViewById(R.id.currentTime);
        totalTime = (TextView) view.findViewById(R.id.totalTime);

        btnPlayPause = (ImageView) view.findViewById(R.id.play_pause);
        btnPlayPause.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                playPause();
            }
        });
    }

    protected void initDesign() {

        view.setBackgroundColor(colorBackgroundVideo);

        bottomLayout = (LinearLayout) view.findViewById(R.id.bottom_layout);
        bottomLayout.setBackgroundColor(colorBackgroundPlayer);
        if(isPlayerVisible ==  1)
            bottomLayout.setVisibility(VISIBLE);
        else if (isPlayerVisible == 2)
            bottomLayout.setVisibility(GONE);

        if (thumb == null)
            thumb = getDensityDrawable("scrubber_control.png", DisplayMetrics.DENSITY_XHIGH);
        thumb.setColorFilter(colorThumb, PorterDuff.Mode.SRC_IN);
        progressBar.setThumb(thumb);

        LayerDrawable layerDrawable = (LayerDrawable) progressBar.getProgressDrawable();
        layerDrawable.findDrawableByLayerId(R.id.backgroundProgressBar).setColorFilter(colorProgressUnfinished, PorterDuff.Mode.SRC_IN);
        layerDrawable.findDrawableByLayerId(R.id.progressColor).setColorFilter(colorProgressFinished, PorterDuff.Mode.SRC_IN);

        if (btnPlay == null)
            btnPlay = getDensityDrawable("btn_play.png", DisplayMetrics.DENSITY_XHIGH);
        if (btnPause == null)
            btnPause = getDensityDrawable("btn_pause.png", DisplayMetrics.DENSITY_XHIGH);

        btnPlayPause.setColorFilter(colorButtonPlayPause, PorterDuff.Mode.SRC_IN);

        currentTime.setTextColor(colorTime);
        totalTime.setTextColor(colorTime);
    }

    protected void playPause() {
        if (scalableVideoView.isPlaying()) {
            scalableVideoView.pause();
            togglePlayPause();
        }
        else {
            scalableVideoView.start();
            togglePlayPause();
            milliSeconds = 0;
        }
    }

    protected void togglePlayPause() {
        if (scalableVideoView.isPlaying())
            btnPlayPause.setImageDrawable(btnPause);
        else
            btnPlayPause.setImageDrawable(btnPlay);
    }

    protected static String stringForTime(int timeMs) {
        if (timeMs <= 0 || timeMs >= 24 * 60 * 60 * 1000) {
            return "00:00";
        }
        int totalSeconds = timeMs / 1000;
        int seconds = totalSeconds % 60;
        int minutes = (totalSeconds / 60) % 60;
        int hours = totalSeconds / 3600;
        StringBuilder stringBuilder = new StringBuilder();
        Formatter mFormatter = new Formatter(stringBuilder, Locale.getDefault());
        if (hours > 0) {
            return mFormatter.format("%d:%02d:%02d", hours, minutes, seconds).toString();
        } else {
            return mFormatter.format("%02d:%02d", minutes, seconds).toString();
        }
    }

    /**
     *  ProgressBar Update
     */
    protected class ProgressTimerTask extends TimerTask {
        @Override
        public void run() {
            handler.post(new Runnable() {
                @Override
                public void run() {
                    if(scalableVideoView != null) {
                        if (scalableVideoView.isPlaying()) {
                            if (milliSeconds < nbMilliSecondsVisible) {
                                milliSeconds += timerLength;
                            }
                            if (milliSeconds == nbMilliSecondsVisible && isPlayerVisible == 0) {
                                bottomLayout.setVisibility(View.GONE);
                                isControlVisible = false;
                            }
                            progressBar.setProgress(scalableVideoView.getCurrentPosition());
                            currentTime.setText(stringForTime(scalableVideoView.getCurrentPosition()));
                        }
                        else
                            togglePlayPause();
                    }
                }
            });
        }
    }

    protected void startProgressTimer() {
        cancelProgressTimer();
        timer = new Timer();
        progressTimerTask = new ProgressTimerTask();
        timer.schedule(progressTimerTask, 0, timerLength);
    }

    protected void cancelProgressTimer() {
        if (timer != null) {
            timer.cancel();
        }
        if (progressTimerTask != null) {
            progressTimerTask.cancel();
        }
    }

    /**
     * Design
     */
    protected Drawable getDensityDrawable(String imageName, int density) {
        BitmapFactory.Options opts = new BitmapFactory.Options();
        opts.inDensity = density;   // ex : DisplayMetrics.DENSITY_HIGH
        return Drawable.createFromResourceStream(context.getResources(), null, getInputStream(imageName), null, opts);
    }

    protected InputStream getInputStream(String fileName) {
        if(fileName == null || fileName.equals(""))
            return null;
        try {
            return context.getAssets().open(fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        scalableVideoView.release();
        if(timer != null)
            timer.cancel();
    }
}
