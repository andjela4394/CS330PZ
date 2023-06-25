package rs.ac.metropolitan.cs330pz.domain.use_case.database_use_cases.add_cocktail

import rs.ac.metropolitan.cs330pz.domain.model.CocktailDetail
import rs.ac.metropolitan.cs330pz.domain.repository.CocktailDatabaseRepository
import javax.inject.Inject

class AddCocktailToDatabaseUseCase @Inject constructor (
    private val repository: CocktailDatabaseRepository
        ){

    suspend operator fun invoke(cocktailDetail: CocktailDetail){
        repository.insertNewCocktail(cocktailDetail)
    }
}