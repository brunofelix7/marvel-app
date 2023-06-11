package dev.brunofelix.marvelapp.core.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.brunofelix.marvelapp.core.data.local.MarvelDatabase
import dev.brunofelix.marvelapp.core.data.remote.MarvelApi
import dev.brunofelix.marvelapp.feature_character.data.local.repository.CharacterLocalRepositoryImpl
import dev.brunofelix.marvelapp.feature_character.data.remote.repository.CharacterRemoteRepositoryImpl
import dev.brunofelix.marvelapp.feature_character.domain.repository.CharacterLocalRepository
import dev.brunofelix.marvelapp.feature_character.domain.repository.CharacterRemoteRepository
import dev.brunofelix.marvelapp.feature_character.domain.use_case.CharacterListUseCase
import dev.brunofelix.marvelapp.feature_character.domain.use_case.CharacterSearchUseCase
import dev.brunofelix.marvelapp.feature_character.domain.use_case.FindComicsUseCase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CharacterModule {

    @Provides
    @Singleton
    fun provideCharacterListUseCase(repository: CharacterRemoteRepository) =
        CharacterListUseCase(repository)

    @Provides
    @Singleton
    fun provideCharacterSearchUseCase(repository: CharacterRemoteRepository) =
        CharacterSearchUseCase(repository)

    @Provides
    @Singleton
    fun provideFindComicsUseCase(repository: CharacterRemoteRepository) =
        FindComicsUseCase(repository)

    @Provides
    @Singleton
    fun provideCharacterRemoteRepository(api: MarvelApi): CharacterRemoteRepository {
        return CharacterRemoteRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun provideCharacterLocalRepository(db: MarvelDatabase): CharacterLocalRepository {
        return CharacterLocalRepositoryImpl(db)
    }
}