@file:OptIn(ExperimentalMaterial3Api::class)

package com.nextory.testapp.ui.bookdetails

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.nextory.testapp.R
import com.nextory.testapp.ui.booklist.BookDetailsTopBar
import com.nextory.testapp.ui.utils.rememberFlowWithLifecycle
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination
@Composable
fun BookDetails(
    navigator: DestinationsNavigator,
    bookId: Long,
    bookDetailsViewModel: BookDetailsViewModel = hiltViewModel()
) {
    val book = rememberFlowWithLifecycle(bookDetailsViewModel.getBook(bookId)).collectAsState(
        initial = null
    )

    book.value?.let {
        Scaffold(
            Modifier.fillMaxSize(),
            topBar = {
                BookDetailsTopBar(
                    book = it,
                    navigator = navigator,
                    updateFavorite = bookDetailsViewModel.updateFavorite
                )
            },
        ) {
            Column(
                Modifier
                    .fillMaxSize()
                    .padding(8.dp)
            ) {
                AsyncImage(
                    model = book.value?.imageUrl,
                    contentDescription = stringResource(id = R.string.book_cover_image),
                    modifier = Modifier
                        .size(128.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .align(CenterHorizontally)
                        .padding(8.dp)
                )
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontFamily = FontFamily.Monospace,
                        fontWeight = FontWeight.W800,
                    ),
                    textAlign = TextAlign.Start,
                    text = "${book.value?.author}"
                )
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    style = TextStyle(
                        fontSize = 12.sp,
                        fontFamily = FontFamily.Monospace,
                        fontWeight = FontWeight.W600,
                    ),
                    textAlign = TextAlign.Start,
                    text = "${book.value?.description}"
                )

            }

        }
    }
}

