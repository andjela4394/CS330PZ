package rs.ac.metropolitan.cs330pz.presentation.cocktail_list.components

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import rs.ac.metropolitan.cs330pz.presentation.cocktail_list.CocktailListViewModel
import rs.ac.metropolitan.cs330pz.presentation.nav_bar.CocktailNavBarViewModel
import rs.ac.metropolitan.cs330pz.presentation.nav_bar.NavBar
import rs.ac.metropolitan.cs330pz.presentation.nav_bar.search.CocktailSearchDialog
import rs.ac.metropolitan.cs330pz.presentation.nav_bar.search.CocktailSearchItemList

@Composable
fun CocktailMainScreen (
    navController: NavController,
    viewModel: CocktailListViewModel = hiltViewModel(),
    navViewModel: CocktailNavBarViewModel = hiltViewModel()
    ){


        if (navViewModel.state.value.dialog){
            CocktailSearchDialog()
        }
        else if (navViewModel.state.value.isSearching){
            CocktailSearchItemList(viewModel.state.value.search_cocktail,"Searched",navController = navController)
        }
        else if (navViewModel.state.value.isMainPage && !navViewModel.state.value.isSearching) {
            CocktailMainListItem(navController = navController)
        }

    }
