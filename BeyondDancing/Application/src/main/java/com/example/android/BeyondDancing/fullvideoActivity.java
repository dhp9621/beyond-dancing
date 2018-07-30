package com.example.android.BeyondDancing;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

import java.util.Observable;
import java.util.Observer;

public class fullvideoActivity  extends Activity implements Observer {
    private VideoView video;


    private MediaController videocrl;
    private DanceModel DModel;
    private Uri u;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.full_video);

        // Get Model instance
        DModel = DanceModel.getInstance();
        video = findViewById(R.id.fullvideo);
        Intent intent = getIntent();
        u = DModel.getServerUri();
        video.setVideoURI(u);
        videocrl = new MediaController(this);
        video.setMediaController(videocrl);
        videocrl.setAnchorView(video);
        video.requestFocus();
        video.start();
        DModel.addObserver(this);


    }

    @Override
    public void update(Observable o, Object arg) {

    }

    protected void onDestroy()
    {
        super.onDestroy();


        // Remove observer when activity is destroyed.
        DModel.deleteObserver(this);
    }
}
