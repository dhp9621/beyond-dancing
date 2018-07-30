package com.example.android.BeyondDancing;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Intent;
import android.util.Log;
import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

import java.util.Observable;
import java.util.Observer;

public class WatchvideoActivity extends Activity implements Observer {
    private VideoView video;

    private VideobarFragment videoFragment;
    private MediaController videocrl;
    private DanceModel DModel;
    private String video_url;
    private  Uri u;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_video);

        // Get Model instance
        DModel = DanceModel.getInstance();
        video = findViewById(R.id.videodance);
        Intent intent = getIntent();
        video_url = intent.getStringExtra("video_url");
        u = Uri.parse(video_url);
        DModel.setServerUri(u);
        video.setVideoURI(u);
        videoFragment = new VideobarFragment();
        videocrl = new MediaController(this);


        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.fragmentvideo,videoFragment ).commit();
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
