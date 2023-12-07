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

        videoPlayer = VideoPlayerExo(this, lifecycle, frameLayout)
        videoPlayer.play(Uri.parse("asset:///video_demo.mp4"))
    }
}