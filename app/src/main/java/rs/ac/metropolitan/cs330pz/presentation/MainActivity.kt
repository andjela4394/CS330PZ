package rs.ac.metropolitan.cs330pz.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.compose.AppTheme
import dagger.hilt.android.AndroidEntryPoint
import rs.ac.metropolitan.cs330pz.presentation.cocktail_add.CocktailAddScreen
import rs.ac.metropolitan.cs330pz.presentation.cocktail_detail.CocktailDetailScreen
import rs.ac.metropolitan.cs330pz.presentation.cocktail_list.components.CocktailMainScreen

@AndroidEntryPoint
class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent{
            AppTheme {
                Surface(
                    color = MaterialTheme.colorScheme.background,
                    modifier = Modifier.fillMaxSize()
                ) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Screen.CocktailMainScreen.route
                    ) {
                        composable(
                            route = Screen.CocktailMainScreen.route
                        ) {
                            CocktailMainScreen(navController = navController)
                        }
                        composable(
                            route = Screen.CocktailDetailScreen.route + "/{cocktailId}"
                        ) {
                            CocktailDetailScreen(navController = navController)
                        }
                        composable(
                            route = Screen.CocktailAddScreen.route
                        ){
                            CocktailAddScreen(navController = navController)
                        }

                    }
                }

            }
        }

    }
}




