package com.seoleo.unscramblegame

interface GameRepository {
    fun scrambledWord(): String
    fun originalWord(): String
    fun next()
}