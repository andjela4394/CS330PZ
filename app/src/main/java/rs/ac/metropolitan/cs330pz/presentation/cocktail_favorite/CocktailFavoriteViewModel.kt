package rs.ac.metropolitan.cs330pz.presentation.cocktail_favorite


import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import androidx.compose.runtime.State
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import rs.ac.metropolitan.cs330pz.domain.use_case.database_use_cases.add_cocktail.AddCocktailToDatabaseUseCase
import rs.ac.metropolitan.cs330pz.domain.use_case.database_use_cases.get_favorite_cocktail.GetFavoriteCocktailFromDatabaseUseCase

@HiltViewModel
class CocktailFavoriteViewModel @Inject constructor(
    private val getFavoriteCocktailFromDatabaseUseCase: GetFavoriteCocktailFromDatabaseUseCase,
    private val addCocktailToDatabaseUseCase: AddCocktailToDatabaseUseCase,
): ViewModel(){


    private val _state = mutableStateOf(CocktailFavoriteState())
    val state : State<CocktailFavoriteState> = _state


    init {
        getFavoriteCocktailFromDatabase()
    }



    private fun getFavoriteCocktailFromDatabase(){
        getFavoriteCocktailFromDatabaseUseCase().onEach {
                favorite ->
            _state.value = state.value.copy(
                cocktailDetailFavorite = favorite
            )
        }.launchIn(viewModelScope)

    }

}