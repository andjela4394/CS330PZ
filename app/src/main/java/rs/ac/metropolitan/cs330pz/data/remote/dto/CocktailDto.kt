package rs.ac.metropolitan.cs330pz.data.remote.dto

import rs.ac.metropolitan.cs330pz.domain.model.Cocktail
import rs.ac.metropolitan.cs330pz.domain.model.CocktailDetail

data class CocktailDto(
    val description: String,
    val id: Int,
    val ingredients: List<String>,
    val name: String,
    val origin: String,
    val strength: Int,
    val tags: List<String>,
    val imageUrl: String
)

fun CocktailDto.toCocktail(): Cocktail {
   return Cocktail(
       id = id,
       name = name,
       strength = strength,
       tags = tags,
       ingredients = ingredients,
       imageUrl = imageUrl
   )
}

fun CocktailDto.toCocktailDetail(): CocktailDetail {
    return CocktailDetail(
        description = description,
        id = id,
        ingredients = ingredients,
        name = name,
        origin = origin,
        strength = strength,
        tags = tags,
        imageUrl = imageUrl,
        favorite = null
    )
}