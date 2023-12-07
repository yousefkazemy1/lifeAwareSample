package com.holoosoftware.lifecycleawarecomponentsample

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView

class VideoPlayerExo(
    private val context: Context,
    private val lifecycle: Lifecycle,
    parentView: ViewGroup
): DefaultLifecycleObserver {
    private val player = ExoPlayer.Builder(context).build()

    init {
        parentView.addView(createVideoPlayerView())
        lifecycle.addObserver(this)
    }

    fun play(uri: Uri) {
        player.setMediaItem(MediaItem.fromUri(uri))
        player.prepare()
        player.playWhenReady = true
    }

    private fun createVideoPlayerView(): PlayerView {
        val playerView =
            LayoutInflater.from(context).inflate(R.layout.video_player_ui, null) as PlayerView
        playerView.player = player
        return playerView
    }

    override fun onResume(owner: LifecycleOwner) {
        super.onResume(owner)
        if (!player.isPlaying) {
            player.play()
        }
    }

    override fun onPause(owner: LifecycleOwner) {
        super.onPause(owner)
        if (player.isPlaying) {
            player.pause()
        }
    }

    override fun onDestroy(owner: LifecycleOwner) {
        super.onDestroy(owner)
        player.release()
        lifecycle.removeObserver(this)
    }
}