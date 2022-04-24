package com.keshav.mybooks.viewmodel

import com.keshav.mybooks.model.BookItem
import java.lang.Exception

sealed class ViewState {
    object Empty : ViewState()
    object Loading : ViewState()
    data class Success(val data: List<BookItem>) : ViewState()
    data class Error(val exception: Throwable) : ViewState()
}