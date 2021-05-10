package com.android.a64streamingvideo;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {

    ProgressDialog progressDialog;
    VideoView videoView;
    ImageButton btnPlayPause;

    String videoUrl = "https://www.dropbox.com/s/0x2ke57h7wv49ll/Sample_512x288.mp4";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        videoView = findViewById(R.id.videoView);
        btnPlayPause = findViewById(R.id.btn_play_pause);

        btnPlayPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressDialog = new ProgressDialog(MainActivity.this);
                progressDialog.setMessage("Please wait...");
                progressDialog.setCanceledOnTouchOutside(false);
                progressDialog.show();


                try {
                    if (!videoView.isPlaying()) {
                        Uri uri = Uri.parse(videoUrl);
                        videoView.setVideoURI(uri);
                        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mediaPlayer) {
                                btnPlayPause.setImageResource(R.drawable.ic_play);
                            }
                        });
                    } else {
                        videoView.pause();
                        btnPlayPause.setImageResource(R.drawable.ic_play);
                    }
                } catch (Exception e) {

                }

                videoView.requestFocus();
                videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                    @Override
                    public void onPrepared(MediaPlayer mediaPlayer) {
                        progressDialog.dismiss();
                        mediaPlayer.setLooping(true);
                        videoView.start();
                        btnPlayPause.setImageResource(R.drawable.ic_pause);
                    }
                });

            }
        });

    }
}