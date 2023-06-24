package rs.ac.metropolitan.cs330pz.domain.model


data class Cocktail(
    val id: Int,
    val name: String,
    val strength: Int,
    val tags: List<String>,
    val ingredients: List<String>,
    val imageUrl: String
)
