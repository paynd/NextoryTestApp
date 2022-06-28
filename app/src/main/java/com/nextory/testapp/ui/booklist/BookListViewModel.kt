package com.nextory.testapp.ui.booklist

import androidx.lifecycle.ViewModel
import androidx.paging.PagingConfig
import com.nextory.testapp.data.BookRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import javax.inject.Inject

@HiltViewModel
class BookListViewModel @Inject constructor(
    private val bookRepository: BookRepository
) : ViewModel() {
    private val queryFlow = MutableStateFlow("")

    val pagedBooks = queryFlow.flatMapLatest { query ->
        bookRepository.observePagedBooks(query, PAGING_CONFIG)
    }

    fun updateSearch(search: String) {
        queryFlow.value = search
    }

    companion object {
        val PAGING_CONFIG = PagingConfig(
            pageSize = 12,
            enablePlaceholders = false
        )
    }
}