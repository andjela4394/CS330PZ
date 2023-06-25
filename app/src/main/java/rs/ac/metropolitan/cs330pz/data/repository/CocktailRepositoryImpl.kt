package rs.ac.metropolitan.cs330pz.data.repository

import android.util.Log
import rs.ac.metropolitan.cs330pz.data.remote.CocktailApi
import rs.ac.metropolitan.cs330pz.data.remote.dto.CocktailDto
import rs.ac.metropolitan.cs330pz.domain.model.CocktailDetail
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
        ingredients: String,
        name: String,
        page: Int
    ): List<CocktailDto> {
        return cocktailApi.getCocktailsByNameAndTags(tags = tags, ingredients = ingredients, name = name, page = page)
    }

    override suspend fun getCocktailsByTag(tags: String): List<CocktailDto> {
        return cocktailApi.getCocktailsByTag(tags)
    }

    override suspend fun getCocktailCountByNameAndTags(tags: String, ingredients:String, name: String): Int {
        val response = cocktailApi.getCocktailsCountByNameAndTags(
            tags = tags,
            ingredients = ingredients,
            name = name,
        )
        val count = response.size
        Log.d("CocktailMainScreen","Count $count")
        return count
    }

    override suspend fun uploadNewCocktail(newCocktail: CocktailDetail) {
        cocktailApi.uploadNewCocktail(newCocktail)
    }


}
