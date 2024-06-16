package com.dgu.smartcareapp.data.localdatasource

import android.content.Context
import androidx.core.content.edit
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import com.dgu.smartcareapp.BuildConfig
import com.dgu.smartcareapp.domain.entity.SmartCareStorage
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SmartCareStorageImpl @Inject constructor(@ApplicationContext context: Context) :
    SmartCareStorage {
    private val masterKey = MasterKey.Builder(context, MasterKey.DEFAULT_MASTER_KEY_ALIAS)
        .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
        .build()

    private val pref by lazy {
        if (BuildConfig.DEBUG) {
            context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE)
        } else {
            EncryptedSharedPreferences.create(
                context,
                FILE_NAME,
                masterKey,
                EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
                EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM,
            )
        }
    }

    override var phoneNumber: String
        set(value) = pref.edit { putString(PHONE_NUMBER, value) }
        get() = pref.getString(
            PHONE_NUMBER,
            "01091631442",
        ) ?: "01091631442"

    companion object {
        private const val FILE_NAME = "SCDataStore"
        private const val PHONE_NUMBER = "phone_number"
    }
}
