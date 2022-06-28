package com.nextory.testapp.ui.bookdetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nextory.testapp.data.Book
import com.nextory.testapp.data.BookRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookDetailsViewModel @Inject constructor(
    private val bookRepository: BookRepository
) : ViewModel() {
    fun getBook(id: Long) = bookRepository.getBook(id)

    val updateFavorite: (Book) -> Unit = {
        viewModelScope.launch(Dispatchers.IO) {
            bookRepository.toggleBookFavorite(book = it)
        }
    }
}