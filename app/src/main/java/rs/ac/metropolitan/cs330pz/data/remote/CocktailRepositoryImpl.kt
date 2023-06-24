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

    override suspend fun getCocktailsByNameAndTags(
        tags: String,
        name: String,
        page: Int
    ): List<CocktailDto> {
        return cocktailApi.getCocktailsByNameAndTags(tag = tags, name = name, page = page)
    }

    override suspend fun getCocktailsByTag(tag: String): List<CocktailDto> {
        return cocktailApi.getCocktailsByTag(tag)
    }

    override suspend fun getCocktailCountByNameAndTags(tags: String, name: String): Int {
        val response = cocktailApi.getCocktailsCountByNameAndTags(
            tag = tags,
            name = name,
        )
        val count = response.size
        return count
    }


}
