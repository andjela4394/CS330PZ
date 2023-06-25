package rs.ac.metropolitan.cs330pz.domain.use_case.database_use_cases.get_all_cocktail

import kotlinx.coroutines.flow.Flow
import rs.ac.metropolitan.cs330pz.domain.model.CocktailDetail
import rs.ac.metropolitan.cs330pz.domain.repository.CocktailDatabaseRepository
import javax.inject.Inject

class GetAllCocktailDatabaseUseCase @Inject constructor(
    private val repository: CocktailDatabaseRepository
){

    operator fun invoke(): Flow<List<CocktailDetail>> {
        return repository.getAllCocktailDetail()
    }
}