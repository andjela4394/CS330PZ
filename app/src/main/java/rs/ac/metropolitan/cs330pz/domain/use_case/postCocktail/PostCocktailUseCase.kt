package rs.ac.metropolitan.cs330pz.domain.use_case.postCocktail

import rs.ac.metropolitan.cs330pz.domain.model.CocktailDetail
import rs.ac.metropolitan.cs330pz.domain.repository.CocktailRepository
import javax.inject.Inject

class PostCocktailUseCase@Inject constructor(
    private val repository : CocktailRepository
) {

        suspend fun submitCocktail(newCocktail: CocktailDetail){
            repository.uploadNewCocktail(newCocktail)
        }
}