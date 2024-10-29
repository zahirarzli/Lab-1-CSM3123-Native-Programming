package com.example.musicplayerapp;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.IBinder;
import androidx.core.app.NotificationCompat;

public class MusicService extends Service {
    private static final String CHANNEL_ID = "music_service_channel";
    private MediaPlayer mediaPlayer;

    @Override
    public void onCreate() {
        super.onCreate();
        mediaPlayer = MediaPlayer.create(this, R.raw.song); // Ensure this points to your audio file
        mediaPlayer.setLooping(true); // Set looping
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        startForegroundServiceWithNotification(); // Start foreground service with notification
        if (!mediaPlayer.isPlaying()) {
            mediaPlayer.start(); // Start playing the music
        }
        return START_STICKY; // Keeps service running until explicitly stopped
    }

    private void startForegroundServiceWithNotification() {
        createNotificationChannel();

        // Create a pending intent for notification click
        Intent notificationIntent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        // Build notification
        Notification notification = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setContentTitle("Music Player")
                .setContentText("Playing music...")
                .setSmallIcon(R.drawable.ic_music_note) // Ensure you have this icon in res/drawable
                .setContentIntent(pendingIntent)
                .setPriority(NotificationCompat.PRIORITY_LOW) // Set priority
                .build();

        // Start the service in the foreground
        startForeground(1, notification);
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel serviceChannel = new NotificationChannel(
                    CHANNEL_ID,
                    "Music Service Channel",
                    NotificationManager.IMPORTANCE_LOW
            );
            NotificationManager manager = getSystemService(NotificationManager.class);
            if (manager != null) {
                manager.createNotificationChannel(serviceChannel);
            }
        }
    }

    @Override
    public void onDestroy() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
        }
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
