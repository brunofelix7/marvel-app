package dev.brunofelix.marvelapp.feature_comic.presentation.viewmodel

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.brunofelix.marvelapp.feature_comic.domain.use_case.FindComicsUseCase
import javax.inject.Inject

@HiltViewModel
class ComicViewModel @Inject constructor(
    private val useCase: FindComicsUseCase
) : ViewModel() {

    init {
        fetchComics()
    }

    private fun fetchComics() {

    }
}