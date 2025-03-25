package com.example.ponchomovies.core.generic.useCase

interface BaseUseCase<In, Out> {
    // todo esto cambiará
    suspend fun execute(input:In):Out
}