package com.example.nyt.feature.mostPopular.ui.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nyt.feature.mostPopular.data.domain.MostPopularRepository
import com.example.nyt.feature.mostPopular.data.remote.data.response.MostPopularResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class MostPopularViewModel @Inject constructor(private val mostPopularRepository: MostPopularRepository) :
    ViewModel() {

    private val _viewState = MutableStateFlow(MostPopularsViewState())
    val viewState = _viewState.asStateFlow()

    private val _event = Channel<MostPopularsEvent>()
    val event = _event.consumeAsFlow()

    fun get(type: Int) {
        mostPopularRepository
            .get(type)
            .onEach { result ->
                result.onSuccess { data -> _viewState.update { MostPopularsViewState(data).also { println("ssda ${data.size}") } } }
            }
            .launchIn(viewModelScope)
    }
}

class MostPopularsViewState(
    val list: List<MostPopularResponse.Result> = emptyList()
)

sealed class MostPopularsEvent {

}