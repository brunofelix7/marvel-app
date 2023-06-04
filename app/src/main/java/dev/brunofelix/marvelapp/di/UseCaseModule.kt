package dev.brunofelix.marvelapp.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.brunofelix.marvelapp.domain.repository.MarvelRepository
import dev.brunofelix.marvelapp.domain.use_case.ListCharactersUseCase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    @Singleton
    fun provideListCharactersUseCase(repository: MarvelRepository): ListCharactersUseCase {
        return ListCharactersUseCase(repository)
    }
}