package rs.ac.metropolitan.cs330pz.presentation

sealed class Screen(val route: String) {
    object CocktailListScreen: Screen("cocktail_main_screen")
    object CocktailDetailScreen: Screen("cocktail_detail_screen")
}