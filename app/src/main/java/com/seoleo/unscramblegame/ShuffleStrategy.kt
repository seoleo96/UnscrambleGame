package com.seoleo.unscramblegame

import android.util.Log
import kotlin.random.Random

interface ShuffleStrategy {

    fun shuffle(source: String): String
}

class ShuffleStrategyImpl : ShuffleStrategy {

    override fun shuffle(source: String): String {
        val shuffleText =  source.toCharArray().apply {
            this.shuffle(Random(System.currentTimeMillis()))
        }.joinToString("")
        Log.d(TAG, "shuffle: shuffleText - $shuffleText")
        return shuffleText
    }
    
    companion object {
        private const val TAG = "ShuffleStrategy"
    }
}

class ReverseStrategyImpl : ShuffleStrategy {

    override fun shuffle(source: String): String {
        return source.reversed()
    }
}
