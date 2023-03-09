package com.example.ponchomovies.framework.network

sealed class Failure {
    object NetworkConnection : Exception()
    object ExceptionUnknown : Exception()
}