package com.example.mysamplekmmapp.android.superherolistScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.mysamplekmmapp.android.MainViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun SuperheroListScreen(
        viewModel:MainViewModel = koinViewModel()
) {
    Column {
        val uiStates = viewModel.uiStates.collectAsStateWithLifecycle()
        LazyVerticalGrid(columns = GridCells.Fixed(2)){
            items(uiStates.value.list.size){index->
                val item = uiStates.value.list[index]
                if (item != null) {
                    SuperheroCard(imageUrl = item.images?.lg ?: "", title = item.name ?: "")
                }
            }
        }
    }

}