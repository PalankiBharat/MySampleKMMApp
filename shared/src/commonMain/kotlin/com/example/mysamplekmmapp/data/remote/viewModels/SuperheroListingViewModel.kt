package com.example.mysamplekmmapp.data.remote.viewModels

import com.example.mysamplekmmapp.data.mappers.toSuperheroDataHolder
import com.example.mysamplekmmapp.data.model.SuperheroDetailsDataHolder
import com.example.mysamplekmmapp.data.model.SuperheroListResponseItem
import com.example.mysamplekmmapp.data.remote.repository.AppRepository
import com.rickclephas.kmm.viewmodel.KMMViewModel
import com.rickclephas.kmm.viewmodel.coroutineScope
import com.rickclephas.kmm.viewmodel.stateIn
import com.rickclephas.kmp.nativecoroutines.NativeCoroutinesState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent


open class SuperheroListingViewModel(
    private val repository: AppRepository
) : KMMViewModel(), KoinComponent{

    private val _uiStates = MutableStateFlow(SuperheroListingUIStates())
    @NativeCoroutinesState
    val uiStates = _uiStates.asStateFlow()

    @NativeCoroutinesState
    val iosUIStates = _uiStates.asStateFlow().stateIn(
        viewModelScope, SharingStarted.WhileSubscribed(),
        SuperheroListingUIStates()
    )


    init {
        getAllHeroes()
    }

    fun getSuperheroById(id:Int):SuperheroDetailsDataHolder
    {
        return repository.getSuperheroById(id).toSuperheroDataHolder()
    }

    private fun getAllHeroes()
    {
        viewModelScope.coroutineScope.launch {
            try {
                val data = repository.getSuperheroList()
            }
            catch (e:Exception)
            {
             _uiStates.update { it.copy(loading = false, error = e.message?:"An Unknown Exception Occurred") }
            }
        }
    }

    @NativeCoroutinesState
    val superheroListingStates = repository.getSuperheroesFromLocal().stateIn(viewModelScope,
        SharingStarted.WhileSubscribed(), emptyList()
    )
}

data class SuperheroListingUIStates(
    val loading: Boolean = false,
    val list: List<SuperheroListResponseItem?> = arrayListOf(),
    val newList: List<SuperheroDetailsDataHolder?> = arrayListOf(),
    val selectedSuperhero :SuperheroDetailsDataHolder? = null,
    val error: String = ""
)

sealed class SuperheroListingEvents {
    data object GetSuperheroFromId : SuperheroListingEvents()
}
