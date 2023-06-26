package rs.ac.metropolitan.cs330pz.presentation.cocktail_history

import rs.ac.metropolitan.cs330pz.domain.model.CocktailDetail

data class CocktailHistoryState (

    val cocktailDetail:List<CocktailDetail> = emptyList()
)