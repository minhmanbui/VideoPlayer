package org.apache.cordova.videoplayer;




import android.app.Activity;

import android.content.Intent;

import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.net.Uri;
import android.os.Bundle;

import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

import android.widget.MediaController;
import android.widget.VideoView;


import com.renault.app.R;

public class VideoPlayer extends Activity implements OnCompletionListener,OnPreparedListener,OnTouchListener {
    
    private VideoView mVV;
          
    @Override
    public void onCreate(Bundle b) {
        super.onCreate(b);
 
        setContentView(R.layout.videoplayer);
 
        int fileRes=0;    
        Bundle e = getIntent().getExtras();
        if (e!=null) {
            fileRes = e.getInt("fileRes");
        }
        
        mVV = (VideoView)findViewById(R.id.myvideoview);
        mVV.setOnCompletionListener(this);
        mVV.setOnPreparedListener(this);
        //mVV.setOnTouchListener(this);
 
        if (!playFileRes(fileRes)) return;
        MediaController mediaController = new MediaController(this);
		mediaController.setAnchorView(mVV);
		mVV.setMediaController(mediaController);
        mVV.start();
    }
          
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        int fileRes = 0;
        Bundle e = getIntent().getExtras();
        if (e != null) {
            fileRes = e.getInt("fileRes");
        }
        playFileRes(fileRes);
    }
             
    private boolean playFileRes(int fileRes) {
        if (fileRes==0) {
            stopPlaying();
            return false;
        } else {
            mVV.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + fileRes));
            return true;
        }
    }
 
    public void stopPlaying() {
        mVV.stopPlayback();
        this.finish();             
    }
 
    @Override
    public void onCompletion(MediaPlayer mp) {
        finish();
    }
             
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        stopPlaying();
        return true;
    }
 
    @Override
    public void onPrepared(MediaPlayer mp) {
        mp.setLooping(true);
    }


}