package rs.ac.metropolitan.cs330pz.presentation.cocktail_list.components

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import rs.ac.metropolitan.cs330pz.presentation.Screen
import rs.ac.metropolitan.cs330pz.presentation.cocktail_favorite.components.CocktailFavoriteMainListItem
import rs.ac.metropolitan.cs330pz.presentation.cocktail_history.components.CocktailHisotryMainListItem
import rs.ac.metropolitan.cs330pz.presentation.cocktail_list.CocktailListViewModel
import rs.ac.metropolitan.cs330pz.presentation.nav_bar.CocktailNavBarViewModel
import rs.ac.metropolitan.cs330pz.presentation.nav_bar.NavBar
import rs.ac.metropolitan.cs330pz.presentation.nav_bar.search.CocktailSearchDialog
import rs.ac.metropolitan.cs330pz.presentation.nav_bar.search.CocktailSearchItemList

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CocktailMainScreen (
    navController: NavController,
    viewModel: CocktailListViewModel = hiltViewModel(),
    navViewModel: CocktailNavBarViewModel = hiltViewModel()
    ) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.DarkGray)
    ) {
        Column(modifier = Modifier.background(color = Color.DarkGray)) {
            NavBar(navController = navController)
        }
        //NavBar(navController = navController)

        Scaffold(
            floatingActionButton = {
                FloatingActionButton(onClick = { navController.navigate(Screen.CocktailAddScreen.route) }) {
                    Icon(Icons.Filled.Add, contentDescription = "Add student")
                }
            }) {
            //CocktailMainListItem(navController, viewModel)
            if (navViewModel.state.value.dialog) {
                CocktailSearchDialog()
            } else if (navViewModel.state.value.isSearching) {
                CocktailSearchItemList(
                    viewModel.state.value.search_cocktail,
                    "Searched",
                    navController = navController
                )
            } else if (navViewModel.state.value.isMainPage && !navViewModel.state.value.isSearching) {
                CocktailMainListItem(navController = navController)
            } else if (navViewModel.state.value.isFavorite && !navViewModel.state.value.isSearching) {
                 CocktailFavoriteMainListItem(navController = navController)
             }
            else{
                CocktailHisotryMainListItem(navController = navController)
            }
        }

    }
}
