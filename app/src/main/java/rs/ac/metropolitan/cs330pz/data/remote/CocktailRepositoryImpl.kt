package rs.ac.metropolitan.cs330pz.data.remote

import rs.ac.metropolitan.cs330pz.data.remote.dto.CocktailDto
import rs.ac.metropolitan.cs330pz.domain.repository.CocktailRepository
import javax.inject.Inject


class CocktailRepositoryImpl @Inject constructor(
    private val cocktailApi: CocktailApi
): CocktailRepository{


    override suspend fun getAll(): List<CocktailDto> {
        return cocktailApi.getAll()
    }

    override suspend fun getCocktailsByName(name: String): List<CocktailDto> {
        return cocktailApi.getCocktailsByName()
    }

    override suspend fun getCocktailById(cocktailId: Int): CocktailDto {
        return cocktailApi.getCocktailById(cocktailId)
    }




}
