package com.example.alarmy
import android.media.MediaPlayer
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.alarmy.R

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        private lateinit var mediaPlayer: MediaPlayer
        private val FORWARD_REWIND_INTERVAL = 5000 // milliseconds
        mediaPlayer = MediaPlayer.create(this, R.raw.blame)

        findViewById<Button>(R.id.btnPlay).setOnClickListener {
            if (!mediaPlayer.isPlaying) {
                mediaPlayer.start()
            }
        }

        findViewById<Button>(R.id.btnPause).setOnClickListener {
            if (mediaPlayer.isPlaying) {
                mediaPlayer.pause()
            }
        }

        findViewById<Button>(R.id.btnForward).setOnClickListener {
            val currentPosition = mediaPlayer.currentPosition
            val duration = mediaPlayer.duration
            if (currentPosition + FORWARD_REWIND_INTERVAL < duration) {
                mediaPlayer.seekTo(currentPosition + FORWARD_REWIND_INTERVAL)
            } else {
                mediaPlayer.seekTo(duration)
            }
        }

        findViewById<Button>(R.id.btnRewind).setOnClickListener {
            val currentPosition = mediaPlayer.currentPosition
            if (currentPosition - FORWARD_REWIND_INTERVAL > 0) {
                mediaPlayer.seekTo(currentPosition - FORWARD_REWIND_INTERVAL)
            } else {
                mediaPlayer.seekTo(0)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        if (mediaPlayer.isPlaying) {
            mediaPlayer.stop()
        }
        mediaPlayer.release()
    }
}
