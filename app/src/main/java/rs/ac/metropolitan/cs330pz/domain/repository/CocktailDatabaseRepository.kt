package rs.ac.metropolitan.cs330pz.domain.repository

import kotlinx.coroutines.flow.Flow
import rs.ac.metropolitan.cs330pz.domain.model.CocktailDetail

interface CocktailDatabaseRepository {

    suspend fun insertNewCocktail(cocktailDetail: CocktailDetail)

    fun getAllCocktailDetail(): Flow<List<CocktailDetail>>
    suspend fun deleteCocktailDetail(cocktailId: CocktailDetail)
    suspend fun deleteCocktailFromDatabaseById(cocktailId: Int)
    suspend fun getCocktailFromDatabaseById(cocktailId: Int):CocktailDetail
    fun getFavoriteCocktailDetails(): Flow<List<CocktailDetail>>

}