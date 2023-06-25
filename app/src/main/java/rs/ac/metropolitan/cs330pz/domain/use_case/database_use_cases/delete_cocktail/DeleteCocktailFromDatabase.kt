package rs.ac.metropolitan.cs330pz.domain.use_case.database_use_cases.delete_cocktail

import rs.ac.metropolitan.cs330pz.domain.repository.CocktailDatabaseRepository
import javax.inject.Inject

class DeleteCocktailFromDatabase @Inject constructor(
    private val repository: CocktailDatabaseRepository
){

    suspend operator fun invoke(cocktailId: Int){
        repository.deleteCocktailFromDatabaseById(cocktailId)
    }
}