package com.example.mysamplekmmapp.android.ui.superheroListing

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.mysamplekmmapp.data.model.SuperheroDetailsDataHolder
import com.example.mysamplekmmapp.data.model.SuperheroListResponseItem
import com.example.mysamplekmmapp.data.remote.viewModels.SuperheroListingViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun SuperheroListScreen(
    viewModel: SuperheroListingViewModel,
    onItemClick: (SuperheroDetailsDataHolder?) -> Unit
) {
    Column {
        val list = viewModel.superheroListingStates.collectAsStateWithLifecycle(emptyList())
        LazyVerticalGrid(columns = GridCells.Fixed(2)) {
            items(list.value.size) { index ->
                val item = list.value[index]
                SuperheroCard(
                    modifier = Modifier.clickable {
                        onItemClick(item)
                    },
                    imageUrl = item.imageUrl,
                    title = item.name
                )
            }
        }
    }

}