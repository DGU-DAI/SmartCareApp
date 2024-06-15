package com.dgu.smartcareapp.di

import com.dgu.smartcareapp.data.repository.DummyRepositoryImpl
import com.dgu.smartcareapp.domain.repository.DummyRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun providesDummyRepository(dummyRepositoryImpl: DummyRepositoryImpl): DummyRepository =
        dummyRepositoryImpl
}
