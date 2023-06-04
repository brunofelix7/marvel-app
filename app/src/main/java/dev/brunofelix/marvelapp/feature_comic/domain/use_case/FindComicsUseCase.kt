package dev.brunofelix.marvelapp.feature_comic.domain.use_case

import dev.brunofelix.marvelapp.BuildConfig
import dev.brunofelix.marvelapp.core.data.ResourceState
import dev.brunofelix.marvelapp.feature_comic.domain.repository.ComicRepository
import kotlinx.coroutines.flow.flow
import timber.log.Timber
import java.io.IOException
import java.net.UnknownHostException
import javax.inject.Inject

class FindComicsUseCase @Inject constructor(
    private val repository: ComicRepository
) {

    suspend operator fun invoke(characterId: Int) = flow {
        try {
            emit(ResourceState.Loading())

            val response = repository.fetchComics(characterId)
            val body = response.body()

            if (response.isSuccessful) {
                if (body != null) {
                    emit(ResourceState.Success(data = body.data?.results?.map { it.toComic() }))
                } else {
                    emit(ResourceState.Error(message = BuildConfig.SERVER_ERROR))
                }
            } else {
                emit(ResourceState.Error(message = response.message()))
            }
        } catch (t: Throwable) {
            Timber.e("$t")
            when (t) {
                is UnknownHostException -> {
                    emit(ResourceState.Error(message = BuildConfig.HOST_ERROR))
                }
                is IOException -> {
                    emit(ResourceState.Error(message = BuildConfig.SERVER_ERROR))
                }
                else -> {
                    emit(ResourceState.Error(message = BuildConfig.UNKNOWN_ERROR))
                }
            }
        }
    }
}