package dev.brunofelix.marvelapp.core.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.brunofelix.marvelapp.core.data.remote.MarvelApi
import dev.brunofelix.marvelapp.feature_character.data.remote.repository.CharacterRepositoryImpl
import dev.brunofelix.marvelapp.feature_character.domain.repository.CharacterRepository
import dev.brunofelix.marvelapp.feature_character.domain.use_case.CharacterListUseCase
import dev.brunofelix.marvelapp.feature_character.domain.use_case.CharacterSearchUseCase
import dev.brunofelix.marvelapp.feature_character.domain.use_case.FindComicsUseCase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CharacterModule {

    @Provides
    @Singleton
    fun provideCharacterListUseCase(repository: CharacterRepository) =
        CharacterListUseCase(repository)

    @Provides
    @Singleton
    fun provideCharacterSearchUseCase(repository: CharacterRepository) =
        CharacterSearchUseCase(repository)

    @Provides
    @Singleton
    fun provideFindComicsUseCase(repository: CharacterRepository) =
        FindComicsUseCase(repository)

    @Provides
    @Singleton
    fun provideCharacterRepository(api: MarvelApi): CharacterRepository {
        return CharacterRepositoryImpl(api)
    }
}