package com.ofek.sample.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DomainCommonModule {


    @Provides
    @Singleton
    fun navigationChannel(): MutableStateFlow<String?> {
        return MutableStateFlow(null)
    }

}