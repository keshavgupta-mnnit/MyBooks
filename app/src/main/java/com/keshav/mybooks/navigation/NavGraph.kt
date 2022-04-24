package com.keshav.mybooks.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.HiltViewModelFactory
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.keshav.mybooks.view.BookDetailsScreen
import com.keshav.mybooks.view.BookListScreen
import com.keshav.mybooks.viewmodel.MainViewModel

object EndPoints {
    const val Id = "id"
}

@ExperimentalComposeUiApi
@Composable
fun NavGraph() {
    val navController = rememberNavController()
    val actions = remember(navController) { MainActions(navController) }
    val context = LocalContext.current
    NavHost(navController = navController, startDestination = Screen.BookList.route) {
        composable(Screen.BookList.route) {
            val viewModel: MainViewModel = viewModel(
                factory = HiltViewModelFactory(context =  LocalContext.current, it)
            )
            viewModel.getAllBooks(context = context)
            BookListScreen(viewModel = viewModel, actions = actions)
        }

        composable(
            "${Screen.BookDetails.route}/{id}",
            arguments = listOf(navArgument(EndPoints.Id) { type = NavType.StringType })
        ) {
            val viewModel = hiltViewModel<MainViewModel>(it)
            val isbnNo = it.arguments?.getString(EndPoints.Id) ?: throw IllegalStateException("Isbn can not be empty")
            viewModel.getBooksByIsbn(context, isbnNo)
            BookDetailsScreen(viewModel = viewModel, actions = actions,)
        }
    }
}

class MainActions(navController: NavController) {
    val upPress: () -> Unit = {
        navController.navigateUp()
    }
    val goToBookDetails: (String) -> Unit = { isbnNo ->
        navController.navigate("${Screen.BookDetails.route}/$isbnNo")
    }
    val goToBookList: (String) -> Unit = {
        navController.navigate(Screen.BookList.route)
    }
}