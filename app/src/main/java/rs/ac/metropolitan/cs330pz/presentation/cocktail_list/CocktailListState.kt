package rs.ac.metropolitan.cs330pz.presentation.cocktail_list

import rs.ac.metropolitan.cs330pz.domain.model.Cocktail

data class CocktailListState (
    val isLoading: Boolean = false,
    val cocktails: List<Cocktail> = emptyList(),
    val error: String = "",
    val search_cocktail: List<Cocktail> = emptyList(),
    val searchString:String = "",
    val searchTags:String = "",
    val searchIngredients:String = "",
    val searchCount:Int =0
)