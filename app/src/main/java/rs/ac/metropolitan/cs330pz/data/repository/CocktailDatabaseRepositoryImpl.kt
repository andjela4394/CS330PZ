package rs.ac.metropolitan.cs330pz.data.repository

import android.util.Log
import kotlinx.coroutines.flow.Flow
import rs.ac.metropolitan.cs330pz.data.local.dao.CocktailDetailDao
import rs.ac.metropolitan.cs330pz.domain.model.CocktailDetail
import rs.ac.metropolitan.cs330pz.domain.repository.CocktailDatabaseRepository

class CocktailDatabaseRepositoryImpl (
    private val dao:CocktailDetailDao
        ):CocktailDatabaseRepository{

    override suspend fun insertNewCocktail(cocktailDetail: CocktailDetail) {
        Log.d("CocktailDetailScreen", "Uslo u rep")
        dao.insertCocktailDetail(cocktailDetail)
    }

    override fun getAllCocktailDetail(): Flow<List<CocktailDetail>> {
        return dao.getCocktailDetail()
    }

    override suspend fun deleteCocktailDetail(cocktailId: CocktailDetail) {
        return dao.deleteCocktailDetail(cocktailId)
    }

    override suspend fun deleteCocktailFromDatabaseById(cocktailId: Int) {
        dao.deleteCocktailById(cocktailId)
    }

    override suspend fun getCocktailFromDatabaseById(cocktailId: Int): CocktailDetail {
        return dao.getCocktailById(cocktailId)
    }

    override fun getFavoriteCocktailDetails(): Flow<List<CocktailDetail>> {
        return dao.getFavoriteCocktailDetails()
    }
}