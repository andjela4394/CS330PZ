package rs.ac.metropolitan.cs330pz.domain.use_case.get_cocktail_search

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import rs.ac.metropolitan.cs330pz.common.Resource
import rs.ac.metropolitan.cs330pz.domain.repository.CocktailRepository
import java.io.IOException
import javax.inject.Inject

class GetCocktailCountUseCase @Inject constructor(
    private val repository: CocktailRepository
) {

    operator fun invoke(tags: String ="",ingredients:String ="", name :String="") : Flow<Resource<Int>> = flow {

        try {
            emit(Resource.Loading())
            val cocktails = repository.getCocktailCountByNameAndTags(tags = tags, ingredients = ingredients, name = name)
            emit(Resource.Success(cocktails))
        }
        catch (e: HttpException){
            emit(
                Resource.Error(
                    e.localizedMessage?:
                    "An unknown error occurred"
                )
            )
        }
        catch (e : IOException){
            emit(
                Resource.Error(
                    e.localizedMessage?:
                    "Couldn't reach the server, test your internet connection"
                )
            )
        }
    }

}