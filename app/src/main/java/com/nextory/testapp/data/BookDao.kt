package com.nextory.testapp.data

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged

@Dao
interface BookDao {
    @Query("SELECT * FROM book")
    fun observePagedBooks(): PagingSource<Int, Book>

    @Query("SELECT * FROM book WHERE id = :id")
    fun getBookById(id: Long): Flow<Book>

    fun getBookDistinctUntilChanged(id: Long) = getBookById(id).distinctUntilChanged()

    @Update
    fun updateBook(book: Book)
}
