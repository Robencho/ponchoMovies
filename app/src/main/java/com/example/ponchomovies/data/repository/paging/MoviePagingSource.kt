package com.example.ponchomovies.data.repository.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.ponchomovies.core.app.Constants
import com.example.ponchomovies.data.datasource.remote.MovieRemoteDataSource
import com.example.ponchomovies.data.models.remote.mapper.mapFromListModel
import com.example.ponchomovies.domain.model.Movie
import retrofit2.HttpException
import java.io.IOException

class MoviePagingSource(
    private val remoteDataSource: MovieRemoteDataSource,
): PagingSource<Int, Movie>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        return try {
            val currentPage = params.key ?: 1
            val movies = remoteDataSource.getMovies(
                apiKey = Constants.MOVIE_API_KEY,
                pageNumber = currentPage
            )
            LoadResult.Page(
                data = movies.results?.mapFromListModel() ?: emptyList(),
                prevKey = if (currentPage == 1) null else currentPage - 1,
                nextKey = if (movies.results?.isEmpty() == true) null else movies.page?.plus(1)
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return state.anchorPosition
    }
}