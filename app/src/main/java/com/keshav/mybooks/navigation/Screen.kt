package com.keshav.mybooks.navigation

import androidx.annotation.StringRes
import com.keshav.mybooks.R

sealed class Screen(
    val route:String,
    @StringRes val resourceId:Int
){
    object BookList : Screen("book_list", R.string.bookList)
    object BookDetails : Screen("book_details", R.string.bookDetails)
}