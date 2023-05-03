package ru.mmcs.disneywiki.viemodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import ru.mmcs.disneywiki.adapters.CharacterListRvAdapter
import ru.mmcs.disneywiki.databinding.FragmentListBinding
import ru.mmcs.disneywiki.entities.DisneyCharacter
import ru.mmcs.disneywiki.repositories.CharacterRepository

class CharacterListViewModel(private val repository: CharacterRepository) : ViewModel() {

    val totalPages = MutableLiveData(1)
    val currentpage = MutableLiveData(1)
    val isLoadingInProgress = MutableLiveData(false)
    val characterList = MutableLiveData<List<DisneyCharacter?>?>(listOf())

    private val _uiState = MutableStateFlow(UiState())
    val uiState: StateFlow<UiState> = _uiState

    public val characterListRvAdapter: CharacterListRvAdapter = CharacterListRvAdapter(characterList,
        object: CharacterListRvAdapter.OnItemInteractionListener{
            override fun onItemClicked(item: DisneyCharacter?) {
                _uiState.update { currentUiState ->
                    currentUiState.copy(navigationTargetId = item?._id)
                }
            }
        })

    init{
        repository.attach(this)
        isLoadingInProgress.value = true
        repository.getCharacters()
    }

    override fun onCleared() {
        super.onCleared()
        repository.detach()
    }

    public fun onBtnPrevClick(){
        if(currentpage.value ==  1){
            return
        }
        isLoadingInProgress.value = true
        currentpage.value = currentpage.value!! - 1
        repository.getCharacters(currentpage.value!!)
    }

    public fun onBtnNextClick(){
        if(currentpage.value == totalPages.value!! - 1){
            return
        }
        isLoadingInProgress.value = true
        currentpage.value = currentpage.value!! + 1
        repository.getCharacters(currentpage.value!!)
    }

    fun onCharacterListReceived(items: List<DisneyCharacter?>?, totalItemsCount: Int?){
        totalPages.value = totalItemsCount ?: 0
        characterList.value = items
        isLoadingInProgress.value = false
        characterListRvAdapter.notifyItemRangeChanged(0,characterList.value?.size ?: 0)
    }

    fun onFetchFailed(reason: String){
        isLoadingInProgress.value = false
        _uiState.update { currentUiState ->
            currentUiState.copy(errorMessage = reason)
        }
    }

    fun onDetailsFragmentOpened(){
        _uiState.update { currentUiState ->
            currentUiState.copy(navigationTargetId = null)
        }
    }

    fun onErrorMessageShown(){
        _uiState.update { currentUiState ->
            currentUiState.copy(errorMessage = null)
        }
    }

    data class UiState(val errorMessage: String? = null,
                       val navigationTargetId: Int? = null)

    class Factory(private val repo : CharacterRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return CharacterListViewModel(repo) as T
        }
    }
}