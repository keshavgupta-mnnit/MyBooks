package com.keshav.mybooks.view

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.keshav.mybooks.R
import com.keshav.mybooks.components.ItemBookList
import com.keshav.mybooks.components.TextInputView
import com.keshav.mybooks.model.BookItem
import com.keshav.mybooks.navigation.MainActions
import com.keshav.mybooks.ui.theme.typography
import com.keshav.mybooks.viewmodel.MainViewModel
import com.keshav.mybooks.viewmodel.ViewState

@ExperimentalComposeUiApi
@Composable
fun BookListScreen(viewModel: MainViewModel, actions: MainActions) {
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
            BookList(data = result.data, actions = actions)
        }
    }
}

@ExperimentalComposeUiApi
@Composable
fun BookList(data: List<BookItem>, actions: MainActions) {
    val input = remember {
        mutableStateOf("")
    }
    LazyColumn() {
        item {
            Text(
                text = stringResource(R.string.title_book_list),
                style = typography.h5,
                textAlign = TextAlign.Start,
                color = MaterialTheme.colors.primary,
                maxLines = 2,
                modifier = Modifier.padding(start = 16.dp, end = 24.dp, bottom = 24.dp)
            )
        }
        item {
            TextInputView(
                label = stringResource(id = R.string.search_title),
                value = input.value,
                onValueChanged = { input.value = it })
        }
        item {
            Text(
                text = stringResource(R.string.famous_books),
                style = typography.subtitle1,
                textAlign = TextAlign.Start,
                color = MaterialTheme.colors.primary
            )
        }
        items(data.filter {
            it.title.contains(input.value, ignoreCase = true)
        }) { book ->
            ItemBookList(book) {
                actions.goToBookDetails.invoke(book.isbn)
            }
        }
    }
}