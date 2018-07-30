package com.example.android.BeyondDancing;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import java.util.Observable;
import java.util.Observer;

public class VideobarFragment extends Fragment implements View.OnClickListener,Observer{
    DanceModel DModel;
    ImageButton dance;
    ImageButton interst;
    ImageButton reject;
    ImageButton fullvideo;
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DModel = DanceModel.getInstance();
        View v = inflater.inflate(R.layout.videobar, container, false);
        fullvideo = (ImageButton) v.findViewById(R.id.fullscreen);
        fullvideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( v.getContext(),fullvideoActivity.class);
                startActivity(intent);
            }
        });
        dance = (ImageButton) v.findViewById(R.id.danceit);
        dance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dance.setImageResource(R.drawable.danceselect);
                DModel.addUri(0);
                Intent intent = new Intent( v.getContext(),CameraActivity.class);
                startActivity(intent);
            }
        });
        reject = (ImageButton) v.findViewById(R.id.reject);
        reject.setOnClickListener(new View.OnClickListener() {
                                       @Override
                                       public void onClick(View v) {
                                           DModel.resetUri();
                                           reject.setImageResource(R.drawable.downvote);
                                           Intent intent2 = new Intent(v.getContext(), MainActivity.class);
                                           startActivity(intent2);
                                       }
                                   });
        interst = (ImageButton) v.findViewById(R.id.select);

        instbuttonupdate();


        interst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DModel.addUri(0);
                if(DModel.fitforcompare()){
                    instbuttonupdate();

                    Intent intent = new Intent(v.getContext(), PlaybackActivity.class);
                    startActivity(intent);
                } else {
                    instbuttonupdate();

                    Intent intent2 = new Intent(v.getContext(), MainActivity.class);
                    startActivity(intent2);
                }
            } });
        DModel.addObserver(this);
        return v;
    }
    private void instbuttonupdate(){
        int num = DModel.numbvideo();
        switch(num){
            case 0:
                interst.setImageResource(R.drawable.select);
                break;
            case 1:
                interst.setImageResource(R.drawable.select1);
                break;
            case 2:
                interst.setImageResource(R.drawable.select2);
                break;


        }

    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void update(Observable o, Object arg) {

    }
    @Override
    public void onDestroy()
    {
        super.onDestroy();


        // Remove observer when activity is destroyed.
        DModel.deleteObserver(this);
    }
}
