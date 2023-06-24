package rs.ac.metropolitan.cs330pz.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.compose.AppTheme
import dagger.hilt.android.AndroidEntryPoint
import rs.ac.metropolitan.cs330pz.presentation.cocktail_detail.CocktailDetailScreen
import rs.ac.metropolitan.cs330pz.presentation.cocktail_list.components.CocktailMainListItem

@AndroidEntryPoint
class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent{
            AppTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Screen.CocktailListScreen.route
                    ) {
                        composable(
                            route = Screen.CocktailListScreen.route
                        ) {
                            CocktailMainListItem(navController)
                        }
                        composable(
                            route = Screen.CocktailDetailScreen.route + "/{cocktailId}"
                        ) {
                            CocktailDetailScreen()
                        }
                    }
                }

            }
        }

    }
}




