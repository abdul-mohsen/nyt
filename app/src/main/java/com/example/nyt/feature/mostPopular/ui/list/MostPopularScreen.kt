package com.example.nyt.feature.mostPopular.ui.list

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.nyt.ui.theme.NytTheme

@Composable
fun MostPopularsScreen(viewModel: MostPopularViewModel) {
    viewModel.get(1)
    val viewState by viewModel.viewState.collectAsState()
    LazyColumn {
        items(viewState.list) { item ->
            Text(text = item.title.orEmpty())
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    NytTheme {
    }
}