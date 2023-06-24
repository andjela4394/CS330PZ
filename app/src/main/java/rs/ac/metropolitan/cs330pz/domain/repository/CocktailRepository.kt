package rs.ac.metropolitan.cs330pz.domain.repository

import rs.ac.metropolitan.cs330pz.data.remote.dto.CocktailDto
import rs.ac.metropolitan.cs330pz.domain.model.Cocktail

interface CocktailRepository {

    suspend fun getAll(): List<CocktailDto>
    suspend fun getCocktailsByName(name: String): List<CocktailDto>

    suspend fun getCocktailById(cocktailId: Int): CocktailDto
    suspend fun getCocktailsByNameAndTags(tags: String, name:String, page: Int): List<CocktailDto>

    suspend fun getCocktailsByTag(tag: String): List<CocktailDto>

    suspend fun getCocktailCountByNameAndTags(tags: String, name:String): Int

}