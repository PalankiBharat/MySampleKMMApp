package com.example.mysamplekmmapp.android

import android.util.Log
import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mysamplekmmapp.data.model.SuperheroListResponseItem
import com.example.mysamplekmmapp.data.remote.repository.AppRepository
import kotlinx.coroutines.launch


class MainViewModel(
    private val repository: AppRepository
) : ViewModel(){

    var uiStates by mutableStateOf(MainUIStates())

    fun getAllHeroes()
    {

        viewModelScope.launch {
            //uiStates.copy(loading = true)
            try {
                Log.d("TAG", "getAllHeroes: ")
                val data = repository.getSuperheroList()
                Log.d("TAG", "getAllHeroes lisy: "+data)
              //  uiStates.copy(loading = false,list = data)
            }
            catch (e:Exception)
            {
            //    uiStates.copy(loading = false, error = e.localizedMessage)
            }

        }
    }

}

data class MainUIStates(
    val loading: Boolean = false,
    val list: List<SuperheroListResponseItem?> = arrayListOf(),
    val error: String = ""
)