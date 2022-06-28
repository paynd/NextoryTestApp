package com.nextory.testapp.ui.booklist

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.nextory.testapp.R
import com.nextory.testapp.data.Book
import com.ramcosta.composedestinations.navigation.DestinationsNavigator


@Composable
fun BookDetailsTopBar(
    book: Book,
    navigator: DestinationsNavigator,
    updateFavorite: (book: Book) -> Unit
) {
    CenterAlignedTopAppBar(
        title = { Text(text = book.title) },
        navigationIcon = {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = stringResource(id = R.string.navigate_back),
                modifier = Modifier.clickable {
                    navigator.navigateUp()
                }
            )
        },
        modifier = Modifier.windowInsetsPadding(
            WindowInsets.safeDrawing.only(
                WindowInsetsSides.Horizontal + WindowInsetsSides.Top
            )
        ),
        actions = {
            Box(modifier = Modifier.clickable {
                updateFavorite.invoke(book)
            }) {
                Icon(
                    painter = if (book.favorite) {
                        painterResource(id = R.drawable.ic_checked_favorite_star)
                    } else {
                        painterResource(id = R.drawable.ic_unchecked_favorite_star)
                    },
                    contentDescription = stringResource(id = R.string.favorite)
                )
            }
        }
    )
}