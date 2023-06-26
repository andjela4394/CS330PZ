package rs.ac.metropolitan.cs330pz.presentation.cocktail_history.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import rs.ac.metropolitan.cs330pz.presentation.Screen
import rs.ac.metropolitan.cs330pz.presentation.cocktail_detail.CocktailDetailViewModel
import rs.ac.metropolitan.cs330pz.presentation.cocktail_favorite.CocktailFavoriteViewModel
import rs.ac.metropolitan.cs330pz.presentation.cocktail_history.CocktailHistoryViewModel

@Composable
fun CocktailHisotryMainListItem(
    navController: NavController,
    viewModel: CocktailHistoryViewModel = hiltViewModel(),
    viewModelD: CocktailDetailViewModel = hiltViewModel()
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
            itemsIndexed(state.cocktailDetail) { index, cocktail ->
                CocktailHistoryMainItem(
                    cocktail = cocktail,
                    onItemClick = {
                       // viewModelD.favouriteT()
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