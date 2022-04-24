package com.keshav.mybooks.view

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.keshav.mybooks.components.BookDetailCard
import com.keshav.mybooks.components.TopBar
import com.keshav.mybooks.navigation.MainActions
import com.keshav.mybooks.ui.theme.typography
import com.keshav.mybooks.viewmodel.MainViewModel
import com.keshav.mybooks.viewmodel.ViewState

@Composable
fun BookDetailsScreen(viewModel: MainViewModel, actions: MainActions) {
    Scaffold(topBar = {
        TopBar(title = "Book Description") {
            actions.upPress()
        }
    }) {
        BookDetails(viewModel)
    }

}

@Composable
fun BookDetails(viewModel: MainViewModel) {
    when (val result = viewModel.books.value) {
        ViewState.Empty -> {
            Text(text = "No result found", color = Color.Black)
        }
        is ViewState.Error -> {
            Text(text = "Error found${result.exception.localizedMessage}")
        }
        ViewState.Loading -> {
            Text(text = "Loading", color = Color.Black)
        }
        is ViewState.Success -> {
            val book = result.data.first()
            LazyColumn {
                item {
                    BookDetailCard(bookItem = book)
                }
                item {
                    Spacer(modifier = Modifier.height(24.dp))
                    Text(
                        text = "Book Details",
                        style = typography.subtitle1,
                        color = MaterialTheme.colors.primaryVariant,
                        modifier = Modifier.padding(start = 16.dp, end = 16.dp)
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(
                        text = book.longDescription,
                        style = typography.body1,
                        textAlign = TextAlign.Justify,
                        color = MaterialTheme.colors.primaryVariant.copy(0.7f),
                        modifier = Modifier.padding(start = 16.dp, end = 16.dp)
                    )
                }
            }

        }
    }
}