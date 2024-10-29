package com.example.custombroadcastreceiverapp // Ensure this matches your package name

import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.localbroadcastmanager.content.LocalBroadcastManager

class MainActivity : AppCompatActivity() {
    private lateinit var sendBroadcastButton: Button // Button to send broadcast
    private lateinit var myReceiver: MyCustomBroadcastReceiver // Custom Broadcast Receiver instance

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main) // Set the layout resource

        // Initialize the Button using findViewById
        sendBroadcastButton = findViewById(R.id.sendBroadcastButton)

        // Initialize the BroadcastReceiver
        myReceiver = MyCustomBroadcastReceiver()

        // Set up button click listener
        sendBroadcastButton.setOnClickListener {
            val intent = Intent("com.example.CUSTOM_ACTION")
            LocalBroadcastManager.getInstance(this).sendBroadcast(intent)
        }
    }

    override fun onStart() {
        super.onStart()
        // Register the BroadcastReceiver
        LocalBroadcastManager.getInstance(this).registerReceiver(
            myReceiver,
            IntentFilter("com.example.CUSTOM_ACTION")
        )
    }

    override fun onStop() {
        super.onStop()
        // Unregister the BroadcastReceiver
        LocalBroadcastManager.getInstance(this).unregisterReceiver(myReceiver)
    }
}
