package com.example.mysamplekmmapp.android.ui.superheroListing

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.mysamplekmmapp.data.remote.viewModels.SuperheroListingViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun SuperheroListScreen(
        viewModel:SuperheroListingViewModel = koinViewModel(),
        onItemClick:()->Unit
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