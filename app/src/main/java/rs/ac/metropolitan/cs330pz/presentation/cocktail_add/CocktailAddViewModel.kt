package rs.ac.metropolitan.cs330pz.presentation.cocktail_add

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import rs.ac.metropolitan.cs330pz.domain.model.CocktailDetail
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