package rs.ac.metropolitan.cs330pz.presentation.nav_bar

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavBar(
    viewModel: CocktailNavBarViewModel = hiltViewModel(),
    navController: NavController,

    ) {

    val tabs = listOf("Find", "Favorites", "Viewed")


    Column(modifier = Modifier.fillMaxWidth()) {
        TopAppBar(
            title = { Text("Cocktail List") },
            actions = {
                IconButton(onClick = {viewModel.dialog() }) {
                    Icon(Icons.Default.Search, contentDescription = "Search")
                }
            }
        )

        TabRow(selectedTabIndex = viewModel.tabIndex) {
            tabs.forEachIndexed { index, title ->
                Tab(
                    text = { Text(title, fontSize = 19.sp,
                        fontFamily = FontFamily.SansSerif,
                    )
                    },
                    selected = viewModel.tabIndex == index,
                    onClick = {
                        viewModel.tabIndex = index
                        viewModel.setShown(index)
                    },
                )
            }
        }
    }
}