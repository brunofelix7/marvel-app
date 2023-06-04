package dev.brunofelix.marvelapp.core.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.brunofelix.marvelapp.core.data.remote.MarvelApi
import dev.brunofelix.marvelapp.feature_character.data.remote.repository.CharacterRepositoryImpl
import dev.brunofelix.marvelapp.feature_character.domain.repository.CharacterRepository
import dev.brunofelix.marvelapp.feature_character.domain.use_case.ListCharactersUseCase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CharacterModule {

    @Provides
    @Singleton
    fun provideListCharactersUseCase(repository: CharacterRepository): ListCharactersUseCase {
        return ListCharactersUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideCharacterRepository(api: MarvelApi): CharacterRepository {
        return CharacterRepositoryImpl(api)
    }
}