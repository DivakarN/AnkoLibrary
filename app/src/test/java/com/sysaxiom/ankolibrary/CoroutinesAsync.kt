package com.example.solarsystem

import kotlinx.coroutines.*
import org.junit.Test

class CoroutinesAsyncTest {

    @Test
    fun usingAsync() {
        println("Starting...")

        GlobalScope.async(Dispatchers.IO) {
            delay(1_000L)
            println("Inside...")
        }

        Thread.sleep(1_500L)

        println("Stopping...")
    }

    @Test
    fun usingAsyncWithDeferred() {
        println("Starting...")

        GlobalScope.launch(Dispatchers.IO) {
            val result: Deferred<Int> = async(Dispatchers.IO) {
                delay(1_000L)
                println("Inside...")
                42
            }

            println("the result: ${result.await()}")
        }

        Thread.sleep(1_500L)

        println("Stopping...")
    }

    @Test
    fun usingAsyncTwice() {
        println("Starting...")

        GlobalScope.launch(Dispatchers.IO) {
            val result: Deferred<Int> = async(Dispatchers.IO) {
                delay(1_000L)
                println("Inside first...")
                42
            }

            val result2: Deferred<Int> = async(Dispatchers.IO) {
                delay(1_000L)
                println("Inside second...")
                8
            }

            println("the sum: ${result.await() + result2.await()}")
        }

        Thread.sleep(1_500L)

        println("Stopping...")
    }

}
