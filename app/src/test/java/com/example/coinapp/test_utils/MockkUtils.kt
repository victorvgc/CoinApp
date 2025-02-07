package com.example.coinapp.test_utils

import io.mockk.coVerify

fun coVerifyOnce(block: suspend () -> Unit) = coVerify(exactly = 1) {
    block()
}
