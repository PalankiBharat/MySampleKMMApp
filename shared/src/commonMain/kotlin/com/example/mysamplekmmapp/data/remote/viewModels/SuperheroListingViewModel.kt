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
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
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
        viewModelScope.coroutineScope, SharingStarted.WhileSubscribed(),
        SuperheroListingUIStates()
    )


    init {
        getAllHeroesNew()
      //  getAllHeroes()
    }

    fun setSelectedSuperhero(superhero:SuperheroListResponseItem?)
    {
        _uiStates.update { it.copy(selectedSuperhero = superhero) }
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

    fun getAllHeroesNew() = repository.getSuperheroesFromLocal()
}

data class SuperheroListingUIStates(
    val loading: Boolean = false,
    val list: List<SuperheroListResponseItem?> = arrayListOf(),
    val newList: List<SuperheroDetailsDataHolder?> = arrayListOf(),
    val selectedSuperhero :SuperheroListResponseItem? = null,
    val error: String = ""
)