package com.forlxy.presettimer

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.media.Ringtone
import android.os.CountDownTimer
import android.os.Handler
import android.os.IBinder
import android.support.design.widget.Snackbar

class TimerService: Service() {

    var context = this
//    private lateinit var mHandler: Handler
//    private lateinit var mRunnable: Runnable
    private var notificationManager: NotificationManager? = null
    var norTimer = object: CountDownTimer(5000, 1000) {

        override fun onTick(millisUntilFinished: Long) {}

        override fun onFinish() {
//                Snackbar.make(getWindow().getDecorView().getRootView(), "Click the pin for more options", Snackbar.LENGTH_LONG).show();

            val notificationID = 101

            val channelID = "com.forlxy.presettimer"

            val notification = Notification.Builder(this@TimerService,
                channelID)
                .setContentTitle("Time up!")
                .setContentText("Time up!")
                .setSmallIcon(android.R.drawable.ic_dialog_info)
                .setChannelId(channelID)
                .build()

            notificationManager?.notify(notificationID, notification)

            // Get the device default ringtone
            var ringTool = RingTool()
            val ringtone: Ringtone = ringTool.getDefaultRing(context)
            if (!ringtone.isPlaying){
                ringtone.play()
            }
            ringTool.vibratePhone(context)
            stopSelf()
        }
    }


    override fun onBind(intent: Intent): IBinder? {
        throw UnsupportedOperationException("Not yet implemented")
    }

    override fun onCreate() {
        super.onCreate()

        notificationManager =
            getSystemService(
                Context.NOTIFICATION_SERVICE) as NotificationManager

        createNotificationChannel(
            "com.forlxy.presettimer",
            "Preset Timer",
            "Time up!")
    }
    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        // Send a notification that service is started
//        Snackbar.make("Service started.")

        // Do a periodic task
//        mHandler = Handler()


        norTimer.start()
//
//        mRunnable = Runnable { }
//        mHandler.postDelayed(mRunnable, 5000)

        return Service.START_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
//        toast("Service destroyed.")
//        mHandler.removeCallbacks(mRunnable)
    }

//    // Custom method to do a task
//    private fun showRandomNumber() {
//        val rand = Random()
//        val number = rand.nextInt(100)
//        toast("Random Number : $number")
//        mHandler.postDelayed(mRunnable, 5000)
//    }


    fun setTimer(length: Long): Unit {
        norTimer = object: CountDownTimer(length * 1000, 1000) {

            override fun onTick(millisUntilFinished: Long) {}

            override fun onFinish() {
//                Snackbar.make(getWindow().getDecorView().getRootView(), "Click the pin for more options", Snackbar.LENGTH_LONG).show();

                val notificationID = 101

                val channelID = "com.forlxy.presettimer"

                val notification = Notification.Builder(this@TimerService,
                    channelID)
                    .setContentTitle("Time up!")
                    .setContentText("Time up!")
                    .setSmallIcon(android.R.drawable.ic_dialog_info)
                    .setChannelId(channelID)
                    .build()

                notificationManager?.notify(notificationID, notification)

//                onDestroy()
                }
        }
    }

    private fun createNotificationChannel(id: String, name: String, description: String) {

        val importance = NotificationManager.IMPORTANCE_DEFAULT
        val channel = NotificationChannel(id, name, importance)

        channel.description = description
        channel.enableLights(true)
        channel.lightColor = Color.RED
        channel.enableVibration(true)
        channel.vibrationPattern =
            longArrayOf(100, 200, 300, 400, 500, 400, 300, 200, 400)

        channel.setShowBadge(true)
        notificationManager?.createNotificationChannel(channel)
    }

}