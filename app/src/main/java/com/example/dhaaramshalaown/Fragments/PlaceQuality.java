package com.example.dhaaramshalaown.Fragments;

import android.graphics.Color;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.MediaController;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.VideoView;

import com.example.dhaaramshalaown.R;

import java.util.ArrayList;


public class PlaceQuality extends Fragment {


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private String mParam1;
    private String mParam2;

    public PlaceQuality() {

    }

    VideoView videoView;
    TextView textview;
    TextView textview2;
    TextView textview3;
    TextView textview4;
    TextView textview5;
    TextView textview6;
    boolean flag = true;
    private  static final ArrayList<String> placequalitylist = new ArrayList<>();

    public static PlaceQuality newInstance(String param1, String param2) {
        PlaceQuality fragment = new PlaceQuality();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_place_quality, container, false);


        videoView = view.findViewById(R.id.videoview);
        textview= view.findViewById(R.id.textview);
        textview2= view.findViewById(R.id.textview2);
        textview3= view.findViewById(R.id.textview3);
        textview4= view.findViewById(R.id.textview4);
        textview5= view.findViewById(R.id.textview5);
        textview6= view.findViewById(R.id.textview6);

        textview.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if (flag) {

                    textview.setTextColor(Color.parseColor("#ffffff"));
                    textview.setBackgroundResource(R.drawable.designchange);
                    flag = false;
                    placequalitylist.add("Charming");

                } else {

                    textview.setTextColor(Color.parseColor("#FF000000"));
                    textview.setBackgroundResource(R.drawable.design);
                    flag = !flag;
                    placequalitylist.remove("Charming");
                }

            }
        });

        textview2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (flag) {
                    textview2.setTextColor(Color.parseColor("#ffffff"));
                    textview2.setBackgroundResource(R.drawable.designchange);
                    flag = false;
                    placequalitylist.add("Hip");

                } else {
                    textview2.setTextColor(Color.parseColor("#FF000000"));
                    textview2.setBackgroundResource(R.drawable.design);
                    flag = !flag;
                    placequalitylist.remove("Hip");
                }
            }

        });

        textview3.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (flag) {
                    textview3.setTextColor(Color.parseColor("#ffffff"));
                    textview3.setBackgroundResource(R.drawable.designchange);
                    flag = false;
                    placequalitylist.add("Stylish");
                } else {
                    textview3.setTextColor(Color.parseColor("#FF000000"));
                    textview3.setBackgroundResource(R.drawable.design);
                    flag = !flag;
                    placequalitylist.remove("Stylish");
                }
            }

        });

        textview4.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (flag) {
                    textview4.setTextColor(Color.parseColor("#ffffff"));
                    textview4.setBackgroundResource(R.drawable.designchange);
                    flag = false;
                    placequalitylist.add("Upscale");
                } else {
                    textview4.setTextColor(Color.parseColor("#FF000000"));
                    textview4.setBackgroundResource(R.drawable.design);
                    flag = !flag;
                    placequalitylist.remove("Upscale");
                }
            }

        });

        textview5.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (flag) {
                    textview5.setTextColor(Color.parseColor("#ffffff"));
                    textview5.setBackgroundResource(R.drawable.designchange);
                    flag = false;
                    placequalitylist.add("Central");
                } else {
                    textview5.setTextColor(Color.parseColor("#FF000000"));
                    textview5.setBackgroundResource(R.drawable.design);
                    flag = !flag;
                    placequalitylist.remove("Central");
                }
            }
        });

        textview6.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (flag) {
                    textview6.setTextColor(Color.parseColor("#ffffff"));
                    textview6.setBackgroundResource(R.drawable.designchange);
                    flag = false;
                    placequalitylist.add("Unique");
                } else {
                    textview6.setTextColor(Color.parseColor("#FF000000"));
                    textview6.setBackgroundResource(R.drawable.design);
                    flag = !flag;
                    placequalitylist.remove("Unique");
                }
            }

        });

        String Link = "android.resource://" + getActivity().getPackageName() + "/" + R.raw.video;
        Uri uri = Uri.parse(Link);
        videoView.setVideoURI(uri);
        videoView.start();

        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                mediaPlayer.setLooping(true);
            }
        });

        return view;
    }

    @Override
    public void onStart() {
        videoView.start();
        super.onStart();
    }

    public static ArrayList<String> getPlacequalitylist() {
        return placequalitylist;
    }
}
