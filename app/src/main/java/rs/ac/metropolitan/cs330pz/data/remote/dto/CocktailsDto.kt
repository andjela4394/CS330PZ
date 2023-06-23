package rs.ac.metropolitan.cs330pz.data.remote.dto

import rs.ac.metropolitan.cs330pz.domain.model.Cocktail
import rs.ac.metropolitan.cs330pz.domain.model.Cocktails

data class CocktailsDto (
    val drinks: List<CocktailDto>
)

fun CocktailsDto.toCocktails(): Cocktails{
    return Cocktails(drinks = drinks)
}
