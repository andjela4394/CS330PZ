package rs.ac.metropolitan.cs330pz.presentation.nav_bar.search

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import rs.ac.metropolitan.cs330pz.domain.model.Cocktail
import rs.ac.metropolitan.cs330pz.presentation.Screen
import rs.ac.metropolitan.cs330pz.presentation.cocktail_list.CocktailListViewModel
import rs.ac.metropolitan.cs330pz.presentation.cocktail_list.components.CocktailMainItem

@Composable
fun CocktailSearchItemList(
    cocktailList: List<Cocktail>,
    name: String,
    viewModel: CocktailListViewModel = hiltViewModel(),
    navController: NavController
) {

    var currentPage by remember { mutableStateOf(1) }

//    Text(
//        text = name + " cocktail",
//        fontSize = 35.sp,
//        modifier = Modifier.padding(
//            top = 10.dp,
//            start = 32.dp
//        ),
//        fontFamily = FontFamily.Monospace
//    )

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        LazyColumn(
            modifier = Modifier
                .width(500.dp)
                .padding(16.dp)
        ) {
            items(cocktailList) { cocktail ->
                CocktailMainItem(
                    cocktail = cocktail,
                    onItemClick = {
                        navController.navigate(Screen.CocktailDetailScreen.route+ "/${cocktail.id}")
                    },
                    modifier = Modifier.padding(16.dp)
                )
            }

            item {
                Spacer(modifier = Modifier.height(5.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(15.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Spacer(modifier = Modifier.padding(10.dp))
                    Button(
                        onClick = {
                            if (currentPage > 0) {
                                --currentPage
                            }
                            viewModel.searchCocktail(page = currentPage)
                        },
                        enabled = currentPage > 1
                    ) {
                        Text(text = "Backward")
                    }
                    Spacer(modifier = Modifier.padding(10.dp))
                    Text(
                        text = "Page ${currentPage}/${viewModel.state.value.searchCount/10 + 1}",
                        modifier = Modifier.padding(top=10.dp)
                    )
                    Spacer(modifier = Modifier.padding(10.dp))
                    Button(
                        onClick = {
                            if (currentPage < viewModel.state.value.searchCount/10 + 1) {
                                ++currentPage
                            }
                            viewModel.searchCocktail(page = currentPage)
                        },
                        enabled = currentPage < (viewModel.state.value.searchCount/10 + 1)
                    ) {
                        Text(text = "Forward")
                    }


                }
            }
        }
    }
}