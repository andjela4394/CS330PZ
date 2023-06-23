package rs.ac.metropolitan.cs330pz.domain.use_case.getCocktailsById

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import rs.ac.metropolitan.cs330pz.common.Resource
import rs.ac.metropolitan.cs330pz.data.remote.dto.toCocktail
import rs.ac.metropolitan.cs330pz.domain.model.Cocktail
import rs.ac.metropolitan.cs330pz.domain.repository.CocktailRepository
import java.io.IOException
import javax.inject.Inject

class GetCocktailByIdUseCase @Inject constructor(private val repository : CocktailRepository) {

    operator fun invoke(cocktailId: String): Flow<Resource<Cocktail>> = flow {
        try {
            emit(Resource.Loading<Cocktail>())
            val cocktail = repository.getCocktailById(cocktailId)?.toCocktail()
            if (cocktail != null) {
                emit(Resource.Success<Cocktail>(cocktail))
            } else {
                emit(Resource.Error<Cocktail>("Cocktail not found"))
            }
        } catch (e: Exception) {
            emit(Resource.Error<Cocktail>(e.localizedMessage ?: "An unexpected error occurred"))
        }catch(e: IOException) {
            emit(Resource.Error<Cocktail>("Couldn't reach server. Check your internet connection."))
        }
    }
}