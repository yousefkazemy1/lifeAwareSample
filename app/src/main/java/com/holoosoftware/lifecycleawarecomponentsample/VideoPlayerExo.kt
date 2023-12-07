package com.holoosoftware.lifecycleawarecomponentsample

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView

class VideoPlayerExo(
    private val context: Context,
    parentView: ViewGroup,
) {
    val player = ExoPlayer.Builder(context).build()

    init {
        parentView.addView(createVideoPlayerView())
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
}