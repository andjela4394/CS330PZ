package rs.ac.metropolitan.cs330pz.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow
import rs.ac.metropolitan.cs330pz.domain.model.CocktailDetail

@Dao
interface CocktailDetailDao {

    @Upsert
    suspend fun insertCocktailDetail(cocktailDetail: CocktailDetail)

    @Delete
    suspend fun deleteCocktailDetail(cocktailDetail: CocktailDetail)

    @Query("SELECT * FROM cocktaildetail")
    fun getCocktailDetail(): Flow<List<CocktailDetail>>

    @Query("DELETE FROM cocktaildetail WHERE id = :cocktailId")
    suspend fun deleteCocktailById(cocktailId: Int)

    @Query("SELECT * FROM cocktaildetail WHERE id = :cocktailId")
    suspend fun getCocktailById(cocktailId: Int): CocktailDetail

    @Query("SELECT * FROM cocktaildetail WHERE favorite = 1")
    fun getFavoriteCocktailDetails(): Flow<List<CocktailDetail>>
}