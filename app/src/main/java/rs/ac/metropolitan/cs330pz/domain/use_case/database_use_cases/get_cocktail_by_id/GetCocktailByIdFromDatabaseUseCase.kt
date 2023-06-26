package rs.ac.metropolitan.cs330pz.domain.use_case.database_use_cases.get_cocktail_by_id

import kotlinx.coroutines.flow.Flow
import rs.ac.metropolitan.cs330pz.domain.model.CocktailDetail
import rs.ac.metropolitan.cs330pz.domain.repository.CocktailDatabaseRepository
import javax.inject.Inject

class GetCocktailByIdFromDatabaseUseCase @Inject constructor(
    private val repository: CocktailDatabaseRepository
){

    operator suspend fun invoke(cocktailId: Int): CocktailDetail {
        return repository.getCocktailFromDatabaseById(cocktailId)
    }
}