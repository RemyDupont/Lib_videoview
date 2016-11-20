# MaterialVideoView



# Implement

## Simple init 

```java
materialVideoView = (MaterialVideoView) findViewById(R.id.smavideoview);
materialVideoView.create("video.mp4");
```
     
The function create() could have a **string** : load from assets or an **AssetFileDescriptor**
    

## Custom init

```java
materialVideoView
            // drawable
           .thumbDrawable(getDrawable("thumb.png"))
           .playDrawable(getDrawable("play.png"))
           .pauseDrawable(getDrawable("pause.png"))
           
           // scalable : default value is FIT_CENTER
           .setScalableType(ScalableType scalableType)
           
           // custom colors
           .setVideoBackgroundColor(Color.parseColor("#000000"))
           .setPlayerBackgroundColor("#555555")
           .setThumbColor("#abcdef")
           .setProgressFinishedColor("#abcdef")
           .setProgressUnfinishedColor("#abcdef")
           .setTimeColor("#abcdef")
           .setPlayPauseBtnColor("#abcdef")
           
           // default: 0: the player will be hidden after 5 seconds
           // 1: player will be always visible
           // 2: player will be always hidden
           .setPlayerVisibility(0)
           
           // defines how many time the player is visible
           .setVisibleTime(millisecond)
           
           // true : video start at create / default false : video not playing at start
           autoPlay(boolean);
```
                   
These color functions could have a **Color (int)** or a **String ("#ff0000")**
    
    
### Common method

```java
materialVideoView.isPlaying();               // return true or false if video is palying
materialVideoView.play();                    // start video
materialVideoView.pause();                   // pause video       
materialVideoView.setProgress(int time);     // set video time at "time" milliseconds
materialVideoView.getCurrentTime();          // return current video "time"
materialVideoView.setProgressVisible(true);   // Active method : show or hide player
```
