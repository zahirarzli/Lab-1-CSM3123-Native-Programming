package com.example.custombroadcastreceiverapp // Ensure this matches your package name

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast

class MyCustomBroadcastReceiver : BroadcastReceiver() {
    private val TAG = MyCustomBroadcastReceiver::class.java.simpleName // Use class name as TAG

    override fun onReceive(context: Context, intent: Intent) {
        // Check if the received broadcast action matches the expected action
        if (intent.action == "com.example.CUSTOM_ACTION") {
            // Log the message with the TAG
            Log.d(TAG, "Custom Broadcast Received with action: ${intent.action}")

            // Display a Toast message to inform the user
            Toast.makeText(context, "Custom Broadcast Received!", Toast.LENGTH_SHORT).show()
        }
    }
}
