package com.example.nyt.feature.mostPopular.ui.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nyt.feature.mostPopular.data.domain.MostPopularRepository
import com.example.nyt.feature.mostPopular.data.domain.data.MostPopular
import com.example.nyt.feature.mostPopular.data.remote.data.response.MostPopularResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.flow.launchIn
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

    private var job: Job? = null

    fun loadMostPopular(type: MostPopularDuration) {
        _viewState.update { it.copy(currentDuration = type) }
        job?.cancel()
        job = mostPopularRepository
            .get(type.getValue())
            .onEach { result ->
                result.onSuccess { data -> _viewState.update { it.copy(list = data) } }
            }
            .launchIn(viewModelScope)
    }

    fun init() {
        if (job == null) loadMostPopular(MostPopularDuration.ONE_DAY)
    }
}

data class MostPopularsViewState(
    val list: List<MostPopular> = emptyList(),
    val currentDuration: MostPopularDuration = MostPopularDuration.ONE_DAY
)

sealed class MostPopularsEvent {

}

enum class MostPopularDuration {
    ONE_DAY, SEVEN_DAYS, MONTH;

    fun getValue() = when (this) {
        ONE_DAY -> 1
        SEVEN_DAYS -> 7
        MONTH -> 30
    }
}