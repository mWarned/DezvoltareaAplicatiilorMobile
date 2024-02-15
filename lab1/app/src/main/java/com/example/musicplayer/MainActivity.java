package com.example.musicplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Switch;

public class MainActivity extends AppCompatActivity {
    private MediaPlayer mp1;
    @SuppressLint("UseSwitchCompatOrMaterialCode")
    private Switch backMusic;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        backMusic = (Switch) findViewById(R.id.switchBack);
        mp1= MediaPlayer.create(getApplicationContext(), R.raw.song);

        ImageButton btnPlay=(ImageButton)findViewById(R.id.btnPlay);
        ImageButton btnPause=(ImageButton)findViewById(R.id.btnPause);
        ImageButton btnStop=(ImageButton)findViewById(R.id.btnStop);

        View.OnClickListener elem = v -> {

            if(v.getId() == R.id.btnPlay){
                mp1.start();
            } else if(v.getId() == R.id.btnPause){
                mp1.pause();
            } else if(v.getId() == R.id.btnStop){
                mp1.seekTo(0);
                mp1.pause();
            }
        };

        btnPlay.setOnClickListener(elem);
        btnPause.setOnClickListener(elem);
        btnStop.setOnClickListener(elem);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (!backMusic.isChecked()) {
            mp1.pause();
        }
    }
}
