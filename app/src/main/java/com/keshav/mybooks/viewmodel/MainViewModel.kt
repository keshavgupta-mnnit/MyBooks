package com.keshav.mybooks.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.keshav.mybooks.model.BookItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import okio.use

class MainViewModel : ViewModel() {

    private val _state = MutableStateFlow<ViewState>(ViewState.Loading)
    val books = _state.asStateFlow()

    private val format = Json {
        ignoreUnknownKeys = true
        prettyPrint = true
        isLenient = true
    }

    fun getAllBooks(context: Context) = viewModelScope.launch {
        try {
            val jsonData = context.assets.open("books.json").bufferedReader().use {
                it.readText()
            }
            val bookList = format.decodeFromString<List<BookItem>>(jsonData)
            _state.value = ViewState.Success(bookList)
        } catch (e: Exception) {
            _state.value = ViewState.Error(e)
        }
    }

    fun getBooksByIsbn(context: Context, isbn:String) = viewModelScope.launch {
        try {
            val jsonData = context.assets.open("books.json").bufferedReader().use {
                it.readText()
            }
            val bookItem = format.decodeFromString<List<BookItem>>(jsonData).filter {
                it.isbn == isbn
            }
            _state.value = ViewState.Success(bookItem)
        } catch (e: Exception) {
            _state.value = ViewState.Error(e)
        }
    }
}
