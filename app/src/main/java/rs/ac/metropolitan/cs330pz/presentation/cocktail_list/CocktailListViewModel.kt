package rs.ac.metropolitan.cs330pz.presentation.cocktail_list

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import rs.ac.metropolitan.cs330pz.common.Resource
import rs.ac.metropolitan.cs330pz.domain.use_case.getAll.GetAllUseCase
import rs.ac.metropolitan.cs330pz.domain.use_case.getCocktailsByName.GetCocktailsByNameUseCase
import rs.ac.metropolitan.cs330pz.domain.use_case.get_by_tag.GetCocktailsByTag
import rs.ac.metropolitan.cs330pz.domain.use_case.get_cocktail_search.GetCocktailCountUseCase
import rs.ac.metropolitan.cs330pz.domain.use_case.get_cocktail_search.GetCocktailSearch
import javax.inject.Inject

@HiltViewModel
class CocktailListViewModel @Inject constructor(
    private val getAll: GetAllUseCase,
    private val getCocktailCountUseCase: GetCocktailCountUseCase,
    private val getCocktailSearch: GetCocktailSearch,
    private val getCocktailsByTag: GetCocktailsByTag
) : ViewModel() {

    var tabIndex by mutableStateOf(2)
    private val _state = mutableStateOf(CocktailListState())
    val state: State<CocktailListState> = _state

    init {
        getAllCocktails()
    }

    private var dataLoadingCounter = 0

    fun refresh(){
        getAllCocktails()
    }

    fun closeDialog(){
        _state.value = _state.value.copy(
            searchTags = "",
            searchString = ""
        )
        getAll()
    }

    fun getSearchCount(){
        getCocktailCountUseCase(
            tags=_state.value.searchTags,
            ingredients = _state.value.searchIngredients,
            name = _state.value.searchString,
        ).onEach {
                result->
            when(result){
                is Resource.Success -> {
                    _state.value = _state.value.copy(
                        searchCount = result.data?: 0
                    )
                    decrementDataLoadingCounter()
                }
                is Resource.Error -> {
                    _state.value = _state.value.copy(
                        error = result.message?: "An unexpected error occurred"
                    )
                }
                is Resource.Loading -> {
                    _state.value = _state.value.copy(
                        isLoading = true
                    )
                }
            }
        }.launchIn(viewModelScope)
    }

    fun searchCocktail(

        tags:String = _state.value.searchTags,
        name: String = _state.value.searchString,
        ingredients: String = _state.value.searchIngredients,
        page: Int = 1){
        tabIndex = 0
        _state.value = _state.value.copy(
            searchTags = tags,
            searchString = name,
            searchIngredients = ingredients
        )
        getSearchCount()
        getCocktailSearch(
            tags=_state.value.searchTags,
            name = _state.value.searchString,
            ingredients = _state.value.searchIngredients,
            page=page).onEach {
                result->
            when(result){
                is Resource.Success -> {
                    _state.value = _state.value.copy(
                        search_cocktail = result.data?: emptyList()
                    )
                    decrementDataLoadingCounter()
                }
                is Resource.Error -> {
                    _state.value = _state.value.copy(
                        error = result.message?: "An unexpected error occurred"
                    )
                }
                is Resource.Loading -> {
                    _state.value = _state.value.copy(
                        isLoading = true
                    )
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun decrementDataLoadingCounter() {
        dataLoadingCounter--
        if (dataLoadingCounter <= 0) {
            _state.value = _state.value.copy(
                isLoading = false
            )
        }
    }

    private fun getAllCocktails() {
        getAll().onEach { result ->
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