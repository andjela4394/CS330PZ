package rs.ac.metropolitan.cs330pz.presentation.cocktail_detail

import rs.ac.metropolitan.cs330pz.domain.model.Cocktail
import rs.ac.metropolitan.cs330pz.domain.model.CocktailDetail

data class CocktailDetailState(
    val isLoading: Boolean = false,
    val cocktail: CocktailDetail? = null,
    val error: String = "",
    var isFavorite: Boolean = false
){}