package com.example.mysamplekmmapp.data.remote.viewModels

import com.example.mysamplekmmapp.data.model.SuperheroListResponseItem
import com.example.mysamplekmmapp.data.remote.repository.AppRepository
import com.rickclephas.kmm.viewmodel.KMMViewModel
import com.rickclephas.kmm.viewmodel.coroutineScope
import com.rickclephas.kmp.nativecoroutines.NativeCoroutinesState
import io.github.aakira.napier.Napier
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


class SuperheroListingViewModel(
    private val repository: AppRepository
) : KMMViewModel(){

    private val _uiStates = MutableStateFlow(SuperheroListingUIStates())
    @NativeCoroutinesState
    val uiStates = _uiStates.asStateFlow()

    init {
        getAllHeroes()
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
}

data class SuperheroListingUIStates(
    val loading: Boolean = false,
    val list: List<SuperheroListResponseItem?> = arrayListOf(),
    val error: String = ""
)