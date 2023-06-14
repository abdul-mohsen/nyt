package com.example.nyt.feature.mostPopular.ui.list

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Chip
import androidx.compose.material.ChipDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun MostPopularsScreen(viewModel: MostPopularViewModel) {
    viewModel.init()
    val viewState by viewModel.viewState.collectAsState()

    Column(modifier = Modifier.padding(8.dp)) {
        DurationChips(MostPopularDuration.values(), viewState.currentDuration, viewModel::loadMostPopular)

        LazyColumn {
            items(viewState.list) { item ->
                Text(text = item.title)
            }
        }
    }

}

@Composable
private fun DurationChips(
    categories: Array<MostPopularDuration>,
    currentDuration: MostPopularDuration,
    onClock: (MostPopularDuration) -> Unit,
) {

    LazyRow {

        items(categories) {
            val isSelected = currentDuration.ordinal == it.ordinal
            val textColor =
                if (isSelected) MaterialTheme.colorScheme.onPrimary
                else MaterialTheme.colorScheme.secondary
            val background =
                if (isSelected) MaterialTheme.colorScheme.primary
                else MaterialTheme.colorScheme.surface
            val border =
                BorderStroke(1.dp, MaterialTheme.colorScheme.outline).takeIf { isSelected.not() }
            Chip(
                onClick = { onClock(it) },
                modifier = Modifier.padding(8.dp),
                colors = ChipDefaults.chipColors(backgroundColor = background),
                border = border,
            ) {
                Text(text = it.name, color = textColor)
            }
        }
    }
}
