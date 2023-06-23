package rs.ac.metropolitan.cs330pz.presentation

sealed class Screen(val route: String) {
    object CocktailListScreen: Screen("cocktail_list_screen")
    //object CoinDetailScreen: Screen("coin_detail_screen")
}