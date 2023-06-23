package rs.ac.metropolitan.cs330pz.domain.use_case.filterCocktailsByCategory

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import rs.ac.metropolitan.cs330pz.common.Resource
import rs.ac.metropolitan.cs330pz.data.remote.dto.toCocktail
import rs.ac.metropolitan.cs330pz.domain.model.Cocktail
import rs.ac.metropolitan.cs330pz.domain.repository.CocktailRepository
import java.io.IOException
import javax.inject.Inject

class FilterCoctailsByCategory @Inject constructor(private val repository : CocktailRepository) {

    operator fun invoke(cocktailCategory: String): Flow<Resource<List<Cocktail>>> = flow {
        try {
            emit(Resource.Loading<List<Cocktail>>())
            val cocktails = repository.filterCocktailsByCategory(cocktailCategory).map { it.toCocktail() }
            emit(Resource.Success<List<Cocktail>>(cocktails))
        } catch(e: HttpException) {
            emit(Resource.Error<List<Cocktail>>(e.localizedMessage ?: "An unexpected error occured"))
        } catch(e: IOException) {
            emit(Resource.Error<List<Cocktail>>("Couldn't reach server. Check your internet connection."))
        }
    }

}