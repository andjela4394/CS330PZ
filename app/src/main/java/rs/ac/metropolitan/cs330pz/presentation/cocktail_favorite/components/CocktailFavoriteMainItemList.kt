package rs.ac.metropolitan.cs330pz.presentation.cocktail_favorite.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import rs.ac.metropolitan.cs330pz.presentation.Screen
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import rs.ac.metropolitan.cs330pz.presentation.cocktail_favorite.CocktailFavoriteViewModel
import rs.ac.metropolitan.cs330pz.presentation.cocktail_list.CocktailListViewModel
import rs.ac.metropolitan.cs330pz.presentation.nav_bar.NavBar

@Composable
fun CocktailFavoriteMainListItem(
    navController: NavController,
    viewModel: CocktailFavoriteViewModel = hiltViewModel()
) {

    val state = viewModel.state.value

    Column(
        modifier = Modifier.fillMaxSize()
    ) {

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
                .padding(vertical = 20.dp)
        )
        {
            itemsIndexed(state.cocktailDetailFavorite) { index, cocktail ->
                CocktailFavoriteMainItem(
                    cocktail = cocktail,
                    onItemClick = {
                        navController.navigate(Screen.CocktailDetailScreen.route + "/${cocktail.id}")
                    }
                )
                Spacer(modifier = Modifier.height(20.dp))
            }
        }
//        if (state.error.isNotBlank()) {
//            Text(
//                text = state.error,
//                color = MaterialTheme.colorScheme.error,
//                textAlign = TextAlign.Center,
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(horizontal = 20.dp)
//            )
//        }
//        if (state.isLoading) {
//            CircularProgressIndicator(modifier = Modifier)
//        }
    }
}
