package com.elisha.tools.ui.screens.screenrecord

import android.app.*
import android.content.Context
import android.content.Intent
import android.hardware.display.DisplayManager
import android.hardware.display.VirtualDisplay
import android.media.MediaRecorder
import android.media.projection.MediaProjection
import android.media.projection.MediaProjectionManager
import android.os.Environment
import android.os.IBinder
import androidx.core.app.NotificationCompat
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

class ScreenRecorderService : Service() {
    private var mediaRecorder: MediaRecorder? = null
    private var mediaProjection: MediaProjection? = null
    private var virtualDisplay: VirtualDisplay? = null
    private var isRecording = false

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val action = intent?.action
        if (action == "ACTION_START") {
            val resultCode = intent.getIntExtra("EXTRA_RESULT_CODE", 0)
            val data = intent.getParcelableExtra<Intent>("EXTRA_DATA")
            val recordAudio = intent.getBooleanExtra("EXTRA_AUDIO", true)

            startForegroundService()
            if (data != null) {
                initRecorder(recordAudio)
                val projectionManager = getSystemService(Context.MEDIA_PROJECTION_SERVICE) as MediaProjectionManager
                mediaProjection = projectionManager.getMediaProjection(resultCode, data)
                virtualDisplay = mediaProjection?.createVirtualDisplay(
                    "ScreenRecorder",
                    1080, 1920, 420,
                    DisplayManager.VIRTUAL_DISPLAY_FLAG_AUTO_MIRROR,
                    mediaRecorder?.surface, null, null
                )
                mediaRecorder?.start()
                isRecording = true
            }
        } else if (action == "ACTION_STOP") {
            stopRecording()
            stopSelf()
        }
        return START_NOT_STICKY
    }

    private fun initRecorder(audio: Boolean) {
        val folder = File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MOVIES), "EliShaTools")
        if (!folder.exists()) folder.mkdirs()
        val file = File(folder, "REC_${SimpleDateFormat("yyyyMMdd_HHmmss", Locale.FRANCE).format(Date())}.mp4")

        mediaRecorder = MediaRecorder().apply {
            if (audio) setAudioSource(MediaRecorder.AudioSource.MIC)
            setVideoSource(MediaRecorder.VideoSource.SURFACE)
            setOutputFormat(MediaRecorder.OutputFormat.MPEG_4)
            setOutputFile(file.absolutePath)
            setVideoSize(1080, 1920)
            setVideoEncoder(MediaRecorder.VideoEncoder.H264)
            if (audio) setAudioEncoder(MediaRecorder.AudioEncoder.AAC)
            setVideoEncodingBitRate(8 * 1000 * 1000)
            setVideoFrameRate(60)
            prepare()
        }
    }

    private fun stopRecording() {
        if (isRecording) {
            try {
                mediaRecorder?.stop()
                mediaRecorder?.reset()
            } catch (_: Exception) {}
            virtualDisplay?.release()
            mediaProjection?.stop()
            isRecording = false
        }
    }

    private fun startForegroundService() {
        val channelId = "EliSha_Recording"
        val channel = NotificationChannel(channelId, "Enregistrement", NotificationManager.IMPORTANCE_LOW)
        val manager = getSystemService(NotificationManager::class.java)
        manager.createNotificationChannel(channel)

        val notification = NotificationCompat.Builder(this, channelId)
            .setContentTitle("Eli Sha Tools")
            .setContentText("Enregistrement en cours...")
            .setSmallIcon(android.R.drawable.ic_menu_camera)
            .build()

        startForeground(101, notification)
    }

    override fun onDestroy() {
        stopRecording()
        super.onDestroy()
    }

    override fun onBind(intent: Intent?): IBinder? = null
}
