package dev.brunofelix.marvelapp.core.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.brunofelix.marvelapp.core.data.remote.MarvelApi
import dev.brunofelix.marvelapp.feature_comic.data.remote.repository.ComicRepositoryImpl
import dev.brunofelix.marvelapp.feature_comic.domain.repository.ComicRepository
import dev.brunofelix.marvelapp.feature_comic.domain.use_case.FindComicsUseCase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ComicModule {

    @Provides
    @Singleton
    fun provideFindComicsUseCase(repository: ComicRepository): FindComicsUseCase {
        return FindComicsUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideComicRepository(api: MarvelApi): ComicRepository {
        return ComicRepositoryImpl(api)
    }
}