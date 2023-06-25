package rs.ac.metropolitan.cs330pz.domain.use_case.get_cocktail_search

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import rs.ac.metropolitan.cs330pz.common.Resource
import rs.ac.metropolitan.cs330pz.data.remote.dto.toCocktail
import rs.ac.metropolitan.cs330pz.domain.model.Cocktail
import rs.ac.metropolitan.cs330pz.domain.repository.CocktailRepository
import java.io.IOException
import javax.inject.Inject

class GetCocktailSearch @Inject constructor(
    private val repository: CocktailRepository
) {

    operator fun invoke(tags: String ="",name :String="", ingredients:String ="",page:Int = 1) : Flow<Resource<List<Cocktail>>> = flow {

        try {
            emit(Resource.Loading<List<Cocktail>>())
            val cocktails = repository.getCocktailsByNameAndTags(tags=tags,ingredients=ingredients,name=name,page=page).map {
                it.toCocktail()
            }
            emit(Resource.Success<List<Cocktail>>(cocktails))
        }
        catch (e: HttpException){
            emit(
                Resource.Error<List<Cocktail>>(
                    e.localizedMessage?:
                    "An unknown error occurred"
                )
            )
        }
        catch (e : IOException){
            emit(
                Resource.Error<List<Cocktail>>(
                    e.localizedMessage?:
                    "Couldn't reach the server, test your internet connection"
                )
            )
        }
    }

}