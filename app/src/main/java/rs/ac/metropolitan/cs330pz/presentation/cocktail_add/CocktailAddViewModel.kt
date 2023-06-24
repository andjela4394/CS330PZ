package rs.ac.metropolitan.cs330pz.presentation.cocktail_add

import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import rs.ac.metropolitan.cs330pz.data.remote.CocktailRepositoryImpl
import rs.ac.metropolitan.cs330pz.domain.model.CocktailDetail
import rs.ac.metropolitan.cs330pz.domain.repository.CocktailRepository
import rs.ac.metropolitan.cs330pz.domain.use_case.getAll.GetAllUseCase
import rs.ac.metropolitan.cs330pz.domain.use_case.get_by_tag.GetCocktailsByTag
import rs.ac.metropolitan.cs330pz.domain.use_case.get_cocktail_search.GetCocktailCountUseCase
import rs.ac.metropolitan.cs330pz.domain.use_case.get_cocktail_search.GetCocktailSearch
import rs.ac.metropolitan.cs330pz.domain.use_case.postCocktail.PostCocktailUseCase
import javax.inject.Inject
import kotlin.random.Random

@HiltViewModel
class CocktailAddViewModel @Inject constructor(
    private val postCocktail: PostCocktailUseCase,
) : ViewModel() {

    fun submitCocktail(cocktail: CocktailDetail) {
        GlobalScope.launch {
            postCocktail.submitCocktail(cocktail)
        }
    }


    fun generateRandomId(): Int {
        val minId = 36
        val maxId = 10000
        return Random.nextInt(minId, maxId + 1)
    }

}