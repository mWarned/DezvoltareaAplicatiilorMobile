package com.example.videoplayer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.MediaController;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {
    private VideoView videoView;
    private int savedPosition = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        ImageButton btnPlay = findViewById(R.id.btnPlay);
        ImageButton btnPause = findViewById(R.id.btnPause);

        MediaController mc = new MediaController(this);
        videoView = findViewById(R.id.videoView);

        String uriPath2 = "android.resource://" + getPackageName() + "/" + R.raw.video;
        Uri uri2 = Uri.parse(uriPath2);

        mc.setAnchorView(videoView);
        videoView.setVideoURI(uri2);

        View.OnClickListener videoListener = v -> {
            if (v.getId() == R.id.btnPlay) {
                videoView.start();
            } else if (v.getId() == R.id.btnPause){
                videoView.pause();
            }
        };

        btnPlay.setOnClickListener(videoListener);
        btnPause.setOnClickListener(videoListener);

        if (savedInstanceState != null) {
            savedPosition = savedInstanceState.getInt("savedPosition", 0);
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("savedPosition", videoView.getCurrentPosition());
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);

            // Hide the action bar if it's not null
            if (getSupportActionBar() != null) {
                getSupportActionBar().hide();
            }

            // Adjust the layout params of the VideoView to match_parent
            ViewGroup.LayoutParams layoutParams = videoView.getLayoutParams();
            layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
            layoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT;
            videoView.setLayoutParams(layoutParams);
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            // Show the action bar if it's not null
            if (getSupportActionBar() != null) {
                getSupportActionBar().show();
            }

            // Reset the layout params of the VideoView
            ViewGroup.LayoutParams layoutParams = videoView.getLayoutParams();
            layoutParams.width = ViewGroup.LayoutParams.WRAP_CONTENT;
            layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT;
            videoView.setLayoutParams(layoutParams);
        }
    }

}
