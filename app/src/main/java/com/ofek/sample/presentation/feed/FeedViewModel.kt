package com.ofek.sample.presentation.feed

import androidx.lifecycle.*
import com.ofek.sample.data.exceptions.ApiException
import com.ofek.sample.data.exceptions.NetworkUnavailableException
import com.ofek.sample.domain.feed.FeedType
import com.ofek.sample.domain.feed.FetchFeedUseCase
import com.ofek.sample.domain.models.remote.RemotePagingRequestDto
import com.ofek.sample.domain.models.remote.RemoteResponseDto
import com.ofek.sample.presentation.common.ViewModelDispatchers
import com.ofek.sample.presentation.errors.Error
import com.ofek.sample.presentation.errors.GeneralError
import com.ofek.sample.presentation.errors.NetworkError
import com.ofek.sample.presentation.feed.models.mapFeedPostItemDtoToUiPostListItemModel
import com.ofek.sample.presentation.navigation.NavigationManager
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.net.UnknownHostException

class FeedViewModel @AssistedInject constructor(
    private val fetchFeedUseCase: FetchFeedUseCase,
    private val viewModelDispatchers: ViewModelDispatchers,
    private val navigationManager: NavigationManager,
    @Assisted private val feedType: FeedType,
) : ViewModel() {


    companion object {


        fun provideFactory(
            assistedFactory: FeedViewModelFactory,
            feedType: FeedType,
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return assistedFactory.create(feedType) as T
            }
        }
    }

    @AssistedFactory
    interface FeedViewModelFactory {
        fun create(feedType: FeedType): FeedViewModel
    }


    private val _errorLiveData = MutableLiveData<Error?>(null)
    private val _feedStateLiveData = MutableLiveData(FeedState())
    private var fetchingJob: Job? = null
    fun feedState(): LiveData<FeedState> = _feedStateLiveData

    fun getErrorState(): LiveData<Error?> = _errorLiveData

    fun onScreenCreated() {
        val currentState = _feedStateLiveData.value
        // avoid reloading the list when screen recreated
        val loading = currentState?.isLoading ?: false
        val items = currentState?.items.orEmpty()
        if (items.isEmpty() && loading.not()) {
            fetchFeedItems(0, false)
        }
    }

    fun onLoadMore() {
        val currentState = _feedStateLiveData.value
        val isCurrentlyLoading = currentState?.isLoading ?: false
        val canLoadMore = currentState?.canLoadMore ?: false
        val currentPage = currentState?.currentPage ?: 0
        if (isCurrentlyLoading.not() && canLoadMore) {
            fetchFeedItems(currentPage + 1, false)
        }
    }

    private fun fetchFeedItems(
        page: Int,
        refresh: Boolean,
    ) {
        _feedStateLiveData.value = _feedStateLiveData.value?.copy(
            isLoading = true
        )
        val job = viewModelScope.launch(
            context = viewModelDispatchers.asyncIoDispatcher,
        ) {
            val responseDto = fetchFeedUseCase(
                FetchFeedUseCase.FetchFeedUseCaseParams(
                    RemotePagingRequestDto(
                        page
                    ),
                    feedType = feedType
                )
            )
            val currentState = _feedStateLiveData.value
            when (responseDto) {
                is RemoteResponseDto.Success -> {
                    val items = responseDto.content.items.map {
                        mapFeedPostItemDtoToUiPostListItemModel(it, ::onPostItemClicked)
                    }
                    val newItemsList = if (refresh) {
                        items
                    } else {
                        val collectedItems = ArrayList(currentState?.items.orEmpty())
                        collectedItems.addAll(items)
                        collectedItems
                    }
                    withContext(viewModelDispatchers.mainDispatcher) {
                        _feedStateLiveData.value = currentState?.copy(
                            items = newItemsList,
                            isLoading = false,
                            currentPage = responseDto.content.page,
                            canLoadMore = responseDto.content.canLoadMore
                        )
                    }
                }
                is RemoteResponseDto.Error -> {
                    withContext(viewModelDispatchers.mainDispatcher) {
                        if (responseDto.exception is NetworkUnavailableException ||
                            responseDto.exception is ApiException || responseDto.exception is UnknownHostException
                        ) {
                            _errorLiveData.value = NetworkError
                            _feedStateLiveData.value = currentState?.copy(
                                isLoading = false
                            )
                        } else {
                            _errorLiveData.value = GeneralError
                        }
                    }
                }
            }
        }
        fetchingJob = job
    }

    private fun onPostItemClicked(s: String) {
//        navigationManager.navigateTo()
    }

    fun refresh() {
        // cancel any other fetching action if exists so it won't collide with the refresh
        fetchingJob?.cancel()
        fetchFeedItems(0, true)
    }
}