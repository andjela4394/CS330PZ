package rs.ac.metropolitan.cs330pz.presentation.cocktail_list

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import rs.ac.metropolitan.cs330pz.common.Resource
import rs.ac.metropolitan.cs330pz.domain.use_case.getCocktailsByName.GetCocktailsByNameUseCase
import javax.inject.Inject

@HiltViewModel
class CocktailListViewModel @Inject constructor(
    private val getCocktailsByNameUseCase: GetCocktailsByNameUseCase
) : ViewModel() {

    private val _state = mutableStateOf(CocktailListState())
    val state: State<CocktailListState> = _state

    init {
        getCocktails()
    }

    private fun getCocktails() {
        getCocktailsByNameUseCase("margarita").onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = CocktailListState(cocktails = result.data ?: emptyList())
                }
                is Resource.Error -> {
                    _state.value = CocktailListState(
                        error = result.message ?: "An unexpected error occured"
                    )
                }
                is Resource.Loading -> {
                    _state.value = CocktailListState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}