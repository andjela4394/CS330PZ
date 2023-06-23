package rs.ac.metropolitan.cs330pz.domain.use_case.getCocktailById

import android.util.Log
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import rs.ac.metropolitan.cs330pz.common.Resource
import rs.ac.metropolitan.cs330pz.data.remote.dto.toCocktail
import rs.ac.metropolitan.cs330pz.data.remote.dto.toCocktailDetail
import rs.ac.metropolitan.cs330pz.domain.model.Cocktail
import rs.ac.metropolitan.cs330pz.domain.model.CocktailDetail
import rs.ac.metropolitan.cs330pz.domain.repository.CocktailRepository
import java.io.IOException
import javax.inject.Inject

class GetCocktailByIdUseCase @Inject constructor(private val repository : CocktailRepository) {

    operator fun invoke(cocktailId: Int): Flow<Resource<CocktailDetail>> = flow {

        try {
            emit(Resource.Loading<CocktailDetail>())
            val cocktail = repository.getCocktailById(cocktailId).toCocktailDetail()
            emit(Resource.Success<CocktailDetail>(cocktail))
        } catch (e: Exception) {
            emit(Resource.Error<CocktailDetail>(e.localizedMessage ?: "An unexpected error occurred"))
        }catch(e: IOException) {
            emit(Resource.Error<CocktailDetail>("Couldn't reach server. Check your internet connection."))
        }
    }
}