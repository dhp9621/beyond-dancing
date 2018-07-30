package com.example.android.BeyondDancing;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class ListViewFragment extends Fragment {
    private ListView mVideoList;
    private MessageAdapter mMessageAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.video_frame, container, false);
        mVideoList = (ListView) rootView.findViewById(R.id.videoListView);

        List<BasicVideo> basicVideos = new ArrayList<>();
        mMessageAdapter = new MessageAdapter(getActivity(), R.layout.video_item, basicVideos);
        mVideoList.setAdapter(mMessageAdapter);
        return rootView;
    }

    public void addVideos(List<BasicVideo> l) {
        mMessageAdapter.addAll(l);
    }
}
