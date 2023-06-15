package com.example.mysamplekmmapp.android.ui.superheroListing

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.mysamplekmmapp.data.model.SuperheroListResponseItem
import com.example.mysamplekmmapp.data.remote.viewModels.SuperheroListingViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun SuperheroListScreen(
    viewModel: SuperheroListingViewModel,
    onItemClick: (SuperheroListResponseItem?) -> Unit
) {
    Column {
        val uiStates = viewModel.uiStates.collectAsStateWithLifecycle()
        LazyVerticalGrid(columns = GridCells.Fixed(2)) {
            items(uiStates.value.list.size) { index ->
                val item = uiStates.value.list[index]
                if (item != null) {
                    SuperheroCard(
                        modifier = Modifier.clickable {
                            onItemClick(uiStates.value.list[index])
                        },
                        imageUrl = item.images?.lg ?: "",
                        title = item.name ?: ""
                    )
                }
            }
        }
    }

}