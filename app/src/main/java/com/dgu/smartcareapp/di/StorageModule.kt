package com.dgu.smartcareapp.di

import android.content.Context
import com.dgu.smartcareapp.data.localdatasource.SmartCareStorageImpl
import com.dgu.smartcareapp.domain.entity.SmartCareStorage
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object StorageModule {
    @Provides
    @Singleton
    fun provideSmartCareStorage(@ApplicationContext context: Context): SmartCareStorage =
        SmartCareStorageImpl(context)
}
