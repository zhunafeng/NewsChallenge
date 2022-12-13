package com.example.newschallenge.utils

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.flow.toList
import org.junit.Assert

/**
 * Asserts only the [expected] items by just taking that many from the stream
 */
suspend fun <T> Flow<T>.assertItems(vararg expected: T) {
    Assert.assertEquals(expected.toList(), this.take(expected.size).toList())
}

/**
 * Takes all elements from the stream and asserts them.
 */
suspend fun <T> Flow<T>.assertCompleteStream(vararg expected: T) {
    Assert.assertEquals(expected.toList(), this.toList())
}