package com.ofek.sample.presentation.common

import kotlinx.coroutines.CoroutineDispatcher

class ViewModelDispatchers(
    val asyncIoDispatcher: CoroutineDispatcher,
    val mainDispatcher: CoroutineDispatcher,
    val asyncComputationDispatcher: CoroutineDispatcher,
) 