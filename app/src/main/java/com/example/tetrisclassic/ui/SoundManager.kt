package com.example.tetrisclassic.ui

import android.media.AudioManager
import android.media.ToneGenerator

class SoundManager {
    private val toneGenerator = ToneGenerator(AudioManager.STREAM_MUSIC, 100)

    fun playMoveSound() {
        toneGenerator.startTone(ToneGenerator.TONE_PROP_BEEP, 50)
    }

    fun playRotateSound() {
        toneGenerator.startTone(ToneGenerator.TONE_PROP_BEEP2, 50)
    }

    fun playLineClearSound() {
        toneGenerator.startTone(ToneGenerator.TONE_CDMA_PIP, 200)
    }

    fun playGameOverSound() {
        toneGenerator.startTone(ToneGenerator.TONE_CDMA_ABBR_REORDER, 500)
    }

    fun release() {
        toneGenerator.release()
    }
}
