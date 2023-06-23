package rs.ac.metropolitan.cs330pz.domain.model

data class Cocktail(
    val id: String,
    val name: String,
    val instructions: String,
    val imageUrl: String,
    val ingredients: List<String>,
    val measurements: List<String>,
    val tags: List<String>,
    val category: String,
    val isAlcoholic: String,
    val glass: String
    // Add more properties as needed
)
