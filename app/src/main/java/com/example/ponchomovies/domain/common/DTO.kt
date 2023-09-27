package com.example.ponchomovies.domain.common

import com.example.ponchomovies.BuildConfig

abstract class DTO {

    fun map(mapper: (Entity) -> DTO, fromClass: Entity): DTO?{
        return createFromDomain(mapper, fromClass)
    }

    fun map(mapper: (DTO) -> Entity, fromClass: DTO): Entity?{
        return mapToDomain(mapper, fromClass)
    }

    private fun createFromDomain(mapper: (Entity) -> DTO, fromClass: Entity): DTO?{
        return try {
            mapper(fromClass)
        } catch (e: Exception){
            null
        }
    }

    private fun mapToDomain(mapper: (DTO) -> Entity, fromClass: DTO): Entity?{
        return try {
            mapper(fromClass)
        }catch (exception: Exception){
            if (BuildConfig.DEBUG){
                exception.printStackTrace()
            }
            null
        }
    }
}