package rs.ac.metropolitan.cs330pz.presentation.cocktail_favorite

import rs.ac.metropolitan.cs330pz.domain.model.CocktailDetail

data class CocktailFavoriteState (

    val cocktailDetail:List<CocktailDetail> = emptyList(),
    val cocktailDetailFavorite:List<CocktailDetail> = emptyList(),
    val cocktail: CocktailDetail? = null
)