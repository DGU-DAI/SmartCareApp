package com.dgu.smartcareapp.di


import android.content.Context
import com.dgu.smartcareapp.util.VoiceRecognitionManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object VoiceRecognitionModule {

    @Singleton
    @Provides
    fun provideVoiceRecognitionManager(
        @ApplicationContext context: Context
    ): VoiceRecognitionManager {
        return VoiceRecognitionManager(context)
    }
}
