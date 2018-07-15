package com.example.android.BeyondDancing;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

public class VideobarFragment  extends Fragment implements View.OnClickListener {
    ImageButton dance;
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.videobar, container, false);
        dance = (ImageButton) v.findViewById(R.id.danceit);
        dance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( v.getContext(),CameraActivity.class);
                startActivity(intent);
            }
        });
        return v;
    }


    @Override
    public void onClick(View v) {

    }
}
