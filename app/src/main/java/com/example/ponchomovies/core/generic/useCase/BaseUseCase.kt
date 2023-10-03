package com.example.ponchomovies.core.generic.useCase

interface BaseUseCase<In, Out> {
    suspend fun execute(input:In):Out
}