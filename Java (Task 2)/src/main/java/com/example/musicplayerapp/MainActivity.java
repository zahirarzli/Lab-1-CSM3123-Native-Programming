package com.example.musicplayerapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private Button startMusicButton;
    private Button stopMusicButton;
    private TextView statusTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startMusicButton = findViewById(R.id.startMusicButton);
        stopMusicButton = findViewById(R.id.stopMusicButton);
        statusTextView = findViewById(R.id.statusTextView); // For status updates

        startMusicButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MusicService.class);
                startService(intent); // Start the foreground service
                statusTextView.setText("Music is Playing...");
            }
        });

        stopMusicButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MusicService.class);
                stopService(intent); // Stop the foreground service
                statusTextView.setText("Music is Stopped.");
            }
        });
    }
}
