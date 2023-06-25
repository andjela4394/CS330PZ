package rs.ac.metropolitan.cs330pz.data.repository

import kotlinx.coroutines.flow.Flow
import rs.ac.metropolitan.cs330pz.data.local.dao.CocktailDetailDao
import rs.ac.metropolitan.cs330pz.domain.model.CocktailDetail
import rs.ac.metropolitan.cs330pz.domain.repository.CocktailDatabaseRepository

class CocktailDatabaseRepositoryImpl (
    private val dao:CocktailDetailDao
        ):CocktailDatabaseRepository{

    override suspend fun insertNewCocktail(cocktailDetail: CocktailDetail) {
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

    override fun getCocktailFromDatabaseById(cocktailId: Int): Flow<CocktailDetail> {
        return dao.getCocktailById(cocktailId)
    }

    override fun getFavoriteCocktailDetails(): Flow<List<CocktailDetail>> {
        return dao.getFavoriteCocktailDetails()
    }
}