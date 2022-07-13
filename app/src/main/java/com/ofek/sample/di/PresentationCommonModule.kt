package com.ofek.sample.di

import android.content.Context
import com.ofek.sample.presentation.common.ResourceProvider
import com.ofek.sample.presentation.common.ResourcesProviderImpl
import com.ofek.sample.presentation.common.ViewModelDispatchers
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers

@Module
@InstallIn(SingletonComponent::class)
object PresentationCommonModule {
    @Provides
    fun resourceProvider(
        @ApplicationContext context: Context,
    ): ResourceProvider {
        return ResourcesProviderImpl(context.resources)
    }

    @Provides
    fun viewModelDispatchers(): ViewModelDispatchers {
        return ViewModelDispatchers(
            asyncIoDispatcher = Dispatchers.IO,
            mainDispatcher = Dispatchers.Main,
            asyncComputationDispatcher = Dispatchers.Default
        )
    }
}