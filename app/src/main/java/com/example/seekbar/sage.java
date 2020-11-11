package com.example.seekbar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;

import java.util.Timer;
import java.util.TimerTask;

public class sage extends AppCompatActivity {

    MediaPlayer player;
    AudioManager audioManager;

    public void play(View view)
    {
        player.start();
    }

    public void pause(View view)
    {
        player.pause();
    }

    public void next(View view) {
        Intent intent = new Intent(this, goodThing.class);
        //intent.putExtra(MSG , message);
        startActivity(intent);
        player.pause();

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sage);

        Intent intent = getIntent();
        //String message = intent.getStringExtra(MainActivity.MSG);

        player = MediaPlayer.create(this , R.raw.music_sage);

        audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        int maxVol = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        int curVol = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);

        SeekBar SeekVol = findViewById(R.id.seekVol);
        SeekVol.setMax(maxVol);
        SeekVol.setProgress(curVol);

        SeekVol.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, progress, 0);

            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        final SeekBar SeekProg = findViewById(R.id.progress);
        SeekProg.setMax(player.getDuration());

        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                SeekProg.setProgress(player.getCurrentPosition());
            }
        } ,  0 , 900);

        SeekProg.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                player.seekTo(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}
