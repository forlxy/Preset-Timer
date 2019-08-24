package com.forlxy.presettimer

import android.content.Context
import android.database.Cursor
import android.media.Ringtone
import android.media.RingtoneManager
import android.net.Uri
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import android.provider.MediaStore

class RingTool {

    fun getDefaultRing(context: Context): Ringtone {
        val uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE)
        return RingtoneManager.getRingtone(context, uri)
    }

    // Extension method to get all specific type audio/sound files list
    fun Context.sounds(type:String):MutableList<Sound>{
        // Initialize an empty mutable list of sounds
        val list:MutableList<Sound> = mutableListOf()

        // Get the internal storage media store audio uri
        //val uri: Uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI
        val uri: Uri = MediaStore.Audio.Media.INTERNAL_CONTENT_URI

        // Non-zero if the audio file type match
        val selection = "$type != 0"

        // Sort the audio
        val sortOrder = MediaStore.Audio.Media.TITLE + " ASC"
        //val sortOrder = MediaStore.Audio.Media.TITLE + " DESC"

        // Query the storage for specific type audio files
        val cursor: Cursor = this.contentResolver.query(
            uri, // Uri
            null, // Projection
            selection, // Selection
            null, // Selection arguments
            sortOrder // Sort order
        )

        // If query result is not empty
        if (cursor!= null && cursor.moveToFirst()){
            val id:Int = cursor.getColumnIndex(MediaStore.Audio.Media._ID)
            val title:Int = cursor.getColumnIndex(MediaStore.Audio.Media.TITLE)

            // Now loop through the audio files
            do {
                val audioId:Long = cursor.getLong(id)
                val audioTitle:String = cursor.getString(title)

                // Add the current audio/sound to the list
                list.add(Sound(audioId,audioTitle))
            }while (cursor.moveToNext())
        }

        // Finally, return the audio files list
        return  list
    }

    fun vibratePhone(context: Context) {
        val vibrator = context?.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        if (Build.VERSION.SDK_INT >= 26) {
            vibrator.vibrate(VibrationEffect.createOneShot(200, VibrationEffect.DEFAULT_AMPLITUDE))
        } else {
            vibrator.vibrate(200)
        }
    }

    // Initialize a new data class to hold audio data
    data class Sound(val id:Long, val title:String)
}