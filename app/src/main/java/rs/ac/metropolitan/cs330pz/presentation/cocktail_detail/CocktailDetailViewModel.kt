package rs.ac.metropolitan.cs330pz.presentation.cocktail_detail

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import rs.ac.metropolitan.cs330pz.common.Constants
import rs.ac.metropolitan.cs330pz.common.Resource
import rs.ac.metropolitan.cs330pz.domain.use_case.getCocktailById.GetCocktailByIdUseCase
import javax.inject.Inject

@HiltViewModel
class CocktailDetailViewModel @Inject constructor(
    private val getCocktailByIdUseCase: GetCocktailByIdUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = mutableStateOf(CocktailDetailState())
    val state: State<CocktailDetailState> = _state

    init {
        savedStateHandle.get<String>(Constants.COCKTAIL_ID)?.let { cocktailId ->
            getCocktail(cocktailId.toInt())
        }
    }


    private fun getCocktail(cocktailId: Int) {
        getCocktailByIdUseCase(cocktailId).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = CocktailDetailState(cocktail = result.data, isLoading = false)
                    Log.d("CocktailDetailViewModel", "getCocktail - Success: ${result.data}")
                }
                is Resource.Error -> {
                    _state.value = CocktailDetailState(
                        error = result.message ?: "An unexpected error occured"
                    )
                    Log.e("CocktailDetailViewModel", "getCocktail - Error: ${result.message}")
                }
                is Resource.Loading -> {
                    _state.value = CocktailDetailState(isLoading = true)
                    Log.d("CocktailDetailViewModel", "getCocktail - Loading")
                }
            }
        }.launchIn(viewModelScope)
    }
}