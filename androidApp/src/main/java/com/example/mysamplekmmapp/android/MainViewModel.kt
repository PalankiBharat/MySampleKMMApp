package com.example.mysamplekmmapp.android

import android.util.Log
import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mysamplekmmapp.data.model.SuperheroListResponseItem
import com.example.mysamplekmmapp.data.remote.repository.AppRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


class MainViewModel(
    private val repository: AppRepository
) : ViewModel(){

    private val _uiStates = MutableStateFlow(MainUIStates())
    val uiStates = _uiStates.asStateFlow()

    init {
        getAllHeroes()
    }

    fun getAllHeroes()
    {
        viewModelScope.launch {
            _uiStates.update { it.copy(loading = true) }
            try {
                val data = repository.getSuperheroList()
                _uiStates.update { it.copy(loading = true, list = data) }
            }
            catch (e:Exception)
            {
             _uiStates.update { it.copy(loading = false, error = e.localizedMessage) }
            }
        }
    }
}

data class MainUIStates(
    val loading: Boolean = false,
    val list: List<SuperheroListResponseItem?> = arrayListOf(),
    val error: String = ""
)