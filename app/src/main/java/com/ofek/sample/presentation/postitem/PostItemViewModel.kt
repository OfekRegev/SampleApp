package com.ofek.sample.presentation.postitem

import androidx.lifecycle.*
import com.ofek.sample.domain.models.remote.RemoteResponseDto
import com.ofek.sample.domain.post.FetchPostItemUseCase
import com.ofek.sample.domain.post.FetchPostItemUseCaseParams
import com.ofek.sample.presentation.common.ViewModelDispatchers
import com.ofek.sample.presentation.errors.PresentationError
import com.ofek.sample.presentation.errors.presentationErrorFromException
import com.ofek.sample.presentation.postitem.models.mapPostItemDtoToPostItemModel
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class PostItemViewModel @AssistedInject constructor(
    private val fetchPostItem: FetchPostItemUseCase,
    private val viewModelDispatchers: ViewModelDispatchers,
    @Assisted private val postId: String,
) : ViewModel() {

    private val _postItemStateLiveData = MutableLiveData(PostItemState())
    private val _errorLiveData = MutableLiveData<PresentationError?>()

    fun postItemState(): LiveData<PostItemState> = _postItemStateLiveData

    companion object {
        fun provideFactory(
            assistedFactory: PostItemViewModel.PostItemViewModelFactory,
            postId: String,
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return assistedFactory.create(postId) as T
            }
        }
    }


    @AssistedFactory
    interface PostItemViewModelFactory {
        fun create(postId: String): PostItemViewModel
    }

    fun onScreenCreated() {
        val currentState = _postItemStateLiveData.value
        currentState?.let {
            // prevent reloading on recomposition or configuration changes
            val shouldFetchItem = currentState.postItemModel == null && currentState.loading.not()
            if (shouldFetchItem) {
                loadPostItem()
            }
        }
    }

    private fun loadPostItem() {
        _postItemStateLiveData.value = _postItemStateLiveData.value?.copy(
            loading = true
        )
        _errorLiveData.value = null
        viewModelScope.launch(context = viewModelDispatchers.asyncIoDispatcher) {
            when (val response = fetchPostItem(FetchPostItemUseCaseParams(postId))) {
                is RemoteResponseDto.Success -> {
                    val postItem = mapPostItemDtoToPostItemModel(response.content)
                    withContext(viewModelDispatchers.mainDispatcher) {
                        val currentState = _postItemStateLiveData.value
                        _postItemStateLiveData.value = currentState?.copy(
                            loading = false,
                            postItemModel = postItem
                        )
                    }
                }
                is RemoteResponseDto.Error -> {
                    val error =
                        presentationErrorFromException(exception = response.exception)
                    withContext(viewModelDispatchers.mainDispatcher) {
                        _errorLiveData.value = error
                        _postItemStateLiveData.value = _postItemStateLiveData.value?.copy(
                            loading = false
                        )
                    }
                }
            }
        }
    }


}