package com.ahmed.aaoua.nyt


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ahmed.aaoua.nyt.api.NYTApiResponse
import com.ahmed.aaoua.nyt.api.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class NewsViewModel(private val repository: Repository) : ViewModel() {

    private val _news = MutableStateFlow<NYTApiResponse?>(null)
    val news: StateFlow<NYTApiResponse?> = _news

    init {
        fetchNews()
    }

    private fun fetchNews() {
        viewModelScope.launch(Dispatchers.IO) {
            val fetchedNews = repository.getNews()
            _news.emit(fetchedNews)
        }
    }
}