package rs.ac.metropolitan.cs330pz.presentation.nav_bar

data class CocktailNavBarState (

    val isMainPage: Boolean = true,
    val isSearching: Boolean = false,
    val localDatabase:Boolean = false,
    val dialog: Boolean = false,
    val isFavorite: Boolean = false
)