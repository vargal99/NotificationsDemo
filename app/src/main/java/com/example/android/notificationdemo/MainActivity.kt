package com.example.android.notificationdemo

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.ClipDescription
import android.content.Context
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.NotificationCompat
import com.example.android.notificationdemo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val channelID = "com.example.android.notificationdemo.channel1"
    private var notificationManager:NotificationManager?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

       createNotificationChannel(channelID, "DemoChannel", "this is a demo")
        binding.button.setOnClickListener {
            displayNotification()
        }
    }
    private fun displayNotification(){
        val notificationId = 45
        val notification = NotificationCompat.Builder(this@MainActivity,channelID)
            .setContentTitle("Demo title")
            .setContentText("This is a demo notification")
            .setSmallIcon(android.R.drawable.ic_dialog_info)
            .setAutoCancel(true)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .build()
        notificationManager?.notify(notificationId,notification)

    }

    private fun createNotificationChannel(id: String, name: String, channelDescription: String){
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
            val importance = NotificationManager.IMPORTANCE_HIGH
            val channel = NotificationChannel(id,name,importance).apply {
                description = channelDescription
            }
            notificationManager?.createNotificationChannel(channel)
        }

    }
}