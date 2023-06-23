package rs.ac.metropolitan.cs330pz.data.remote

import rs.ac.metropolitan.cs330pz.data.remote.dto.CocktailDto
import rs.ac.metropolitan.cs330pz.domain.model.Cocktail
import rs.ac.metropolitan.cs330pz.domain.repository.CocktailRepository
import javax.inject.Inject


class CocktailRepositoryImpl @Inject constructor(private val cocktailApi: CocktailApi): CocktailRepository{
    override suspend fun getCocktailsByName(name: String): List<CocktailDto> {
        return cocktailApi.searchCocktailsByName(name)

    }

    override suspend fun getCocktailById(id: String): CocktailDto? {
        return cocktailApi.getCocktailById(id)
    }

    override suspend fun filterCocktailsByCategory(category: String): List<CocktailDto> {
        return cocktailApi.filterCocktailsByCategory(category)
    }


}
