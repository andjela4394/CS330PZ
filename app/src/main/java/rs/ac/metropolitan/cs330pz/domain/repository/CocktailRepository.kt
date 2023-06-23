package rs.ac.metropolitan.cs330pz.domain.repository

import rs.ac.metropolitan.cs330pz.data.remote.dto.CocktailDto
import rs.ac.metropolitan.cs330pz.domain.model.Cocktail

interface CocktailRepository {
    suspend fun getCocktailsByName(name: String): List<CocktailDto>

    suspend fun getCocktailById(id: String): CocktailDto?

    suspend fun filterCocktailsByCategory(category: String): List<CocktailDto>
}