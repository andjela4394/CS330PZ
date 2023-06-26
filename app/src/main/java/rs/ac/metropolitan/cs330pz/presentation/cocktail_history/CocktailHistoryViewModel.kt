package rs.ac.metropolitan.cs330pz.presentation.cocktail_history

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import rs.ac.metropolitan.cs330pz.domain.use_case.database_use_cases.get_all_cocktail.GetAllCocktailDatabaseUseCase
import rs.ac.metropolitan.cs330pz.domain.use_case.database_use_cases.get_favorite_cocktail.GetFavoriteCocktailFromDatabaseUseCase
import rs.ac.metropolitan.cs330pz.presentation.cocktail_favorite.CocktailFavoriteState
import javax.inject.Inject

@HiltViewModel
class CocktailHistoryViewModel @Inject constructor(
    private val getAllCocktailFromDatabaseUseCase: GetAllCocktailDatabaseUseCase,
): ViewModel(){


    private val _state = mutableStateOf(CocktailFavoriteState())
    val state : State<CocktailFavoriteState> = _state


    init {
        getAllCocktailFromDatabase()
    }


    private fun getAllCocktailFromDatabase(){
        getAllCocktailFromDatabaseUseCase().onEach {
                cocktail ->
            _state.value = state.value.copy(
                cocktailDetail = cocktail
            )
        }.launchIn(viewModelScope)
    }

}