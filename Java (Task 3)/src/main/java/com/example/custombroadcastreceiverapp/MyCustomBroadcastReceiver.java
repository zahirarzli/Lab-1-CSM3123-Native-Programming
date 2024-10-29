package com.example.custombroadcastreceiverapp;  // Ensure this matches your actual package name

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class MyCustomBroadcastReceiver extends BroadcastReceiver {
    private static final String TAG = MyCustomBroadcastReceiver.class.getSimpleName(); // Use class name as TAG

    @Override
    public void onReceive(Context context, Intent intent) {
        // Check if the action matches the expected custom action
        if ("com.example.CUSTOM_ACTION".equals(intent.getAction())) {
            Log.d(TAG, "Custom Broadcast Received with action: " + intent.getAction()); // Improved log message
            Toast.makeText(context, "Custom Broadcast Received!", Toast.LENGTH_SHORT).show();
        }
    }
}
