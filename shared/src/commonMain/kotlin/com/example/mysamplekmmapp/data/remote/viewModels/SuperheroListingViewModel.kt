package com.example.mysamplekmmapp.data.remote.viewModels

import com.example.mysamplekmmapp.data.mappers.toSuperheroDataHolder
import com.example.mysamplekmmapp.data.model.SuperheroDetailsDataHolder
import com.example.mysamplekmmapp.data.model.SuperheroListResponseItem
import com.example.mysamplekmmapp.data.remote.repository.AppRepository
import com.rickclephas.kmm.viewmodel.KMMViewModel
import com.rickclephas.kmm.viewmodel.coroutineScope
import com.rickclephas.kmp.nativecoroutines.NativeCoroutinesState
import io.github.aakira.napier.Napier
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


open class SuperheroListingViewModel(
    private val repository: AppRepository
) : KMMViewModel(){

    private val _uiStates = MutableStateFlow(SuperheroListingUIStates())
    @NativeCoroutinesState
    val uiStates = _uiStates.asStateFlow()

    @NativeCoroutinesState
    val iosUIStates = _uiStates.asStateFlow().stateIn(
        viewModelScope.coroutineScope, SharingStarted.WhileSubscribed(),
        SuperheroListingUIStates()
    )


    init {
        getAllHeroes()
    }

    fun setSelectedSuperhero(superhero:SuperheroListResponseItem?)
    {
        _uiStates.update { it.copy(selectedSuperhero = superhero) }
    }

    private fun getAllHeroes()
    {
        viewModelScope.coroutineScope.launch {
            _uiStates.update { it.copy(loading = true) }
            try {
                val data = repository.getSuperheroList()
                _uiStates.update { it.copy(loading = true, list = data) }
            }
            catch (e:Exception)
            {
                Napier.d( "getAllHeroes: "+e.message)
             _uiStates.update { it.copy(loading = false, error = e.message?:"An Unknown Exception Occurred") }
            }
        }
    }

    private fun getAllHeroesNew()
    {
        viewModelScope.coroutineScope.launch {
            _uiStates.update { it.copy(loading = true) }
            try {
                val data = repository.getSuperheroList()
                _uiStates.update { it.copy(loading = true, newList = data.map { superhero-> superhero.toSuperheroDataHolder() }) }
            }
            catch (e:Exception)
            {
                Napier.d( "getAllHeroes: "+e.message)
                _uiStates.update { it.copy(loading = false, error = e.message?:"An Unknown Exception Occurred") }
            }
        }
    }
}

data class SuperheroListingUIStates(
    val loading: Boolean = false,
    val list: List<SuperheroListResponseItem?> = arrayListOf(),
    val newList: List<SuperheroDetailsDataHolder?> = arrayListOf(),
    val selectedSuperhero :SuperheroListResponseItem? = null,
    val error: String = ""
)