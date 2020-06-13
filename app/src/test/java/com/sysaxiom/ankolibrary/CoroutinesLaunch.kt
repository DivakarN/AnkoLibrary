package com.example.solarsystem

import kotlinx.coroutines.*
import org.junit.Test

class CoroutinesLaunchTest {

    @Test
    fun startWithLaunchNoSleep() {
        println("Starting...")

        GlobalScope.launch(Dispatchers.IO) {
            delay(1_000L)
            println("Inside...")
        }

        println("Stopping...")
    }

    @Test
    fun startWithLaunchSleep() {
        println("Starting...")

        GlobalScope.launch(Dispatchers.IO) {
            delay(1_000L)
            println("Inside...")
        }

        Thread.sleep(1_500L)

        println("Stopping...")
    }

    @Test
    fun startWithLaunchMultiple() {
        println("Starting...")

        GlobalScope.launch(Dispatchers.IO) {
            delay(1_000L)
            println("Inside 1...")
        }

        GlobalScope.launch(Dispatchers.IO) {
            delay(1_000L)
            println("Inside 2...")
        }

        Thread.sleep(1_500L)

        println("Stopping...")
    }

    @Test
    fun startWithLaunchLazy() {
        println("Starting...")

        val job = GlobalScope.launch(Dispatchers.IO, CoroutineStart.LAZY) {
            delay(1_000L)
            println("Inside...")
        }

        job.start()

        Thread.sleep(1_500L)

        println("Stopping...")
    }

}
