package com.nextory.testapp.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class BookRepository @Inject constructor(
    private val bookDao: BookDao
) {
    fun observePagedBooks(search: String, pagingConfig: PagingConfig): Flow<PagingData<Book>> {
        return Pager(config = pagingConfig) {
            if (search.isEmpty()) {
                bookDao.observePagedBooks()
            } else {
                bookDao.observePagedBooksFilteredByAuthorOrTitle("%$search%")
            }
        }.flow
    }

    fun getBook(id: Long) = bookDao.getBookById(id)

    fun toggleBookFavorite(book: Book) = bookDao.updateBook(book.copy(favorite = !book.favorite))
}
