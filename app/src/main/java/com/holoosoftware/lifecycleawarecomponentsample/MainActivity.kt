package com.holoosoftware.lifecycleawarecomponentsample

import android.net.Uri
import android.os.Bundle
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var videoPlayer: VideoPlayerExo

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val frameLayout = FrameLayout(this).apply {
            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT
            )
        }
        setContentView(frameLayout)
        videoPlayer = VideoPlayerExo(this, frameLayout)
        videoPlayer.play(Uri.parse("asset:///video_demo.mp4"))
    }

    override fun onResume() {
        super.onResume()
        if (!videoPlayer.player.isPlaying) {
            videoPlayer.player.play()
        }
    }

    override fun onPause() {
        super.onPause()
        if (videoPlayer.player.isPlaying) {
            videoPlayer.player.pause()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        videoPlayer.player.release()
    }
}