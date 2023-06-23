package rs.ac.metropolitan.cs330pz.data.remote.dto

import rs.ac.metropolitan.cs330pz.domain.model.Cocktail

data class CocktailDto(
    val dateModified: String,
    val idDrink: String,
    val strAlcoholic: String,
    val strCategory: String,
    val strCreativeCommonsConfirmed: String,
    val strDrink: String,
    val strDrinkAlternate: Any,
    val strDrinkThumb: String,
    val strGlass: String,
    val strIBA: String,
    val strImageAttribution: String,
    val strImageSource: String,
    val strIngredient1: String,
    val strIngredient10: Any,
    val strIngredient11: Any,
    val strIngredient12: Any,
    val strIngredient13: Any,
    val strIngredient14: Any,
    val strIngredient15: Any,
    val strIngredient2: String,
    val strIngredient3: String,
    val strIngredient4: String,
    val strIngredient5: String,
    val strIngredient6: String,
    val strIngredient7: String,
    val strIngredient8: Any,
    val strIngredient9: Any,
    val strInstructions: String,
    val strInstructionsDE: String,
    val strInstructionsES: Any,
    val strInstructionsFR: Any,
    val strInstructionsIT: String,
    val strInstructionsZHHANS: Any,
    val strInstructionsZHHANT: Any,
    val strMeasure1: String,
    val strMeasure10: Any,
    val strMeasure11: Any,
    val strMeasure12: Any,
    val strMeasure13: Any,
    val strMeasure14: Any,
    val strMeasure15: Any,
    val strMeasure2: String,
    val strMeasure3: String,
    val strMeasure4: String,
    val strMeasure5: String,
    val strMeasure6: String,
    val strMeasure7: String,
    val strMeasure8: Any,
    val strMeasure9: Any,
    val strTags: String,
    val strVideo: Any
)

fun CocktailDto.toCocktail(): Cocktail {
    val ingredients = mutableListOf<String>()
    val measurements = mutableListOf<String>()

    // Collect non-null ingredient and measurement values
    if (!strIngredient1.isNullOrEmpty()) {
        ingredients.add(strIngredient1)
        measurements.add(strMeasure1)
    }
    if (!strIngredient2.isNullOrEmpty()) {
        ingredients.add(strIngredient2)
        measurements.add(strMeasure2)
    }
    if (!strIngredient3.isNullOrEmpty()) {
        ingredients.add(strIngredient3)
        measurements.add(strMeasure3)
    }
    return Cocktail(
        id = idDrink,
        name = strDrink,
        instructions = strInstructions,
        imageUrl = strDrinkThumb,
        ingredients = ingredients,
        measurements = measurements,
        tags = emptyList(), // You can populate this based on your requirements
        category = strCategory, // You can populate this based on your requirements
        isAlcoholic = strAlcoholic, // You can populate this based on your requirements
        glass = strGlass
    )
}