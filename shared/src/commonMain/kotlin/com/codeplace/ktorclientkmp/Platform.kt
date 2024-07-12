package com.codeplace.ktorclientkmp

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform