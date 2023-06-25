package rs.ac.metropolitan.cs330pz.domain.repository

import rs.ac.metropolitan.cs330pz.data.remote.dto.CocktailDto
import rs.ac.metropolitan.cs330pz.domain.model.Cocktail
import rs.ac.metropolitan.cs330pz.domain.model.CocktailDetail

interface CocktailRepository {

    suspend fun getAll(): List<CocktailDto>
    suspend fun getCocktailsByName(name: String): List<CocktailDto>

    suspend fun getCocktailById(cocktailId: Int): CocktailDto
    suspend fun getCocktailsByNameAndTags(tags: String, ingredients:String, name:String, page: Int): List<CocktailDto>

    suspend fun getCocktailsByTag(tags: String): List<CocktailDto>

    suspend fun getCocktailCountByNameAndTags(tags: String, ingredients:String, name:String): Int

    suspend fun uploadNewCocktail(newCocktail: CocktailDetail)

}