package com.example.nyt.feature.mostPopular.ui.list

import com.example.nyt.core.util.DispatchersProvider
import com.example.nyt.feature.mostPopular.data.domain.MostPopularRepository
import com.example.nyt.feature.mostPopular.data.domain.data.MostPopular
import com.example.nyt.util.MainDispatcherRule
import com.example.nyt.util.TestCoroutineDispatchersProvider
import com.google.common.truth.Truth
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import kotlinx.coroutines.time.delay
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import javax.inject.Inject

@HiltAndroidTest
class MostPopularViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var viewModel: MostPopularViewModel

    @Mock
    private lateinit var mostPopularRepository: MostPopularRepository

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        viewModel = MostPopularViewModel(mostPopularRepository)
    }

    @Test
    fun `loading empty data`() = runTest {
        Mockito.`when`(mostPopularRepository.get(Mockito.anyInt())).thenAnswer {
            return@thenAnswer flowOf<Result<List<MostPopular>>>(Result.success(emptyList()))
        }

        viewModel.loadMostPopular(MostPopularDuration.MONTH)

        val expectedState = MostPopularsViewState(list = emptyList(), currentDuration = MostPopularDuration.MONTH)

        Truth.assertThat(viewModel.viewState.value).isEqualTo(expectedState)
    }

    @Test
    fun `loading data with one item`() = runTest()  {
        val mostPopular = MostPopular("title")
        Mockito.`when`(mostPopularRepository.get(Mockito.anyInt())).thenAnswer {
            return@thenAnswer flowOf<Result<List<MostPopular>>>(Result.success(listOf(mostPopular)))
        }

        viewModel.loadMostPopular(MostPopularDuration.MONTH)

        val expectedState = MostPopularsViewState(list = listOf(mostPopular), currentDuration = MostPopularDuration.MONTH)

        Truth.assertThat(viewModel.viewState.value).isEqualTo(expectedState)
    }
}