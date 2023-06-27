package rs.ac.metropolitan.cs330pz
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import rs.ac.metropolitan.cs330pz.domain.model.CocktailDetail
import rs.ac.metropolitan.cs330pz.domain.repository.CocktailDatabaseRepository


class FakeCocktailDatabaseRepositoryImpl : CocktailDatabaseRepository {
    private val cocktailDetails = mutableListOf<CocktailDetail>()

    override suspend fun insertNewCocktail(cocktailDetail: CocktailDetail) {
        cocktailDetails.add(cocktailDetail)
    }

    override fun getAllCocktailDetail(): Flow<List<CocktailDetail>> {
        return flow { emit(cocktailDetails) }
    }

    override suspend fun deleteCocktailDetail(cocktailId: CocktailDetail) {
        cocktailDetails.remove(cocktailId)
    }

    override suspend fun deleteCocktailFromDatabaseById(cocktailId: Int) {
        val cocktail = cocktailDetails.find { it.id == cocktailId }
        cocktail?.let { cocktailDetails.remove(it) }
    }

    override suspend fun getCocktailFromDatabaseById(cocktailId: Int): CocktailDetail {
        return cocktailDetails.find { it.id == cocktailId }!!
    }

    override fun getFavoriteCocktailDetails(): Flow<List<CocktailDetail>> {
        val favoriteCocktails = cocktailDetails.filter { it.favorite != null }
        return flow { emit(favoriteCocktails) }
    }

    fun setCocktailDetails(details: List<CocktailDetail>) {
        cocktailDetails.clear()
        cocktailDetails.addAll(details)
    }
}
